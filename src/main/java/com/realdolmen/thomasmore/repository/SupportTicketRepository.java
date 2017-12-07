package com.realdolmen.thomasmore.repository;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketRepository extends CrudRepository<SupportTicket, Long> {
    SupportTicket findByNaam(String naam);
    List<SupportTicket> findAll();
    List<SupportTicket> findAllByUserOrderByDatumAanvraag(User user);
    List<SupportTicket> findAllByOnderwerp(String onderwerp);
    SupportTicket findByOnderwerp(String onderwerp);
}
