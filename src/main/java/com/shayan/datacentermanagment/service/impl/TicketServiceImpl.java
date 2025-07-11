package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Ticket;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.model.enumration.Role;
import com.shayan.datacentermanagment.reposiory.TicketRepository;
import com.shayan.datacentermanagment.service.TicketService;
import com.shayan.datacentermanagment.service.UserService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final ValidationUtil validationUtil;


    @Override
    @LogActivity("adding new Ticket")
    public Ticket create(Long userId, Ticket ticket) {
        User user = userService.load(userId);
        if (!user.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new RuntimeException("just customer can create Ticket");
        }
        ticket.setCustomer(user);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket load(Long id) {
        return ticketRepository.findById(id).orElseThrow(()-> new NotFoundException("cant found Ticket"));
    }
    @Override
    public Page<Ticket> loadByUserId(Long userId, Pageable pageable) {
        return ticketRepository.loadByUserId(userId,pageable);
    }

    @Override
    @LogActivity("update Ticket")
    public Ticket update(Long id,Ticket ticket) {
        Ticket oldTicket = ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("not fount ticket"));
        oldTicket.setSubject(ticket.getSubject());
        oldTicket.setMessage(ticket.getMessage());
        oldTicket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(oldTicket);


    }

    @Override
    @LogActivity("delete Ticket")
    public void delete(Long userId, Long id) {
        validationUtil.validateExists(ticketRepository,id,"Ticket");
        ticketRepository.deleteById(id);
    }

    @Override
    public Page<Ticket> loadAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }
}
