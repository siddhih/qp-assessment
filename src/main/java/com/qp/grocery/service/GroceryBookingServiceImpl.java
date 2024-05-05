package com.qp.grocery.service;

import com.qp.grocery.dao.GroceryBookingRepository;
import com.qp.grocery.model.BatchHandleGroceryRequest;
import com.qp.grocery.model.BatchHandleGroceryResponse;
import com.qp.grocery.model.Grocery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GroceryBookingServiceImpl implements GroceryBookingService {

    @Autowired
    GroceryBookingRepository groceryBookingRepository;

    /**Adding groceries to the inventory*/
    @Override
    public BatchHandleGroceryResponse addGroceries(BatchHandleGroceryRequest batchHandleGroceryRequest) {
        BatchHandleGroceryResponse batchHandleGroceryResponse = new BatchHandleGroceryResponse();
        try{
            batchHandleGroceryResponse.setId(batchHandleGroceryRequest.getId());
            batchHandleGroceryRequest.getGroceryItems().forEach(grocery -> {
                Date d= new Date();
                grocery.setCreatedOn(d);
                grocery.setModifiedOn(d);
            });
            batchHandleGroceryResponse.setGroceryItems(groceryBookingRepository.
                    saveAll(batchHandleGroceryRequest.getGroceryItems()));
            batchHandleGroceryResponse.setStatus("SUCCESS");
            batchHandleGroceryResponse.setMessage("BATCH INSERT SUCCESSFUL");
        }catch (Exception ex){
            log.error("Exception in addGroceries :: "+ex);
            batchHandleGroceryResponse.setStatus("FAILED");
            batchHandleGroceryResponse.setMessage("INSERT FAILED :: "+ex.getMessage());
        }
        return batchHandleGroceryResponse;
    }

    /**Updating groceries in the inventory*/
    @Override
    public Grocery updateGroceryItems(int id, Grocery grocery) {
        Optional<Grocery> grocery1 = groceryBookingRepository.findById(id);
        if(grocery1.isPresent()){
            Grocery g = grocery1.get();
            if(grocery.getPrice()!=0.0) {
                g.setPrice(grocery.getPrice());
            }
            if(grocery.getQuantity() != 0) {
                g.setQuantity(grocery.getQuantity());
            }
            g.setModifiedBy(grocery.getModifiedBy());
            groceryBookingRepository.save(g);
            return g;
        }else{
            grocery.setCreatedOn(new Date());
            return groceryBookingRepository.save(grocery);
        }
    }

    /**Delete groceries from the inventory*/
    @Override
    public void deleteGroceryItems(int groceryItem) {
        groceryBookingRepository.deleteById(groceryItem);
    }

    /**Fetch inventory*/
    @Override
    public List<Grocery> getAllGroceryItems() {
        return groceryBookingRepository.findAll();
    }

    @Override
    public Grocery getGroceryItem(int groceryItem) {
        return groceryBookingRepository.findById(groceryItem).get();
    }

    @Override
    public Grocery inventoryManagement(int id, Grocery grocery, String operation) {
        Optional<Grocery> grocery1 = groceryBookingRepository.findById(id);
        if(grocery1.isPresent()){
            grocery = grocery1.get();
            int quantity = grocery1.get().getQuantity();
            if(operation.equalsIgnoreCase("ADD")) {
                grocery.setQuantity(grocery.getQuantity() + quantity);
            }else if(operation.equalsIgnoreCase("SUB")){
                grocery.setQuantity(grocery.getQuantity() - quantity);
            }
            grocery.setModifiedBy(grocery.getModifiedBy());
            groceryBookingRepository.save(grocery);
            return grocery;
        }else{
            Date d = new Date();
            grocery.setCreatedOn(d);
            grocery.setModifiedOn(d);
            return groceryBookingRepository.save(grocery);
        }
    }
}
