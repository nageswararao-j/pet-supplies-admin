package com.pet.supplies.admin.repository;


import com.pet.supplies.common.domain.Image;

import com.pet.supplies.common.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>
{

}
