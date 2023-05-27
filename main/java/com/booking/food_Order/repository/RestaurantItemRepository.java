package com.booking.food_Order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.food_Order.entity.Restaurant;
import com.booking.food_Order.entity.RestaurantItems;

@Repository
public interface RestaurantItemRepository extends JpaRepository<RestaurantItems,Long>{

    RestaurantItems findByRestaurantItemId(int restaurantItemId);

    List<RestaurantItems> findAllByRestaurant(Restaurant restaurnatId);

    void deleteByRestaurantItemId(int restaurantItemId);

    void deleteAllByRestaurant(Restaurant restaurantId);

    // RestaurantItems findByRestaurant(Restaurant restaurant);
    
}
