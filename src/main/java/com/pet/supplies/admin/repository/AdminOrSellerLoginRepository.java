package com.pet.supplies.admin.repository;

import com.pet.supplies.common.domain.AdminOrSeller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrSellerLoginRepository extends CrudRepository<AdminOrSeller, Long>
{

   @Query("from AdminOrSeller admOrSeller where admOrSeller.emailId=:emailId and admOrSeller.password=:password")
   public AdminOrSeller authenticateAdminOrSeller(@Param("emailId") String emailId, @Param("password") String password);

   @Query("select admOrSeller.emailId from AdminOrSeller admOrSeller where admOrSeller.emailId=:emailId")
   public String isEmailRegistered(@Param("emailId") String emailId);

}
