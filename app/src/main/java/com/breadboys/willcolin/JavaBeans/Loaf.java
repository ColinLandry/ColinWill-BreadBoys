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

        Loaf white = new Loaf(R.drawable.whitebread_icon, "White Bread", "Classic white bleached wheat, fantastic for sandwiches", 1.99);
        loaves.add(white);

        Loaf wholeWheat = new Loaf(R.drawable.wholewheat_icon, "Whole Wheat", "The healthier alternative to white bread", 3.99);
        loaves.add(wholeWheat);

        Loaf sourdough = new Loaf(R.drawable.sourdough_icon, "Sourdough", "Delicious light round loaf, perfect for parties", 3.99);
        loaves.add(sourdough);

        Loaf garlicBread = new Loaf(R.drawable.garlic_icon, "Garlic Bread", "Our delicious white bread toasted to perfection with garlic butter and other herbs", 2.99);
        loaves.add(garlicBread);

        Loaf baguette = new Loaf(R.drawable.baguette_icon, "White Baguette", "A long tasty baguette infused with flavour", 3.99);
        loaves.add(baguette);

        Loaf yumyum = new Loaf(R.drawable.yumyum_icon, "Yum Yum Bread", "The yummiest bread around, only available at Bread Boys", 5.99);
        loaves.add(yumyum);
    }

    public static ArrayList<Loaf> getItemsWithQuantity(){
        ArrayList<Loaf> tempList = new ArrayList<Loaf>();
        //Loop through loaf list
        for(int i = 0; i < Loaf.getList().size(); i++){
            Loaf loaf = Loaf.getList().get(i);
            //Check if quantity is 0, if not, add to checkout list
            if(loaf.getQuantity() > 0){
                tempList.add(loaf);
                System.out.println(loaf.getName() + ": " + loaf.getQuantity());
            }
        }
        System.out.println(tempList);
        return tempList;
    }
}
