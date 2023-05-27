package com.booking.food_Order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.food_Order.entity.Restaurant;
import com.booking.food_Order.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant){
       return restaurantRepository.save(restaurant);
    }

    public Restaurant findByRestaurantId(int restaurantId){
        return restaurantRepository.findByRestaurantId(restaurantId);
    }

    public void deleteByRestaurantId(int restaurantId){
        restaurantRepository.deleteByRestaurantId(restaurantId);
    }

    public void deleteAllRestaurants(){
        restaurantRepository.deleteAll();
    }

    public boolean existsByRestaurantId(int restaurantId){
        return restaurantRepository.existsByRestaurantId(restaurantId);
    }

    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public int countRestaurants(){
        return  (int)restaurantRepository.count();
    }
}
