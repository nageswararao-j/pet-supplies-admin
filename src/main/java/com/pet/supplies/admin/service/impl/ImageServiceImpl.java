package com.pet.supplies.admin.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.supplies.admin.repository.ImageRepository;
import com.pet.supplies.admin.service.ImageService;
import com.pet.supplies.common.constants.ErrorMessages;
import com.pet.supplies.common.domain.Image;
import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.common.model.ImageModel;
import com.pet.supplies.exception.FunctionalRunTimeException;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Setter
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Set<ImageModel> findImages() { 
		Set<Image> images = null;
		if(imageRepository.findAll() != null){
			images = StreamSupport.stream(imageRepository.findAll().spliterator(), false)
	                 .collect(Collectors.toSet());
		}
		Set<ImageModel> imageModels = EntityToModelMapper.mapImageEntityToImageModel(images);
		return imageModels;
	}

	@Override
	public void deleteImage(ImageModel model) {
		try {
			if(model != null){
				Image image = imageRepository.findOne(model.getId());
				imageRepository.delete(image);
			}
		} catch (Exception e) {
			new FunctionalRunTimeException(ErrorMessages.IMAGE_DELETE);
		}
	}

	@Override
	public ImageModel updateImage(ImageModel model) {
		ImageModel imageModel = new ImageModel();
		if(model != null){
			Image image = imageRepository.findOne(model.getId());
			image.setName(model.getName());
			image.setUrl(model.getUrl());
			imageModel = EntityToModelMapper.mapImageToImageModel(image);
		}
		return imageModel;
	}

}
