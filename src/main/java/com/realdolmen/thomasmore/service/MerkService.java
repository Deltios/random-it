package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Merk;
import com.realdolmen.thomasmore.repository.MerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerkService {
    @Autowired
    private MerkRepository merkRepository;

    public void createMerk(String naam) {
        Merk merk = new Merk();
        merk.setNaam(naam);

        merkRepository.save(merk);
    }

    public List<Merk> findAllMerken() {
        return merkRepository.findAll();
    }
}
