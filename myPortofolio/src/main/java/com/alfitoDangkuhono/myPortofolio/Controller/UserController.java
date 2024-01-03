package com.alfitoDangkuhono.myPortofolio.Controller;

import com.alfitoDangkuhono.myPortofolio.Model.User;
import com.alfitoDangkuhono.myPortofolio.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        try{
            User userRepository=service.save(new User((int) user.getId(),user.getName(),user.getUsia(),user.getJenisKelamin()));
            return new ResponseEntity<>(userRepository,HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public List<User> getAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Optional<User> getById( @PathVariable Integer id){
        return service.findById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,@RequestBody User user){
        User data= service.findById(id).orElse(null);
        if (data == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setName(user.getName());
        user.setUsia(user.getUsia());
        user.setJenisKelamin(user.getJenisKelamin());
        User updateUser=service.save(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }
    @DeleteMapping
    public void deleteAll(){
        service.deleteAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteId(@PathVariable  Integer id){
        User data=service.findById(id).orElse(null);
        if (data== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
