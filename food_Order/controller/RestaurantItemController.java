package com.booking.food_Order.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.booking.food_Order.entity.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.food_Order.entity.Restaurant;
import com.booking.food_Order.entity.RestaurantItems;
import com.booking.food_Order.exception.ResourceNotFoundException;
import com.booking.food_Order.service.RestaurantItemService;
import com.booking.food_Order.service.RestaurantService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@RestController @CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class RestaurantItemController {
    
    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    public RestaurantItemService restaurantItemService;

    @Autowired
    ServletContext context;

    @PostMapping("/restaurant/{restaurantId}/addRestaurantItem")
//    public ResponseEntity<RestaurantItems> addRestaurantItem(@PathVariable(value = "restaurantId") int restaurantId,
//    @RequestBody RestaurantItems restaurantItemRequest){
//        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
//            restaurantItemRequest.setRestaurant(restaurant);
//            RestaurantItems restaurantItems = restaurantItemService.save(restaurantItemRequest);
//
//          return new ResponseEntity<RestaurantItems>(restaurantItems,HttpStatus.CREATED);
//    }

    public ResponseEntity<RestaurantItems> addRestaurantItem(@RequestParam("imageLocation") MultipartFile imageLocation, @RequestParam("restaurantItem") String restaurantItem,@PathVariable(value = "restaurantId") int restaurantId) throws JsonMappingException, JsonProcessingException {
        RestaurantItems restaurantItemObject = new ObjectMapper().readValue(restaurantItem, RestaurantItems.class);
        boolean isExists = new File(context.getRealPath("/Images/")).exists();

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

        restaurantItemObject.setImageLocation(newFileName);
        restaurantItemObject.setRestaurant(restaurantService.findByRestaurantId(restaurantId));
        RestaurantItems restaurantItemRes = restaurantItemService.save(restaurantItemObject);
        if(restaurantItemRes != null){
            return new ResponseEntity<>(restaurantItemRes,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(restaurantItemRes,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/restaurant/{restaurantId}/restaurantItems")
    public ResponseEntity<List<RestaurantItems>> getAllRestaurantItemsByRestaurantId(@PathVariable(value = "restaurantId") int restaurantId) throws ResourceNotFoundException {

        List<RestaurantItems> restaurantItems = restaurantItemService.getAllRestaurantItemsByRestaurantId(restaurantId);
        return new ResponseEntity<>(restaurantItems, HttpStatus.OK);
    }
    
    @GetMapping("/restaurantItem/{restaurantItemId}")
    public ResponseEntity<RestaurantItems> getRestaurantItemById(@PathVariable(value = "restaurantItemId") int restaurantItemId) {

        RestaurantItems restaurantItem = restaurantItemService.findByRestaurantItemId(restaurantItemId);

        return new ResponseEntity<>(restaurantItem, HttpStatus.OK);
    }

//  @PutMapping("/restaurantItem/{restaurantItemId}")
//  public ResponseEntity<RestaurantItems> updateRestaurantItem(@PathVariable("restaurantItemId") int restaurantItemId, @RequestBody RestaurantItems restaurantItems) {
//    RestaurantItems getRestaurantItem = restaurantItemService.findByRestaurantItemId(restaurantItemId);
//
//    getRestaurantItem.setRestaurantItemName(restaurantItems.getRestaurantItemName());
//    getRestaurantItem.setImageLocation(restaurantItems.getImageLocation());
//    getRestaurantItem.setItemPrice(restaurantItems.getItemPrice());
//    Restaurant restaurant = restaurantItems.getRestaurant();
//    getRestaurantItem.setRestaurant(restaurant);
//
//    return new ResponseEntity<>(restaurantItemService.save(getRestaurantItem), HttpStatus.OK);
//  }

    @PutMapping("/restaurantItem/{restaurantItemId}")
    public ResponseEntity<RestaurantItems> updateRestaurant(@PathVariable("restaurantItemId") int restaurantItemId,@RequestParam("imageLocation") MultipartFile imageLocation,@RequestParam("restaurantItem") String restaurantItem) throws JsonMappingException, JsonProcessingException {
        RestaurantItems saveRestaurantItem = restaurantItemService.findByRestaurantItemId(restaurantItemId);

        RestaurantItems restaurantItemObject = new ObjectMapper().readValue(restaurantItem, RestaurantItems.class);
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

        restaurantItemObject.setImageLocation(newFileName);

        saveRestaurantItem.setRestaurantItemId(restaurantItemObject.getRestaurantItemId());
        saveRestaurantItem.setRestaurantItemName(restaurantItemObject.getRestaurantItemName());
        saveRestaurantItem.setImageLocation(restaurantItemObject.getImageLocation());
        saveRestaurantItem.setDescription(restaurantItemObject.getDescription());
        saveRestaurantItem.setItemPrice(restaurantItemObject.getItemPrice());
        saveRestaurantItem.setRestaurant(restaurantItemObject.getRestaurant());

        RestaurantItems restaurantRes = restaurantItemService.save(saveRestaurantItem);
        if(restaurantRes != null){
            return new ResponseEntity<>(restaurantRes,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(restaurantRes,HttpStatus.BAD_REQUEST);
        }
    }

  @DeleteMapping("/restaurantItem/{restaurantItemId}")
  public ResponseEntity<HttpStatus> deleteRestaurantItem(@PathVariable("restaurantItemId") int restaurantItemId) {
    restaurantItemService.deleteByRestaurantItemId(restaurantItemId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/restaurant/{restaurantId}/restaurantItems")
  public ResponseEntity<List<RestaurantItems>> deleteAllRestaurantItemsOfRestaurant(@PathVariable(value = "restaurantId") int restaurantId) throws ResourceNotFoundException {
    if (!restaurantService.existsByRestaurantId(restaurantId)) {
      throw new ResourceNotFoundException("Not found Tutorial with id = " + restaurantId);
    }

    restaurantItemService.deleteAllRestaurantItemsByRestaurantId(restaurantId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
