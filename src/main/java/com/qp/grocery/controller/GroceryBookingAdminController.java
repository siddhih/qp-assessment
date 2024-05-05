package com.qp.grocery.controller;

import com.qp.grocery.model.BatchHandleGroceryRequest;
import com.qp.grocery.model.BatchHandleGroceryResponse;
import com.qp.grocery.model.Grocery;
import com.qp.grocery.service.GroceryBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/grocery")
public class GroceryBookingAdminController {

    @Autowired
    GroceryBookingService groceryBookingService;

    @PostMapping(value = "/add")
    public BatchHandleGroceryResponse addGroceries(@RequestBody BatchHandleGroceryRequest batchGroceryDto){
        return groceryBookingService.addGroceries(batchGroceryDto);
    }

    @PostMapping(value = "/update/{id}")
    public Grocery updateGroceryItems(@PathVariable int id,@RequestBody Grocery grocery){
        return groceryBookingService.updateGroceryItems(id, grocery);
    }

    @PostMapping(value = "/delete/{id}")
    public void deleteGroceryItem(@PathVariable int id){
        groceryBookingService.deleteGroceryItems(id);
    }

    @GetMapping(value = "/getAll")
    public List<Grocery> getAllGroceryItems(){
        return groceryBookingService.getAllGroceryItems();
    }

    @GetMapping(value = "/get/{id}")
    public Grocery getGroceryItem(@PathVariable int id){
        return groceryBookingService.getGroceryItem(id);
    }

    @PostMapping(value = "/inventory/manage/{id}/{operation}")
    public Grocery inventoryManagement(@PathVariable int id,@PathVariable String operation, @RequestBody Grocery grocery){
        return groceryBookingService.inventoryManagement(id, grocery,operation);
    }
}
