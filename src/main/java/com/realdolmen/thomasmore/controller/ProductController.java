package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Categorie;
import com.realdolmen.thomasmore.data.Merk;
import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.data.Specificatie;
import com.realdolmen.thomasmore.service.ProductService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.*;

@ManagedBean
@SessionScoped
public class ProductController {
    @ManagedProperty("#{productService}")
    private ProductService productService;

    private String newNaam;
    private String newOmschrijving;
    private double newPrijs;
    private int newHoeveelheidInVoorraad;
    private int newMerkId;
    private int newCategorieId;

    private int categorieId = 0;
    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }


    private Product product;
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    private Long productId;
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
        this.product = productService.getProduct(productId);
    }
    private List<Specificatie> specificaties;
    public List<Specificatie> getSpecificaties(){
        return product.getSpecificaties();
    }
    public void setSpecificaties(List<Specificatie> specificaties) {
        this.specificaties = specificaties;
    }

    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    public List<Product> getProductsByCategory(int id) {
        return productService.findAllProductsByCategory(id);
    }

    public List<Product> getArtikelsInWinkelkarretje(HttpSession session) {
        HashMap<Product, Integer> winkelkarretje = (HashMap<Product, Integer>)session.getAttribute("winkelkarretje");

        if(winkelkarretje == null) {
            return Collections.EMPTY_LIST;
        }

        List<Product> artikels = new ArrayList<>();
        artikels.addAll(winkelkarretje.keySet());
        return artikels;
    }

    public int getArtikelAantalInWinkelkarretje(HttpSession session, Product artikel) {
        HashMap<Product, Integer> winkelkarretje = (HashMap<Product, Integer>)session.getAttribute("winkelkarretje");
        int aantal = winkelkarretje.get(artikel);
        return aantal;
    }

    public void createProduct() {
        productService.createProduct(newNaam, newOmschrijving, newPrijs, newHoeveelheidInVoorraad, newMerkId, newCategorieId);
        addMessage("Product toegevoegd!");
        clearForm();
    }

    public String naarWinkelkarretjeOverzicht(HttpSession session) {
        if(session.getAttribute("user") == null) {
            return "/user/register";
        }
        else {
            return "/producten/winkelmandje";
        }
    }

    public List<Product> FrontpageProducten(){
        Random rnd = new Random();
        List<Product> alleProducten = this.getProducts();
        List<Product> frontPageProducten = new ArrayList<Product>();
        for (int i = 0; i < 5; i++){
            int index = rnd.nextInt(alleProducten.size());
            Product product = productService.getProduct(alleProducten.get(index).getId());
            frontPageProducten.add(product);
        }
        return frontPageProducten;
    }

    public double berekenTotaalPrijs(HttpSession session) {
        HashMap<Product, Integer> winkelkarretje = (HashMap<Product, Integer>)session.getAttribute("winkelkarretje");

        if(winkelkarretje == null) {
            return 0.0;
        }

        double totaalprijs = 0.0;

        for(Map.Entry<Product, Integer> artikel : winkelkarretje.entrySet()){
            totaalprijs += artikel.getValue() * artikel.getKey().getPrijs();
        }

        return totaalprijs;
    }

    public void createTestProducten(){
        productService.createProduct("GTX 1080", "Zotte GPU", 899, 82, 1, 2);
        productService.createProduct("i7 6700k", "Zotte CPU", 499, 64, 2, 1);
        productService.createProduct("Z370 Aorus", "Zot moederbordje", 279, 38, 3, 3);
    }

    private void clearForm() {
        newNaam = null;
        newOmschrijving = null;
        newPrijs = 0;
        newHoeveelheidInVoorraad = 0;
        newCategorieId = 0;
        newMerkId = 0;
    }

    public String addArtikelInWinkelkarretje(HttpSession session, Product artikel, int aantalToeTeVoegen) {
        if(session.getAttribute("user") == null) {
            return "/user/login";
        }

        HashMap<Product, Integer> winkelkarretje;

        if(session.getAttribute("winkelkarretje") == null) {
            winkelkarretje = new HashMap<>();
            session.setAttribute("winkelkarretje", winkelkarretje);
        }
        else {
            winkelkarretje = (HashMap<Product, Integer>)session.getAttribute("winkelkarretje");
        }

        if(winkelkarretje.containsKey(artikel)) {
            int aantal = winkelkarretje.get(artikel);
            winkelkarretje.put(artikel, aantal + aantalToeTeVoegen);
        }
        else {
            winkelkarretje.put(artikel, aantalToeTeVoegen);
        }

        return "/producten/productenlijst";
    }

    public String removeArtikelFromWinkelkarretje(HttpSession session, Product artikel, int aantalAfTeTrekken) {
        HashMap<Product, Integer> winkelkarretje = (HashMap<Product, Integer>)session.getAttribute("winkelkarretje");

        int aantal = winkelkarretje.get(artikel);
        if((aantal - aantalAfTeTrekken) == 0) {
            winkelkarretje.remove(artikel);
        }
        else {
            winkelkarretje.put(artikel, aantal - aantalAfTeTrekken);
        }

        return "/producten/winkelmandje";
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Aanpassen product
    public String editProduct(Product product) {

        return null;
    }

    public ProductService getProductService() {
        return productService;
    }

    public String getNewNaam() {
        return newNaam;
    }

    public void setNewNaam(String newNaam) {
        this.newNaam = newNaam;
    }

    public String getNewOmschrijving() {
        return newOmschrijving;
    }

    public void setNewOmschrijving(String newOmschrijving) {
        this.newOmschrijving = newOmschrijving;
    }

    public double getNewPrijs() {
        return newPrijs;
    }

    public void setNewPrijs(double newPrijs) {
        this.newPrijs = newPrijs;
    }

    public int getNewHoeveelheidInVoorraad() {
        return newHoeveelheidInVoorraad;
    }

    public void setNewHoeveelheidInVoorraad(int newHoeveelheidInVoorraad) {
        this.newHoeveelheidInVoorraad = newHoeveelheidInVoorraad;
    }

    public int getNewMerkId() {
        return newMerkId;
    }

    public void setNewMerkId(int newMerkId) {
        this.newMerkId = newMerkId;
    }

    public int getNewCategorieId() {
        return newCategorieId;
    }

    public void setNewCategorieId(int newCategorieId) {
        this.newCategorieId = newCategorieId;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
