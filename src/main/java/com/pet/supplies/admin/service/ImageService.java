package com.pet.supplies.admin.service;

import java.util.Set;

import com.pet.supplies.common.model.ImageModel;

public interface ImageService {

	Set<ImageModel> findImages();

	void deleteImage(ImageModel model);

	ImageModel updateImage(ImageModel model);

}
