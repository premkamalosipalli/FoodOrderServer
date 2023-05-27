package com.booking.food_Order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "restaurantItems")
public class RestaurantItems {

    public RestaurantItems(int restaurantItemId,
                           String restaurantItemName,
                           String imageLocation,
                           String description,
                           int itemPrice,
                           Restaurant restaurant) {
        this.restaurantItemId = restaurantItemId;
        this.restaurantItemName = restaurantItemName;
        this.imageLocation = imageLocation;
        this.description = description;
        this.itemPrice = itemPrice;
        this.restaurant = restaurant;
    }

    public RestaurantItems() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurantItemId", nullable = false, unique = true)
    private int restaurantItemId;

    @Column(name = "restaurantItemName", length = 25, nullable = false, unique = false)
    private String restaurantItemName;

    @Column(name = "imageLocation", length = Integer.MAX_VALUE, nullable = true)
    private String imageLocation;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "itemPrice", length = 10, nullable = false)
    private int itemPrice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public int getRestaurantItemId() {
        return restaurantItemId;
    }

    @Override
    public String toString() {
        return "RestaurantItems{" +
                "restaurantItemId=" + restaurantItemId +
                ", restaurantItemName='" + restaurantItemName + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                ", description='" + description + '\'' +
                ", itemPrice=" + itemPrice +
                ", restaurant=" + restaurant +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRestaurantItemId(int restaurantItemId) {
        this.restaurantItemId = restaurantItemId;
    }

    public String getRestaurantItemName() {
        return restaurantItemName;
    }

    public void setRestaurantItemName(String restaurantItemName) {
        this.restaurantItemName = restaurantItemName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
