package com.pet.supplies.admin.repository;

import com.pet.supplies.common.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{

}
