package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.TicketReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketReplyService {

    TicketReply create(Long responder,Long ticket,TicketReply ticketReply);

    TicketReply load(Long id);

    Page<TicketReply> loadBySupporterId(Long id, Pageable pageable);

    TicketReply update(Long id,TicketReply ticketReply);

    void  delete(Long id);

    Page<TicketReply> loadByCustomerId(Long id,Pageable pageable);



}
