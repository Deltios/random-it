package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long> {
    Categorie findByNaam(String naam);
    List<Categorie> findAll();
}
