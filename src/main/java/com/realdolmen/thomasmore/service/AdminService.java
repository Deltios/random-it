package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.*;
import com.realdolmen.thomasmore.repository.*;
import com.realdolmen.thomasmore.session.UserSession;
import net.bootsfaces.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.faces.bean.ManagedProperty;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerkRepository merkRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private BestellingRepository bestellingRepository;
    @Autowired
    private SpecificatieRepository specificatieRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public List<Product> findAllProducts(){return productRepository.findAll();}
    public List<Merk> findAllMerken(){return merkRepository.findAll();}
    public List<Categorie> findAllCategorie(){return categorieRepository.findAll();}
    public List<Bestelling> findAllBestelling(){return bestellingRepository.findAll();}
    public List<Specificatie> findAllSpecificatie(){return specificatieRepository.findAll();}

    public User findUserById(Long id){return userRepository.findOne(id);}
    public Product findProductById(Long id){return productRepository.findOne(id);}
    public Merk findMerkById(Long id){return merkRepository.findOne(id);}
    public Categorie findCategorieById(Long id){return categorieRepository.findOne(id);}
    public Bestelling findBestellingById(Long id){return bestellingRepository.findOne(id);}
    public Specificatie findSpecificatieById(Long id){return specificatieRepository.findOne(id);}

    public void saveOrUpdateUser(User user){ userRepository.save(user); }
    public void saveOrUpdateProduct(Product product){ userRepository.save(product); }
    public void saveOrUpdateMerk(Merk merk){ userRepository.save(merk); }
    public void saveOrUpdateCategorie(Categorie categorie){ userRepository.save(categorie); }
    public void saveOrUpdateBestelling(Bestelling bestelling){ userRepository.save(bestelling); }
    public void saveOrUpdateSpecificatie(Specificatie specificatie){ userRepository.save(specificatie); }
}
