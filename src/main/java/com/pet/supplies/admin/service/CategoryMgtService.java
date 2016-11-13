package com.pet.supplies.admin.service;

import java.util.List;

import com.pet.supplies.common.model.CategoryModel;

public interface CategoryMgtService {

	CategoryModel saveCategory(CategoryModel model);

	List<CategoryModel> findCategoriesByAdmOrSellerId(Long id);

	void deleteCategory(CategoryModel model);

	CategoryModel updateCategory(CategoryModel model);

}
