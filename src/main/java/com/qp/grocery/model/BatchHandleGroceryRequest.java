package com.qp.grocery.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.json.GsonJsonParser;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchHandleGroceryRequest {

    private int id;
    private List<Grocery> groceryItems;
}


