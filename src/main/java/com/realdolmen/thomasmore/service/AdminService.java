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
    @Autowired
    private SupportTicketRepository supportTicketRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public List<Product> findAllProducts(){return productRepository.findAll();}
    public List<Merk> findAllMerken(){return merkRepository.findAll();}
    public List<Categorie> findAllCategorie(){return categorieRepository.findAll();}
    public List<Bestelling> findAllBestelling(){return bestellingRepository.findAll();}
    public List<Specificatie> findAllSpecificatie(){return specificatieRepository.findAll();}
    public List<SupportTicket> findAllSupportTicket(){return supportTicketRepository.findAll();}
    public List<SupportTicket> findSupportByOnderwerp(String onderwerp){return supportTicketRepository.findAllByOnderwerp(onderwerp);}

    public User findUserById(Long id){return userRepository.findOne(id);}
    public Product findProductById(Long id){return productRepository.findOne(id);}
    public Merk findMerkById(Long id){return merkRepository.findOne(id);}
    public Categorie findCategorieById(Long id){return categorieRepository.findOne(id);}
    public Bestelling findBestellingById(Long id){return bestellingRepository.findOne(id);}
    public Specificatie findSpecificatieById(Long id){return specificatieRepository.findOne(id);}

    public void saveOrUpdateUser(User user){
        String hashedWachtwoord = passwordEncoder.encode(user.getWachtwoord());
        user.setWachtwoord(hashedWachtwoord);
        userRepository.save(user); }
    public void saveOrUpdateProduct(Product product){ productRepository.save(product); }
    public void saveOrUpdateMerk(Merk merk){ merkRepository.save(merk); }
    public void saveOrUpdateCategorie(Categorie categorie){ categorieRepository.save(categorie); }
    public void saveOrUpdateBestelling(Bestelling bestelling){ bestellingRepository.save(bestelling); }
    public void saveOrUpdateSpecificatie(Specificatie specificatie){ specificatieRepository.save(specificatie); }

    public void deleteUser(long id){ userRepository.delete(id); }
    public void deleteProduct(long id){productRepository.delete(id);}
    public void deleteMerk(long id){merkRepository.delete(id);}
    public void deleteCategorie(long id){categorieRepository.delete(id);}
    public void deleteBestelling(long id){bestellingRepository.delete(id);}
    public void deleteSpecificatie(long id){specificatieRepository.delete(id);}
    public void deleteSupportTicket(long id){supportTicketRepository.delete(id);}

    public List<Bestelling> findBestellingenByPerson(long id) {
        return bestellingRepository.findBestellingsByUserId(id);
    }
}
