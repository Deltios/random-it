package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.repository.BestellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BestellingService {
    @Autowired
    private BestellingRepository bestellingRepository;

    public void createBestelling(String bestelnummer, Calendar besteldatum, String opmerking) {
        Bestelling bestelling = new Bestelling();
        bestelling.setBestelnummer(bestelnummer);
        bestelling.setBesteldatum(besteldatum);
        bestelling.setOpmerking(opmerking);

        bestellingRepository.save(bestelling);
    }

    public List<Bestelling> findAllBestellingen() {
        return bestellingRepository.findAll();
    }
    public List<Bestelling> findAllBestellingenByUser(long Id){return bestellingRepository.findBestellingsByUserId(Id);}
}
