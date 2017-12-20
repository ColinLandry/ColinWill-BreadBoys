package com.breadboys.willcolin.JavaBeans;

import android.media.Image;
import android.widget.ImageView;

import com.breadboys.willcolin.R;

import java.util.ArrayList;

/**
 * Created by colinlandry on 2017-12-19.
 */

public class Loaf {
    private int image;
    private String name;
    private String description;
    private double price;
    private int quantity;

    private static ArrayList<Loaf> loaves = new ArrayList<Loaf>();
    public Loaf(){

    }
    public Loaf(int image, String name, String description, double price){
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }

    public String toString(){
        return this.name;
    }

    public static ArrayList<Loaf> getList(){
        return loaves;
    }

    public static void initializeInventory(){
        if(loaves.size() > 0){
            return;
        }

        Loaf pumpernickel = new Loaf(R.drawable.knead, "Pumpernickel", "Mouthwatering dark brown round loaf, perfect for spinach dip.", 3.99);
        loaves.add(pumpernickel);

        Loaf sourdough = new Loaf(R.drawable.bake, "Sourdough", "Delicious light round loaf, perfect for parties", 3.99);
        loaves.add(sourdough);

        Loaf white = new Loaf(R.drawable.mixing, "White Bread", "Classic white bleached wheat, fantastic for sandwiches", 2.99);
        loaves.add(white);

        Loaf yumyum = new Loaf(R.drawable.resting, "Yum Yum Bread", "The yummiest bread around, only available at Bread Boys", 5.99);
        loaves.add(yumyum);
    }
}