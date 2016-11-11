package com.pet.supplies.admin.service.impl;

import com.pet.supplies.common.model.AdminOrSellerModel;

import com.pet.supplies.common.domain.AdminOrSeller;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.pet.supplies.common.domain.Address;
import com.pet.supplies.common.mapper.ModelToEntityMapper;
import com.pet.supplies.common.helper.LoginHelper;
import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.admin.repository.AdminOrSellerRepository;
import com.pet.supplies.admin.repository.AdminOrSellerLoginRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import com.pet.supplies.admin.service.AdminOrSellerLoginService;

@Service
@Transactional
public class AdminOrSellerLoginServiceImpl implements AdminOrSellerLoginService
{

   @Autowired
   @Setter
   private AdminOrSellerLoginRepository loginRepository;

   @Autowired
   @Setter
   private AdminOrSellerRepository admOrSellerRepository;

   /**
    * TODO Should be refactor to achieve commons implementation for user and admin
    */
   @Override
   public AdminOrSellerModel registerNewAdminOrSellerUser(AdminOrSellerModel model)
   {
      AdminOrSeller admOrSeller = null;
      AdminOrSellerModel admOrSellerModel = new AdminOrSellerModel();

      if (model != null)
      {
         if (isAdmOrSellerExists(model))
         {
            admOrSellerModel.setPresent(true);
            admOrSellerModel.setActive(true);
            return admOrSellerModel;
         }
         admOrSeller = ModelToEntityMapper.prepateSellerEntity(model);

         AdminOrSeller newAdmSeller = admOrSellerRepository.save(admOrSeller);
         Address address = ModelToEntityMapper.mapAddressModelToAddressEntity(model.getAddress());
         address.setSeller(newAdmSeller);
         newAdmSeller.setAddress(address);
         admOrSellerModel.setId(newAdmSeller.getId());
         admOrSellerModel = EntityToModelMapper.mapAdminOrSellerEntityToAdminOrSellerModel(newAdmSeller);
         admOrSellerModel.setAddress(EntityToModelMapper.mapAddressEntityToAddressModel(newAdmSeller.getAddress()));
      }
      return admOrSellerModel;
   }

   private boolean isAdmOrSellerExists(AdminOrSellerModel model)
   {

      return loginRepository.isEmailRegistered(model.getEmailId()) != null ? true : false;
   }

   /**
    * 
    */
   @Override
   public AdminOrSellerModel validateAdminOrSellerLogin(AdminOrSellerModel model)
   {
      AdminOrSeller admOrSeller = null;
      AdminOrSellerModel authModel = new AdminOrSellerModel();
      if (LoginHelper.isEmailAndPwdNotNulls(model))
      {
         admOrSeller = loginRepository.authenticateAdminOrSeller(model.getEmailId(), model.getPassword());
         if (admOrSeller != null)
         {
            authModel = EntityToModelMapper.mapAdminOrSellerEntityToAdminOrSellerModel(admOrSeller);
         }
      }
      return authModel;
   }

}
