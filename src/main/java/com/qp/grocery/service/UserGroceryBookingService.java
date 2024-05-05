package com.qp.grocery.service;

import com.qp.grocery.model.Grocery;
import com.qp.grocery.model.GroceryBookingRequest;
import com.qp.grocery.model.GroceryBookingResponse;

import java.util.List;

public interface UserGroceryBookingService{
    List<Grocery> getAvailableGroceryItems();

    GroceryBookingResponse bookGrocery(GroceryBookingRequest groceryBookingRequest);
}
