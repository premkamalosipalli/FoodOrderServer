package com.booking.food_Order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.food_Order.entity.Restaurant;
import com.booking.food_Order.entity.RestaurantItems;
import com.booking.food_Order.exception.ResourceNotFoundException;
import com.booking.food_Order.repository.RestaurantItemRepository;
import com.booking.food_Order.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantItemService {
    
    @Autowired
    public RestaurantItemRepository restaurantItemRepository;

    @Autowired
    public RestaurantRepository restaurantRepository;

    public RestaurantItems save(RestaurantItems restaurantItem){
        return restaurantItemRepository.save(restaurantItem);
    }

    public List<RestaurantItems> getAllRestaurantItemsByRestaurantId(int restaurantId) throws ResourceNotFoundException{
        if(!restaurantRepository.existsByRestaurantId(restaurantId)){
            throw new ResourceNotFoundException("Not found Restaurant with restaurantId = " + restaurantId);
        }
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return restaurantItemRepository.findAllByRestaurant(restaurant);
    }

    public List<RestaurantItems> findAllByRestaurantId(int restaurantId){
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return restaurantItemRepository.findAllByRestaurant(restaurant);

    }

    public RestaurantItems findByRestaurantItemId(int restaurantItemId){
        RestaurantItems restaurantItems = restaurantItemRepository.findByRestaurantItemId(restaurantItemId);
        return restaurantItems;
    }

    // public RestaurantItems getRestaurantItemByRestaurantId(int restaurantId){
    //     return restaurantItemRepository.findByRestaurantId(restaurantId);
    // }

    public void deleteByRestaurantItemId(int restaurantItemId){
        restaurantItemRepository.deleteByRestaurantItemId(restaurantItemId);
    }

    public void deleteAllRestaurantItemsByRestaurantId(int restaurantId) throws ResourceNotFoundException{
        if(!restaurantRepository.existsByRestaurantId(restaurantId)){
            throw new ResourceNotFoundException("Not found Restaurant with restaurantId = " + restaurantId);
        }
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        restaurantItemRepository.deleteAllByRestaurant(restaurant);
    }

    // public RestaurantItems findByRestaurant(Restaurant restaurant){
    //     return restaurantItemRepository.findByRestaurant(restaurant);
    // }

    public int countRestaurantItems(){
        return  (int)restaurantItemRepository.count();
    }
}
