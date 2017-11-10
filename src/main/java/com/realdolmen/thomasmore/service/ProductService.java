package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void createProduct(String naam, String omschrijving, double prijs, int hoeveelheidInVoorraad) {
        Product product = new Product();
        product.setNaam(naam);
        product.setOmschrijving(omschrijving);
        product.setPrijs(prijs);
        product.setHoeveelheidInVoorraad(hoeveelheidInVoorraad);

        productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findOne(id);
    }
}
