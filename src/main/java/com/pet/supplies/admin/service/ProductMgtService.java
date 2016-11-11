package com.pet.supplies.admin.service;

import java.util.Set;
import com.pet.supplies.common.model.ProductModel;

public interface ProductMgtService
{

   ProductModel saveProduct(ProductModel model);

   Set<ProductModel> findProductsByAdmOrSellerId(Long id);

   void deleteProduct(ProductModel model);

}
