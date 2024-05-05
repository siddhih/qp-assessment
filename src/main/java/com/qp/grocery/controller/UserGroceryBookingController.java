package com.qp.grocery.controller;

import com.qp.grocery.model.Grocery;
import com.qp.grocery.model.GroceryBookingRequest;
import com.qp.grocery.model.GroceryBookingResponse;
import com.qp.grocery.service.UserGroceryBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/grocery")
public class UserGroceryBookingController {

    @Autowired
    UserGroceryBookingService userGroceryBookingService;

    @GetMapping(value = "/available")
    public List<Grocery> getAllGroceryItems(){
        return userGroceryBookingService.getAvailableGroceryItems();
    }

    @PostMapping(value = "/book")
    public GroceryBookingResponse bookGrocery(@RequestBody GroceryBookingRequest groceryBookingRequest){
        return userGroceryBookingService.bookGrocery(groceryBookingRequest);
    }
}
