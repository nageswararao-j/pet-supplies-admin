package com.pet.supplies.admin.rest.controllers;

import java.util.Set;

import com.pet.supplies.admin.service.ImageService;
import com.pet.supplies.common.model.ImageModel;

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
@RequestMapping("/image")
@CrossOrigin(origins = "${app.allow.origin}")
public class ImageMgtController
{
   Logger logger = Logger.getLogger(ImageMgtController.class);

   @Setter
   @Autowired
   private ImageService imageService;

   @RequestMapping(value = "/load", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Set<ImageModel>> loadImages()
   {
      logger.info("ImageMgtController.loadImages called");
      Set<ImageModel> userModels = imageService.findImages();
      return new ResponseEntity<Set<ImageModel>>(userModels, HttpStatus.OK);
   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> deleteImage(@RequestBody ImageModel model)
   {
      logger.info("ImageMgtController.deleteImage called");
      imageService.deleteImage(model);
      return new ResponseEntity<String>(HttpStatus.OK);
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ImageModel> updateImage(@RequestBody ImageModel model)
   {
      logger.info("ImageMgtController.updateImage called");
      ImageModel imageModel = imageService.updateImage(model);
      return new ResponseEntity<ImageModel>(imageModel, HttpStatus.OK);
   }
}
