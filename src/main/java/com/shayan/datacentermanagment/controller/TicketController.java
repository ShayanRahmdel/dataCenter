package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.dto.TicketDto;
import com.shayan.datacentermanagment.model.Ticket;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.service.TicketService;
import com.shayan.datacentermanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;




    @PostMapping("/createTicket")
    public TicketDto create(@RequestBody TicketDto dto){
        Ticket ticket = convertDtoToEntity(dto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User byUsername = userService.findByUsername(username);
        Ticket ticket1 = ticketService.create(byUsername.getId(), ticket);
        return convertEntityToDto(ticket1);
    }


    @GetMapping
    public Page<TicketDto> seeAllTicket(@PageableDefault(size = 10, sort = "subject",
            direction = Sort.Direction.DESC) Pageable pageable){
        Page<Ticket> tickets = ticketService.loadAll(pageable);
        List<TicketDto> ticketDtos = convertEntityToDtoList(tickets.getContent());
        return new PageImpl<>(ticketDtos);
    }


    private Ticket convertDtoToEntity(TicketDto dto){
        return Ticket.builder().createdAt(dto.getCreatedAt()).message(dto.getMessage()).subject(dto.getSubject())
                .customer(userService.load(dto.getCustomerId())).build();
    }

    private TicketDto convertEntityToDto(Ticket entity){
        return TicketDto.builder().createdAt(entity.getCreatedAt()).message(entity.getMessage()).subject(entity.getSubject())
                .customer(entity.getCustomer()).build();
    }

    private List<TicketDto> convertEntityToDtoList(List<Ticket> tickets){
        return tickets.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}
