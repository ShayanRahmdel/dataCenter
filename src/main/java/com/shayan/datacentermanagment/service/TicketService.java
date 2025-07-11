package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

    Ticket create(Long userId,Ticket ticket);


    Ticket load(Long id);


    Page<Ticket> loadByUserId(Long userId, Pageable pageable);


    Ticket update(Long id,Ticket ticket);


    void delete(Long userId,Long id);

    Page<Ticket> loadAll(Pageable pageable);

}
