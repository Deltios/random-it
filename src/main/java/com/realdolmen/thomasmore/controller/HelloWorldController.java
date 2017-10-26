package com.realdolmen.thomasmore.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUZAU33 on 28/09/2017.
 */
@ManagedBean
@SessionScoped
public class HelloWorldController {

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

}
