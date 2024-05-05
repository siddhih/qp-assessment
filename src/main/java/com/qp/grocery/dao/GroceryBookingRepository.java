package com.qp.grocery.dao;

import com.qp.grocery.model.Grocery;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GroceryBookingRepository extends JpaRepository<Grocery,Integer> {

    void deleteByName(String groceryItem);

    Grocery findByName(String groceryItem);

    @Modifying
    @Query(value = "SELECT * FROM GROCERY_INVENTORY g WHERE g.QUANTITY > 0", nativeQuery = true)
    List<Grocery> findAvailableGroceries();

}
