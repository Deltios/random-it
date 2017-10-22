package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Categorie;
import com.realdolmen.thomasmore.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public void createCategorie(String naam) {
        Categorie categorie = new Categorie();
        categorie.setNaam(naam);

        categorieRepository.save(categorie);
    }

    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }
}
