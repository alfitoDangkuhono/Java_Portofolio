package com.alfitoDangkuhono.myPortofolio.Service;

import com.alfitoDangkuhono.myPortofolio.Model.User;
import com.alfitoDangkuhono.myPortofolio.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;

    @Override
    public User buatUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @Override
    public Optional<User> cariId(Integer id) {
        return userRepository.findById(id);
    }
    @Override
    public List<User> lihatSemuaUser(){
        return userRepository.findAll();
    }
    @Override
    public User rubahUser(User userDetails){
        return userRepository.save(userDetails);
    }
    @Override
    public void hapusById(Integer id){
        userRepository.deleteById(id);
    }
    @Override
    public void hapusSemua(){
        userRepository.deleteAll();
    }

}
