package com.qp.grocery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryBookingResponse {
    private int id;
    private OrderDetails orderDetails;
    private String status;
    private String message;
}
