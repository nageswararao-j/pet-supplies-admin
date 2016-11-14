package com.pet.supplies.admin.rest.controllers;

import java.util.Set;

import com.pet.supplies.admin.service.UserService;
import com.pet.supplies.common.model.UserModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@CrossOrigin(origins = "${app.allow.origin}")
public class UserMgtController
{
   Logger logger = Logger.getLogger(UserMgtController.class);

   @Setter
   @Autowired
   private UserService userService;

   @RequestMapping(value = "/load", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Set<UserModel>> loadUsers()
   {
      logger.info("UserMgtController.loadUsers called");
      Set<UserModel> userModels = userService.findUsers();
      return new ResponseEntity<Set<UserModel>>(userModels, HttpStatus.OK);
   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> deleteUser(@RequestBody UserModel model)
   {
      logger.info("UserMgtController.deleteUser called");
      userService.deleteUser(model);
      return new ResponseEntity<String>(HttpStatus.OK);
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<UserModel> updateUser(@RequestBody UserModel model)
   {
      logger.info("UserMgtController.updateUser called");
      UserModel userModel = userService.updateUser(model);
      return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
   }
}
