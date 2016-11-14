package com.pet.supplies.admin.repository;

import com.pet.supplies.common.domain.AdminOrSeller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrSellerRepository extends CrudRepository<AdminOrSeller, Long>
{
   @Query("from AdminOrSeller adminOrSeller where adminOrSeller.id=:id")
   public AdminOrSeller findOne(@Param("id") Long id);

}
