package com.pet.supplies.admin.service;

import com.pet.supplies.common.model.OrdersModel;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface OrderMgtService
{

   List<OrdersModel> findOrdersByAdmOrSellerId(Long id);

   OrdersModel updateOrder(OrdersModel model);

}
