package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.Bestelling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellingRepository extends CrudRepository<Bestelling, Long>{
    Bestelling findByBestelnummer(String bestelnummer);
    List<Bestelling> findBestellingsByUserId(Long user_id);
    List<Bestelling> findAll();
}
