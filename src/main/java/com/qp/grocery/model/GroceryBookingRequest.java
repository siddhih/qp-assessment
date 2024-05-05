package com.qp.grocery.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryBookingRequest {
    private int id;
    private int userId;
    private List<Grocery> groceries;
}
