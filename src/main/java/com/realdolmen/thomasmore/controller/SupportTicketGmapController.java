package com.realdolmen.thomasmore.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@SessionScoped
public class SupportTicketGmapController implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        //Shared coordinates
        LatLng coord1 = new LatLng(51.1598347, 4.9617172);


        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Random IT"));

    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}