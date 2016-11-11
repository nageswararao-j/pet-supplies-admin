package com.pet.supplies.admin.service;

import com.pet.supplies.common.model.AdminOrSellerModel;

public interface AdminOrSellerLoginService
{

   public AdminOrSellerModel registerNewAdminOrSellerUser(AdminOrSellerModel model);

   public AdminOrSellerModel validateAdminOrSellerLogin(AdminOrSellerModel model);

}
