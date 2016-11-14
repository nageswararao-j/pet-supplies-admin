package com.pet.supplies.admin.rest.controllers;

import java.util.Set;

import com.pet.supplies.admin.service.ProductMgtService;
import org.springframework.web.bind.annotation.RequestBody;
import com.pet.supplies.common.model.ProductModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/product")
@CrossOrigin(origins = "${app.allow.origin}")
public class ProductMgtController
{
   Logger logger = Logger.getLogger(ProductMgtController.class);

   @Setter
   @Autowired
   private ProductMgtService prodcutMgtService;

   @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModel model)
   {
      logger.info("ProductMgtController.saveProduct called");
      if (model == null)
      {
         return new ResponseEntity<ProductModel>(HttpStatus.NOT_FOUND);
      }
      ProductModel productModel = prodcutMgtService.saveProduct(model);

      return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
   }

   @RequestMapping(value = "/load/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Set<ProductModel>> loadProductsByAdmOrSellerId(@PathVariable Long id)
   {
      logger.info("ProductMgtController.loadProductsByAdmOrSellerId called");
      if (id == null)
      {
         return new ResponseEntity<Set<ProductModel>>(HttpStatus.NOT_FOUND);
      }
      Set<ProductModel> productModels = prodcutMgtService.findProductsByAdmOrSellerId(id);

      return new ResponseEntity<Set<ProductModel>>(productModels, HttpStatus.OK);
   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> deleteProduct(@RequestBody ProductModel model)
   {
      logger.info("ProductMgtController.deleteProduct called");
      prodcutMgtService.deleteProduct(model);
      return new ResponseEntity<String>(HttpStatus.OK);
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel model)
   {
      logger.info("ProductMgtController.updateProduct called");
      if (model == null)
      {
         return new ResponseEntity<ProductModel>(HttpStatus.NOT_FOUND);
      }
      ProductModel productModel = prodcutMgtService.updateProduct(model);

      return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
   }
}
