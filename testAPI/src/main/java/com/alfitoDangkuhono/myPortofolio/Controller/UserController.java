package com.alfitoDangkuhono.myPortofolio.Controller;

import com.alfitoDangkuhono.myPortofolio.Model.User;
import com.alfitoDangkuhono.myPortofolio.Repository.UserRepository;
import com.alfitoDangkuhono.myPortofolio.Service.UserService;
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
    UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        //done testing
        try{
            User userRepository=userService.buatUser(new User((int) user.getId(),user.getName(),user.getUsia(),user.getJenisKelamin()));
            return new ResponseEntity<>(userRepository, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public List<User> getAll(){
        //done testing
        return userService.lihatSemuaUser();
    }
    @GetMapping("/{id}")
    public Optional<User> getById( @PathVariable Integer id){
        //done testing
        return userService.cariId(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,@RequestBody User user){
        //done testing
        try{
            User data= userService.cariId(id).orElse(null);
            if (data == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            data.setName(user.getName());
            data.setUsia(user.getUsia());
            data.setJenisKelamin(user.getJenisKelamin());
            User updateUser=userService.rubahUser(data);
            return new ResponseEntity<>(updateUser,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public void deleteAll(){
        //done testing
        userService.hapusSemua();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteId(@PathVariable  Integer id){
        //done testing
        User data=userService.cariId(id).orElse(null);
        if (data== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.hapusById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
