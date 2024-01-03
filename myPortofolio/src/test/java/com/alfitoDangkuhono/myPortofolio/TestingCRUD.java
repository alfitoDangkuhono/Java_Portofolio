package com.alfitoDangkuhono.myPortofolio;

import com.alfitoDangkuhono.myPortofolio.Controller.UserController;
import com.alfitoDangkuhono.myPortofolio.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestingCRUD {

    @Autowired
     UserController userController;
    @Autowired
     User value;

    @Test
    public void testingCreate(){

        value.setName("alfito");
        value.setUsia(21);
        value.setJenisKelamin("laki-laki");

//        User saved= service.createUser(value);
        User saved= userController.create(value).getBody();
        Assertions.assertThat(saved).isNotNull();
//        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }
}
