package com.qp.grocery.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "GROCERY_LIST")
    private String groceryList;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
}
