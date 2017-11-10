package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SupportTicketService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public void createSupportTicket(Calendar datumAanvraag, String opmerking) {
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setDatumAanvraag(datumAanvraag);
        supportTicket.setOpmerking(opmerking);

        supportTicketRepository.save(supportTicket);
    }

    public List<SupportTicket> findAllSupportTickets() {
        return supportTicketRepository.findAll();
    }
}
