package com.qp.grocery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchHandleGroceryResponse {
    private int id;
    private List<Grocery> groceryItems;
    private String status;
    private String message;
}
