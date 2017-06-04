/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tqs104_rentua_legacy;

/**
 *
 * @author Pedro
 */
public class Property {
    private String title;
    private double price;
    private String type;
    private String description;
    //mais tarde os intervalos

    public Property(String title, double price, String type, String description) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.description = description;
    }
    
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
