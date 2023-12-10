package com.example.foodiemenu;

public class FoodDomain {
    private String title;
    private String pic;
    private String description;
    private double cost;
    private int numberInCart;

    public FoodDomain(String title, String pic, String description, Double cost){
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.cost = cost;
    }
    public FoodDomain(String title, String pic, String description, Double cost, int numberInCart){
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.cost = cost;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
