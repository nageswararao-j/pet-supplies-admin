package com.pet.supplies.admin.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import com.pet.supplies.common.domain.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{

   @Query("from Orders orders where orders.productId=:productId")
   Orders findOrdersByProductId(@Param("productId") Long productId);

}
