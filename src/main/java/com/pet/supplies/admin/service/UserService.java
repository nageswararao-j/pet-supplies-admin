package com.pet.supplies.admin.service;

import java.util.Set;

import com.pet.supplies.common.model.UserModel;

public interface UserService {

	public Set<UserModel> findUsers();

	public void deleteUser(UserModel model);

	public UserModel updateUser(UserModel model);

}
