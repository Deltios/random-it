package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByVoornaam(String voornaam);
    List<User> findAll();
}
