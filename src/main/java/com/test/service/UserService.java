package com.test.service;

import com.test.dao.UserDao;
import com.test.domain.UserEntity;
import com.test.model.UserModel;
import com.test.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    //ToDo this would be better as search using predicates; reduce the number of unique methods
    public List<UserModel> getUsersLikeLastName(String lastName) {
        List<UserEntity> userEntities = userRepository.findAllByLastNameContains(lastName);
        return convertEntityList(userEntities);
    }

    public Optional<UserModel> getUser(long id) {
        //UserEntity userEntity = userRepository.findOne(id);
        UserEntity userEntity = userDao.getUserById(id);
        if (userEntity != null) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userEntity, userModel);
            return Optional.of(userModel);
        } else {
            return Optional.empty();
        }
    }

    public List<UserModel> getUserByFirstName(String firstName) {
        List<UserEntity> userEntities = userDao.getAllByFirstName(firstName);
        return convertEntityList(userEntities);
    }

    //Note: Need to add a better conversation model.  I like MapStruct so far.
    private UserModel convertEntity(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);
        return userModel;
    }

    private List<UserModel> convertEntityList(List<UserEntity> userEntities) {
        //ToDo use streams here; its fun!
        List<UserModel> userModels = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userModels.add(convertEntity(userEntity));
        }
        return userModels;
    }
}
