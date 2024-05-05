package com.qp.grocery.service;

import com.qp.grocery.model.BatchHandleGroceryRequest;
import com.qp.grocery.model.BatchHandleGroceryResponse;
import com.qp.grocery.model.Grocery;

import java.util.List;

public interface GroceryBookingService {


    BatchHandleGroceryResponse addGroceries(BatchHandleGroceryRequest batchGroceryDto);

    Grocery updateGroceryItems(int id, Grocery grocery);

    void deleteGroceryItems(int groceryItem);

    List<Grocery> getAllGroceryItems();

    Grocery getGroceryItem(int groceryItem);

    Grocery inventoryManagement(int id, Grocery grocery, String operation);
}
