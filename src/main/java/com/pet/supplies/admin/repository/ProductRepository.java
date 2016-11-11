package com.pet.supplies.admin.repository;


import java.util.Set;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.pet.supplies.common.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>
{

   @Query("select prod from Product prod inner join prod.seller sell where sell.id=:sellerId")
   Set<Product> findAllProductsBySellerId(@Param("sellerId") Long sellerId);

}
