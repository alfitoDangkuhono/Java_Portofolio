package com.alfitoDangkuhono.myPortofolio.Service;

import com.alfitoDangkuhono.myPortofolio.Model.User;
import com.alfitoDangkuhono.myPortofolio.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public Optional<User> getById(Integer id){
        return userRepository.findById(id);
    }
    @Override
    public User updateUser(Integer id, User userDetails){
        Optional<User> user= userRepository.findById(id);
        if (user.isPresent()){
            User fieldUser=user.get();
            fieldUser.setName(userDetails.getName());
            fieldUser.setUsia(userDetails.getUsia());
            fieldUser.setJenisKelamin(userDetails.getJenisKelamin());
            return userRepository.save(fieldUser);
        }
        return null;
    }
    @Override
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }

}
