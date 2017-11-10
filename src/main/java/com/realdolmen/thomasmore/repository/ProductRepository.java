package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByNaam(String naam);
    List<Product> findAll();
    Product findOne(Long id);
}
