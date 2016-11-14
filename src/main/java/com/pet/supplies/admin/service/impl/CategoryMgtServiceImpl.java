package com.pet.supplies.admin.service.impl;

import java.util.List;
import javax.transaction.Transactional;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.supplies.admin.repository.AdminOrSellerRepository;
import com.pet.supplies.admin.repository.CategoryRepository;
import com.pet.supplies.admin.service.CategoryMgtService;
import com.pet.supplies.common.constants.ErrorMessages;
import com.pet.supplies.common.domain.AdminOrSeller;
import com.pet.supplies.common.domain.Category;
import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.common.mapper.ModelToEntityMapper;
import com.pet.supplies.common.model.CategoryModel;
import com.pet.supplies.exception.FunctionalRunTimeException;

@Service
@Transactional
public class CategoryMgtServiceImpl implements CategoryMgtService
{

   @Setter
   @Autowired
   private CategoryRepository categoryRepository;

   @Setter
   @Autowired
   private AdminOrSellerRepository adminOrSellerRepository;

   @Override
   public CategoryModel saveCategory(CategoryModel model)
   {
      Category newCategory = null;
      CategoryModel categoryModel = null;
      if (model != null)
      {
         if (getCategoryByCategoryCode(model.getCode()) != null)
         {
            new FunctionalRunTimeException(ErrorMessages.CATEGORY_EXISTS);
         }

         Category category = ModelToEntityMapper.mapCategoryModelToCategoryEntity(model);
         newCategory = categoryRepository.save(category);
         categoryModel = EntityToModelMapper.mapCategoryEntityToCategoryModel(newCategory);
      }

      return categoryModel;
   }

   public Category getCategoryByCategoryCode(String category)
   {
      return categoryRepository.findOneByCode(category);
   }

   public AdminOrSeller findAdminOrSellerById(Long sellerId)
   {
      return adminOrSellerRepository.findOne(sellerId);
   }

   public List<CategoryModel> findCategoriesByAdmOrSellerId(Long id)
   {
      List<Category> categories = categoryRepository.findCategoriesByAdmOrSellerId(id);
      List<CategoryModel> categoryModels = EntityToModelMapper.mapCategoryEntityToCategoryModel(categories);
      return categoryModels;
   }

   @Override
   public void deleteCategory(CategoryModel model)
   {
      try
      {
         if (model != null)
         {
            Category category = categoryRepository.findOneByCode(model.getCode());
            categoryRepository.delete(category);
         }
      }
      catch (Exception exception)
      {
         new FunctionalRunTimeException(ErrorMessages.CATEGORY_DELETE);
      }
   }

   @Override
   public CategoryModel updateCategory(CategoryModel model)
   {
      CategoryModel categoryModel = new CategoryModel();
      if (model != null)
      {
         Category category = categoryRepository.findOneByCode(model.getCode());
         category.setName(model.getName());
         category.setCode(model.getCode());
         Category updatedCategory = categoryRepository.save(category);
         categoryModel = EntityToModelMapper.mapCategoryEntityToCategoryModel(updatedCategory);
      }
      return categoryModel;
   }

}
