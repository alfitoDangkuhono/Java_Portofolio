package com.alfitoDangkuhono.myPortofolio.Service;

import com.alfitoDangkuhono.myPortofolio.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    void hapusById(Integer id);
    List<User> lihatSemuaUser();
    Optional<User> cariId(Integer id);
    User buatUser(User user);
    void hapusSemua();
    User rubahUser( User userDetails);

}
