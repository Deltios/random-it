package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.Specificatie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificatieRepository extends CrudRepository<Specificatie, Long> {
    Specificatie findByNaam(String naam);
    List<Specificatie> findAll();
}
