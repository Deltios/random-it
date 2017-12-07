package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.repository.SupportTicketRepository;
import com.realdolmen.thomasmore.repository.UserRepository;
import com.realdolmen.thomasmore.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SupportTicketService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSession userSession;

    public void createSupportTicket(LocalDate datumAanvraag, String naam, String opmerking, String onderwerp, User user) {
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setDatumAanvraag(datumAanvraag);
        supportTicket.setOpmerking(opmerking);
        supportTicket.setNaam(naam);
        supportTicket.setOnderwerp(onderwerp);
        supportTicket.setUser(user);

        supportTicketRepository.save(supportTicket);
    }
    public List<User> findAllCategorieid(){
        return userRepository.findAll();
    }

    public List<SupportTicket> findAllSupportTickets() {
        return supportTicketRepository.findAll();
    }

    public List<SupportTicket> findSupportsByUser(User user){
        return supportTicketRepository.findAllByUserOrderByDatumAanvraag(user);
    }


}
