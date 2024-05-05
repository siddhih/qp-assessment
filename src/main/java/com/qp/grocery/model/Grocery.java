package com.qp.grocery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
@Table(name = "GROCERY_INVENTORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grocery {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "GROCERY_ITEM")
    private String name;
    @Column(name = "PRICE")
    @Nullable
    private double price;
    @Column(name = "QUANTITY")
    @Nullable
    private int quantity;
    @Column(name = "CREATED_ON")
    private Date createdOn;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
}
