package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.data.Specificatie;
import com.realdolmen.thomasmore.repository.SpecificatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificatieService {
    @Autowired
    private SpecificatieRepository specificatieRepository;

    public void createSpecificatie(String naam, String waarde) {
        Specificatie specificatie = new Specificatie();
        specificatie.setNaam(naam);
        specificatie.setWaarde(waarde);

        specificatieRepository.save(specificatie);
    }

    public List<Specificatie> findAllSpecificaties() {
        return specificatieRepository.findAll();
    }
    //public List<Specificatie> findAllSpecificatiesFromProduct(Product product){ return specificatieRepository.findAllByProductId(product.getId()); }
}
