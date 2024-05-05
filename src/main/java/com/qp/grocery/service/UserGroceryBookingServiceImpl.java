package com.qp.grocery.service;

import com.google.gson.Gson;
import com.qp.grocery.dao.GroceryBookingRepository;
import com.qp.grocery.dao.OrderDetailsRepository;
import com.qp.grocery.model.Grocery;
import com.qp.grocery.model.GroceryBookingRequest;
import com.qp.grocery.model.GroceryBookingResponse;
import com.qp.grocery.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserGroceryBookingServiceImpl implements UserGroceryBookingService{

    @Autowired
    GroceryBookingRepository groceryBookingRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<Grocery> getAvailableGroceryItems() {
        return groceryBookingRepository.findAvailableGroceries();
    }

    /**Method to book groceries
     * Multiple grocery items could be booked*/
    @Override
    public GroceryBookingResponse bookGrocery(GroceryBookingRequest groceryBookingRequest) {
        GroceryBookingResponse groceryBookingResponse = new GroceryBookingResponse();
        StringBuilder responseString = new StringBuilder();
        groceryBookingResponse.setId(groceryBookingRequest.getId());
        try{
            List<Grocery> groceries = getAvailableGroceryItems();
            AtomicReference<Double> totalPrice= new AtomicReference<>(0.0);

            List<Grocery> responseGroceryList = getResponseGroceryList(groceryBookingRequest, responseString, groceries, totalPrice);

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setUserId(groceryBookingRequest.getUserId());
            Gson gson = new Gson();
            String grocerList = gson.toJson(responseGroceryList);
            orderDetails.setGroceryList(grocerList);
            orderDetails.setTotalPrice(totalPrice.get());
            orderDetails = orderDetailsRepository.save(orderDetails);

            groceryBookingResponse.setOrderDetails(orderDetails);
            groceryBookingResponse.setStatus("SUCCESS");
            responseString.append("Booking confirmed...You will be navigated to the Payment window");
        }catch (Exception ex){
            groceryBookingResponse.setStatus("FAILED");
            responseString.append("Booking Failed!!! Please try again");
        }
        groceryBookingResponse.setMessage(responseString.toString());
        return groceryBookingResponse;
    }

    private List<Grocery> getResponseGroceryList(GroceryBookingRequest groceryBookingRequest, StringBuilder responseString, List<Grocery> groceries, AtomicReference<Double> totalPrice) {
        List<Grocery> responseGroceryList = new ArrayList<>();
        groceryBookingRequest.getGroceries().stream().forEach(reqGrocery -> {
            Grocery availableGrocery = groceries.stream().filter(grocery1 -> grocery1.getName().
                    equalsIgnoreCase(reqGrocery.getName())).findFirst().get();
            if(reqGrocery.getQuantity()<availableGrocery.getQuantity()){
                availableGrocery.setQuantity(availableGrocery.getQuantity()-reqGrocery.getQuantity());
                double price = availableGrocery.getPrice() * reqGrocery.getQuantity();
                reqGrocery.setPrice(price);
                totalPrice.updateAndGet(v -> new Double((double) (v + price)));
                groceryBookingRepository.save(availableGrocery);
            }else{
                reqGrocery.setPrice(0.0);
                reqGrocery.setQuantity(0);
                responseString.append(reqGrocery.getName() + " not available... ");
            }

            responseGroceryList.add(reqGrocery);
        });
        return responseGroceryList;
    }

}
