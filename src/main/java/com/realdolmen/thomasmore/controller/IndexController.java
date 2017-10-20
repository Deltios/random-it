package com.realdolmen.thomasmore.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUZAU33 on 28/09/2017.
 */
@ManagedBean
@RequestScoped
public class IndexController {

    public String getHelloWorld() {
        return "Hello, world!";
    }

    public String testPage(){
        return "testPage";
    }
    public ArrayList<Integer> NummerTest(){
        ArrayList<Integer> nummers = new ArrayList<Integer>();
        for(int i= 0; i <= 10; i++){
            nummers.add(i);
        }
        return nummers;
    }
    //Methode brengt de gebruiker naar de registratiepagina
    public String registerPage(){
        return "register";
    }

}
