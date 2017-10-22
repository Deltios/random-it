package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.Merk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerkRepository extends CrudRepository<Merk, Long> {
    Merk findByNaam(String naam);
    List<Merk> findAll();
}
