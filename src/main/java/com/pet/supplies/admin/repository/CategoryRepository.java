package com.pet.supplies.admin.repository;


import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Query;
import com.pet.supplies.common.domain.Category;
import com.pet.supplies.common.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>
{

   @SuppressWarnings("unchecked")
   Category save(Category category);

   @Query("from Category cat where cat.code=:code")
   Category findOneByCode(@Param("code") String code);

}
