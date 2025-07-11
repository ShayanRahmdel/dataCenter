package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select t from Ticket t where t.customer.id=:userId")
    Page<Ticket> loadByUserId(@Param("userId") Long userId, Pageable pageable);

}
