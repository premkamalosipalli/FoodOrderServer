package com.booking.food_Order.controller;

import com.booking.food_Order.entity.Address;
import com.booking.food_Order.entity.Restaurant;
import com.booking.food_Order.service.RestaurantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    ServletContext context;

    @PostMapping("/restaurant")
    // public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant){
    //     Restaurant savedRestaurant = restaurantService.save(restaurant);
    //     return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    // }

    public ResponseEntity<Restaurant> saveRestaurant(@RequestParam("imageLocation") MultipartFile imageLocation,@RequestParam("restaurant") String restaurant) throws JsonMappingException, JsonProcessingException{
        Restaurant restaurantObject = new ObjectMapper().readValue(restaurant, Restaurant.class);
        boolean isExists = new File (context.getRealPath("/Images/")).exists();

        if(!isExists){
            new File(context.getRealPath("/Images")).mkdir();
        }

        String fileName= imageLocation.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName));

        try {
            FileUtils.writeByteArrayToFile(serverFile, imageLocation.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        restaurantObject.setImageLocation(newFileName);

        Restaurant restaurantRes = restaurantService.save(restaurantObject);
        if(restaurantRes != null){
            return new ResponseEntity<>(restaurantRes,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(restaurantRes,HttpStatus.BAD_REQUEST);
        }
    }

    // @GetMapping("/image/{restaurantId}")
    // public byte[] getImage(@PathVariable("restaurantId") int restaurantId) throws IOException{
    //     Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
    //     context.getRealPath("/Images/");
    //     return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+restaurant.getImageLocation()));
    // }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants = restaurantService.findAll();
        

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    

    @PutMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("restaurantId") int restaurantId,@RequestParam("imageLocation") MultipartFile imageLocation,@RequestParam("restaurant") String restaurant) throws JsonMappingException, JsonProcessingException {
        Restaurant saveRestaurant = restaurantService.findByRestaurantId(restaurantId);

        Restaurant restaurantObject = new ObjectMapper().readValue(restaurant, Restaurant.class);
        boolean isExists = new File (context.getRealPath("/Images/")).exists();

        if(!isExists){
            new File(context.getRealPath("/Images")).mkdir();
        }

        String fileName= imageLocation.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName));

        try {
            FileUtils.writeByteArrayToFile(serverFile, imageLocation.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        restaurantObject.setImageLocation(newFileName);

        saveRestaurant.setRestaurantName(restaurantObject.getRestaurantName());
        saveRestaurant.setImageLocation(restaurantObject.getImageLocation());
        saveRestaurant.setDescription(restaurantObject.getDescription());
        Address address = restaurantObject.getAddress();
        saveRestaurant.setAddress(address);
        saveRestaurant.setPhoneNumber(restaurantObject.getPhoneNumber());

        Restaurant restaurantRes = restaurantService.save(saveRestaurant);
        if(restaurantRes != null){
            return new ResponseEntity<>(restaurantRes,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(restaurantRes,HttpStatus.BAD_REQUEST);
        }
    }

  @DeleteMapping("/restaurant/{restaurantId}")
  public ResponseEntity<HttpStatus> deleteRestaurant(@PathVariable("restaurantId") int restaurantId) {
    restaurantService.deleteByRestaurantId(restaurantId);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/deleteAllRestaurants")
  public ResponseEntity<HttpStatus> deleteAllRestaurants() {
    restaurantService.deleteAllRestaurants();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
    
}
