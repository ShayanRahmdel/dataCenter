package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Ticket;
import com.shayan.datacentermanagment.model.TicketReply;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.model.enumration.Role;
import com.shayan.datacentermanagment.reposiory.TicketReplyRepository;
import com.shayan.datacentermanagment.service.TicketReplyService;
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
public class TicketReplyServiceImpl implements TicketReplyService {

    private final TicketReplyRepository ticketReplyRepository;
    private final TicketService ticketService;
    private final UserService userService;
    private final ValidationUtil validationUtil;

    @LogActivity("adding new TicketReply")
    @Override
    public TicketReply create(Long responder,Long ticketId,TicketReply ticketReply) {
        User supporter = userService.load(responder);
        Ticket ticket = ticketService.load(ticketId);
        if (!supporter.getRole().equals(Role.ROLE_SUPPORT)){
            throw new RuntimeException("just Supporter can send Reply");
        }
        ticketReply.setResponder(supporter);
        ticketReply.setTicket(ticket);
        return ticketReplyRepository.save(ticketReply);
    }

    @Override
    public TicketReply load(Long id) {
        return ticketReplyRepository.findById(id).orElseThrow(()->
                new NotFoundException("cant found TicketReply"));
    }

    @Override
    public Page<TicketReply> loadBySupporterId(Long id,Pageable pageable) {
        return ticketReplyRepository.loadBySupporterId(id,pageable);
    }
    @LogActivity("update TicketReply")
    @Override
    public TicketReply update(Long id, TicketReply ticketReply) {
        TicketReply oldTicketReply = load(id);
        oldTicketReply.setReplyMessage(ticketReply.getReplyMessage());
        oldTicketReply.setReplyTime(LocalDateTime.now());
        oldTicketReply.setTicket(ticketReply.getTicket());
        User supporter = userService.load(ticketReply.getResponder().getId());
        oldTicketReply.setResponder(supporter);
        return ticketReplyRepository.save(oldTicketReply);

    }

    @LogActivity("delete TicketReply")
    @Override
    public void delete(Long id) {
        validationUtil.validateExists(ticketReplyRepository,id,"TicketReply");
        ticketReplyRepository.deleteById(id);
    }

    @Override
    public Page<TicketReply> loadByCustomerId(Long id,Pageable pageable) {
        return ticketReplyRepository.loadByCustomerId(id,pageable);
    }
}
