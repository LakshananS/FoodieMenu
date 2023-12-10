package com.example.foodiemenu;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText user_name,user_email,user_password,con_password,user_address,user_phone;
    private ProgressBar progressBar;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name = findViewById(R.id.name);
        user_email = findViewById(R.id.email_id);
        user_password = findViewById(R.id.editTextTextPassword);
        con_password = findViewById(R.id.confirmPassword);
        user_phone = findViewById(R.id.phonenum);
        user_address = findViewById(R.id.address);
        progressBar = findViewById(R.id.progressBar);
        registerBtn = findViewById(R.id.register);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user_name.getText().toString();
                String email = user_email.getText().toString();
                String password =user_password.getText().toString();
                String confirm_password = con_password.getText().toString();
                String number = user_phone.getText().toString();
                String address = user_address.getText().toString();


                if (TextUtils.isEmpty(uname)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    user_name.setError("Full name is required");
                    user_name.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this, "Please re enter your email address", Toast.LENGTH_SHORT).show();
                    user_email.setError("Email is required");
                    user_email.requestFocus();
                } else if (TextUtils.isEmpty(number)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your valid mobile number", Toast.LENGTH_SHORT).show();
                    user_phone.setError("Phone number is required");
                    user_phone.requestFocus();
                } else if (number.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "Please  re-enter your mobile number", Toast.LENGTH_SHORT).show();
                    user_phone.setError("Mobile number should be 10 digits");
                    user_phone.requestFocus();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    user_address.setError("Address is required");
                    user_address.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    user_password.setError("Password is required");
                    user_password.requestFocus();
                } else if (TextUtils.isEmpty(confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
                    con_password.setError("Password confirmation is required");
                    con_password.requestFocus();
                } else if (!password.equals(confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "Please check you passswords", Toast.LENGTH_SHORT).show();
                    con_password.setError("Password confirmation is required");
                    con_password.requestFocus();
                    //remove already entered passwords
                    user_password.clearComposingText();
                    con_password.clearComposingText();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(uname, email, number, address, password, confirm_password);
                }

            }
        });

    }

    private void registerUser(String uname, String email, String number, String address, String password, String confirm_password) {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    // Create User Profile

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                FirebaseUser firebaseUser = auth.getCurrentUser();

                // Enter User data into Firebase realtime database
                ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(uname, number, address);

                // Extracting user from database gor "Registered user"
                DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered user");

                referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            // sent verify email
                            firebaseUser.sendEmailVerification();

                            Toast.makeText(RegisterActivity.this,"User Register Successfully", Toast.LENGTH_SHORT).show();


                            // Open User profile after successful registration
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this,"User Registered Failed, Please try again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                });

            } else {
                try {
                    throw task.getException();
                } catch (FirebaseAuthWeakPasswordException e){
                    user_password.setError("You password is too weak.Kindly use a mix of alphabets, numbers and special characters");
                    user_password.requestFocus();
                } catch (FirebaseAuthInvalidCredentialsException e){
                    user_password.setError("Your email is invalid or already in use. Kindly re enter.");
                    user_password.requestFocus();
                } catch (FirebaseAuthUserCollisionException e){
                    user_email.setError("User is already registered with this email. Use another email.");
                    user_email.requestFocus();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }
    });
    }
}