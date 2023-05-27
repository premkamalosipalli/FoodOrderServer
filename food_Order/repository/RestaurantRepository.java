package com.booking.food_Order.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.food_Order.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    Restaurant findByRestaurantId(int restaurantId);

    void deleteByRestaurantId(int restaurantId);

    boolean existsByRestaurantId(int restaurantId);
    
}
