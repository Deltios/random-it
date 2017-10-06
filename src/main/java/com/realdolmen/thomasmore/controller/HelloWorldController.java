package com.realdolmen.thomasmore.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by JUZAU33 on 28/09/2017.
 */
@ManagedBean
@RequestScoped
public class HelloWorldController {

    public String getHelloWorld() {
        return "Hello, world!";
    }

}
