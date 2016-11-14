package com.pet.supplies.admin.rest.controllers;

import com.pet.supplies.admin.service.CategoryMgtService;
import com.pet.supplies.common.model.CategoryModel;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
@RequestMapping("/category")
@CrossOrigin(origins = "${app.allow.origin}")
public class CategoryMgtController
{
   Logger logger = Logger.getLogger(CategoryMgtController.class);

   @Setter
   @Autowired
   private CategoryMgtService categoryMgtService;

   @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CategoryModel> saveCategory(@RequestBody CategoryModel model)
   {
      logger.info("CategoryMgtController.saveCategory called");
      if (model == null)
      {
         return new ResponseEntity<CategoryModel>(HttpStatus.NOT_FOUND);
      }
      CategoryModel categoryModel = categoryMgtService.saveCategory(model);

      return new ResponseEntity<CategoryModel>(categoryModel, HttpStatus.OK);
   }

   @RequestMapping(value = "/load/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<CategoryModel>> loadCategoryByAdmOrSellerId(@PathVariable Long id)
   {
      logger.info("CategoryMgtController.loadCategoryByAdmOrSellerId called");
      if (id == null)
      {
         return new ResponseEntity<List<CategoryModel>>(HttpStatus.NOT_FOUND);
      }
      List<CategoryModel> categoryModels = categoryMgtService.findCategoriesByAdmOrSellerId(id);

      return new ResponseEntity<List<CategoryModel>>(categoryModels, HttpStatus.OK);
   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> deleteCategory(@RequestBody CategoryModel model)
   {
      logger.info("CategoryMgtController.deleteCategory called");
      categoryMgtService.deleteCategory(model);
      return new ResponseEntity<String>(HttpStatus.OK);
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CategoryModel> updateCategory(@RequestBody CategoryModel model)
   {
      logger.info("CategoryMgtController.updateCategory called");
      if (model == null)
      {
         return new ResponseEntity<CategoryModel>(HttpStatus.NOT_FOUND);
      }
      CategoryModel categoryModel = categoryMgtService.updateCategory(model);

      return new ResponseEntity<CategoryModel>(categoryModel, HttpStatus.OK);
   }
}
