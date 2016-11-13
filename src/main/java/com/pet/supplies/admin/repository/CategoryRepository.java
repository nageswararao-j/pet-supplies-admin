package com.pet.supplies.admin.repository;


import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.pet.supplies.common.domain.Category;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>
{

   @SuppressWarnings("unchecked")
   Category save(Category category);

   @Query("from Category cat where cat.code=:code")
   Category findOneByCode(@Param("code") String code);

   @Query("from Category cat where cat.sellerId=:id")
   List<Category> findCategoriesByAdmOrSellerId(@Param("id") Long id);

}
