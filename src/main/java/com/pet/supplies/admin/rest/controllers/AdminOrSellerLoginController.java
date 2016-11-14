package com.pet.supplies.admin.rest.controllers;

import com.pet.supplies.common.model.AdminOrSellerModel;

import com.pet.supplies.admin.service.AdminOrSellerLoginService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ???? TODO Similar to User login, Should be refactor later and move to common module
 * 
 * @version $Id:$
 * @author njanjyal //I have removed copyrights
 */
@Controller
@EnableAutoConfiguration
@CrossOrigin(origins = "${app.allow.origin}")
@RequestMapping("/admin")
public class AdminOrSellerLoginController
{
   Logger logger = Logger.getLogger(AdminOrSellerLoginController.class);

   @Setter
   @Autowired
   private AdminOrSellerLoginService loginService;

   /**
    * @param model
    * @return
    */
   @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<AdminOrSellerModel> login(@RequestBody AdminOrSellerModel model)
   {
      logger.info("AdminOrSellerLoginController.login called");
      AdminOrSellerModel userModel = null;
      if (model == null)
      {
         return new ResponseEntity<AdminOrSellerModel>(model, HttpStatus.NOT_FOUND);
      }
      if (model.isRegister())
      {
         userModel = loginService.registerNewAdminOrSellerUser(model);
      }
      else
      {
         userModel = loginService.validateAdminOrSellerLogin(model);
      }
      if (userModel.isPresent())
      {
         return new ResponseEntity<AdminOrSellerModel>(userModel, HttpStatus.FOUND);
      }
      if (userModel != null && !userModel.isActive())
      {
         return new ResponseEntity<AdminOrSellerModel>(model, HttpStatus.UNAUTHORIZED);
      }
      return new ResponseEntity<AdminOrSellerModel>(userModel, HttpStatus.OK);

   }
}
