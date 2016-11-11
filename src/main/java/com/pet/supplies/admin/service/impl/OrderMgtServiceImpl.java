package com.pet.supplies.admin.service.impl;

import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.common.domain.Orders;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import java.util.Set;
import com.pet.supplies.common.domain.Product;
import com.pet.supplies.admin.repository.ProductRepository;
import com.pet.supplies.admin.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Setter;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.pet.supplies.common.model.OrdersModel;
import java.util.List;
import com.pet.supplies.admin.service.OrderMgtService;

@Service
@Transactional
public class OrderMgtServiceImpl implements OrderMgtService
{

   @Setter
   @Autowired
   private OrdersRepository orderRepository;

   @Setter
   @Autowired
   private ProductRepository productRepository;

   @Override
   public List<OrdersModel> findOrdersByAdmOrSellerId(Long id)
   {
      Set<Product> products = productRepository.findAllProductsBySellerId(id);
      List<OrdersModel> orderModels = new ArrayList<OrdersModel>();
      if (CollectionUtils.isNotEmpty(products))
      {
         products.forEach(product -> {
            Orders order = orderRepository.findOrdersByProductId(product.getId());
            orderModels.add(EntityToModelMapper.mapOrderEntityToOrderModel(order));
         });
      }
      return orderModels;
   }

   @Override
   public OrdersModel updateOrder(OrdersModel model)
   {
      OrdersModel ordersModel = new OrdersModel();
      if (model != null)
      {
         Orders order = orderRepository.findOrdersByProductId(model.getProductId());
         order.setStatus(model.getStatus());
         Orders updatedOrders = orderRepository.save(order);
         ordersModel = EntityToModelMapper.mapOrderEntityToOrderModel(updatedOrders);
      }
      return ordersModel;
   }

}
