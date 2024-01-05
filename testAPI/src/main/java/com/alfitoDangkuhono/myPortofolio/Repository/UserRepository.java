package com.alfitoDangkuhono.myPortofolio.Repository;

import com.alfitoDangkuhono.myPortofolio.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
