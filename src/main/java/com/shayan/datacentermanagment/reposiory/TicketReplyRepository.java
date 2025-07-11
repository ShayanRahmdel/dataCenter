package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.TicketReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TicketReplyRepository extends JpaRepository<TicketReply,Long> {

    @Query("SELECT t from TicketReply t where t.responder.id=:supId")
    Page<TicketReply> loadBySupporterId(@Param("supId")Long supId, Pageable pageable);


    @Query("SELECT t from TicketReply t where t.ticket.customer.id=:id")
    Page<TicketReply> loadByCustomerId(@Param("id")Long id, Pageable pageable);
}
