package com.pet.supplies.admin.service.impl;


import com.pet.supplies.exception.FunctionalRunTimeException;

import com.pet.supplies.common.constants.ErrorMessages;
import com.pet.supplies.admin.repository.CategoryRepository;
import com.pet.supplies.common.domain.Category;
import java.util.HashSet;
import com.pet.supplies.admin.repository.ImageRepository;
import org.apache.commons.collections.CollectionUtils;
import com.pet.supplies.common.domain.Image;
import java.util.Set;
import com.pet.supplies.admin.repository.AdminOrSellerRepository;
import com.pet.supplies.common.domain.AdminOrSeller;
import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.common.mapper.ModelToEntityMapper;
import com.pet.supplies.common.domain.Product;
import com.pet.supplies.admin.repository.ProductRepository;
import lombok.Setter;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pet.supplies.common.model.ProductModel;
import com.pet.supplies.admin.service.ProductMgtService;

@Service
@Transactional
public class ProductMgtServiceImpl implements ProductMgtService
{
   @Setter
   @Autowired
   private ProductRepository productRepository;
   
   @Setter
   @Autowired
   private CategoryRepository categoryRepository;

   @Setter
   @Autowired
   private AdminOrSellerRepository adminOrSellerRepository;

   @Setter
   @Autowired
   private ImageRepository imageRepository;

   @Override
   public ProductModel saveProduct(ProductModel model)
   {
      Product newProduct = null;
      ProductModel productModel = null;
      Set<Image> newImages = new HashSet<Image>();
      if (model != null)
      {
         Category category = getCategoryByCategoryCode(model.getCategoryCode());
         Product product = ModelToEntityMapper.mapProductModelToProductEntity(model);
         product.setCategory(category);
         AdminOrSeller admOrSeller = findAdminOrSellerById(model.getSellerId());
         product.setSeller(admOrSeller);
         product.setImageUrls(newImages);
         newProduct = productRepository.save(product);
         Set<Image> images = ModelToEntityMapper.mapImageModelsToImageEntities(model.getImageUrls(),newProduct);
         if (CollectionUtils.isNotEmpty(images))
         {
            images.forEach(image -> {
               newImages.add(imageRepository.save(image));
            });
         }
         productModel = EntityToModelMapper.mapProductToProductModel(newProduct);
         productModel.setCategoryCode(category.getCode());
      }

      return productModel;
   }

   public Category getCategoryByCategoryCode(String category)
   {
      return categoryRepository.findOneByCode(category);
   }

   public AdminOrSeller findAdminOrSellerById(Long sellerId)
   {
      return adminOrSellerRepository.findOne(sellerId);
   }

   @Override
   public Set<ProductModel> findProductsByAdmOrSellerId(Long id)
   {
      Set<Product> products = productRepository.findAllProductsBySellerId(id);
      Set<ProductModel> productModels = EntityToModelMapper.mapProductEntityToProductModel(products);
      return productModels;
   }
   
   @Override
   public void deleteProduct(ProductModel model)
   {
      try{
         if(model != null){
            Product product = ModelToEntityMapper.mapProductModelToProductEntity(model);
            Category category = categoryRepository.findOneByCode(model.getCategoryCode());
            AdminOrSeller seller = adminOrSellerRepository.findOne(model.getSellerId());
            product.setId(model.getProductId());
            product.setCategory(category);
            product.setSeller(seller);
            if(CollectionUtils.isNotEmpty(product.getImageUrls())){
               product.getImageUrls().forEach(image->{
                  imageRepository.delete(image);
               });
            }
            productRepository.delete(product);
         }
      }catch(Exception exception){
         new FunctionalRunTimeException(ErrorMessages.PRODUCT_DELETE);
      }
   }

}
