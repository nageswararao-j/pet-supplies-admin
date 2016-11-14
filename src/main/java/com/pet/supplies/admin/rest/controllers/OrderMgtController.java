package com.pet.supplies.admin.rest.controllers;

import org.springframework.web.bind.annotation.RequestBody;

import com.pet.supplies.common.model.OrdersModel;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pet.supplies.admin.service.OrderMgtService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/order")
@CrossOrigin(origins = "${app.allow.origin}")
public class OrderMgtController
{
   Logger logger = Logger.getLogger(OrderMgtController.class);

   @Setter
   @Autowired
   private OrderMgtService orderMgtService;

   @RequestMapping(value = "/load/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<OrdersModel>> findOrdersByAdmOrSeller(@PathVariable Long id)
   {
      logger.info("OrderMgtController.findOrdersByAdmOrSeller called");
      if (id == null)
      {
         return new ResponseEntity<List<OrdersModel>>(HttpStatus.NOT_FOUND);
      }
      List<OrdersModel> orders = orderMgtService.findOrdersByAdmOrSellerId(id);

      return new ResponseEntity<List<OrdersModel>>(orders, HttpStatus.OK);
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<OrdersModel> updateOrder(@RequestBody OrdersModel model)
   {
      logger.info("OrderMgtController.updateOrder called");
      if (model == null)
      {
         return new ResponseEntity<OrdersModel>(HttpStatus.NOT_FOUND);
      }
      OrdersModel ordersModel = orderMgtService.updateOrder(model);

      return new ResponseEntity<OrdersModel>(ordersModel, HttpStatus.OK);
   }
}
