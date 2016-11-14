package com.pet.supplies.admin.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.supplies.admin.repository.UserRepository;
import com.pet.supplies.admin.service.UserService;
import com.pet.supplies.common.constants.ErrorMessages;
import com.pet.supplies.common.domain.User;
import com.pet.supplies.common.mapper.EntityToModelMapper;
import com.pet.supplies.common.model.UserModel;
import com.pet.supplies.exception.FunctionalRunTimeException;

@Service
@Transactional
public class UserServiceImpl implements UserService
{

   @Setter
   @Autowired
   private UserRepository userRepository;

   @Override
   public Set<UserModel> findUsers()
   {
      List<User> users = null;
      if (userRepository.findAll() != null)
      {
         users = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
      }
      Set<UserModel> userModels = EntityToModelMapper.mapUsersEntityToUsersModel(users);
      return new HashSet<UserModel>(userModels);
   }

   @Override
   public void deleteUser(UserModel model)
   {
      try
      {
         if (model != null)
         {
            userRepository.delete(model.getUserId());
         }
      }
      catch (Exception ex)
      {
         new FunctionalRunTimeException(ErrorMessages.USER_DELETE);
      }
   }

   @Override
   public UserModel updateUser(UserModel model)
   {
      try
      {
         if (model != null)
         {
            User existingUser = userRepository.findOne(model.getUserId());
            existingUser.setName(model.getName());
            User updatedUser = userRepository.save(existingUser);
            model = EntityToModelMapper.mapUserEntityToUserModel(updatedUser);
         }
      }
      catch (Exception ex)
      {
         new FunctionalRunTimeException(ErrorMessages.USER_UPDATE);
      }
      return model;
   }

}
