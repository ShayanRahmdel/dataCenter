package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.dto.TicketReplyDto;
import com.shayan.datacentermanagment.model.TicketReply;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.model.enumration.Role;
import com.shayan.datacentermanagment.service.TicketReplyService;
import com.shayan.datacentermanagment.service.TicketService;
import com.shayan.datacentermanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticketReply")
@RequiredArgsConstructor
public class TicketReplyController {

    private final TicketReplyService ticketReplyService;
    private final TicketService ticketService;
    private final UserService userService;



    @PostMapping("createReply")
    public TicketReplyDto create(@RequestBody TicketReplyDto dto){
        TicketReply ticketReply = convertDtoToEntity(dto);
        TicketReply reply = ticketReplyService.create(ticketReply.getResponder().getId(), dto.getTicketId(), ticketReply);
        return convertEntityToDto(reply);
    }

    @GetMapping("/seeReplyByUserId")
    public Page<TicketReplyDto> seeReplyByUserId(@Param("id") Long id,@PageableDefault(size = 10, sort = "replyMessage",
            direction = Sort.Direction.DESC) Pageable pageable){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        if (user.getRole().equals(Role.ROLE_SUPPORT)){
            Page<TicketReply> ticketReplies = ticketReplyService.loadBySupporterId(id, pageable);
            List<TicketReplyDto> ticketReplyDtoList = convertEntityToDtoList(ticketReplies.getContent());
            return new PageImpl<>(ticketReplyDtoList);
        }
        if (user.getRole().equals(Role.ROLE_CUSTOMER)){
            Page<TicketReply> ticketReplies = ticketReplyService.loadByCustomerId(id, pageable);
            List<TicketReplyDto> ticketReplyDtoList = convertEntityToDtoList(ticketReplies.getContent());
            return new PageImpl<>(ticketReplyDtoList);
        }
        return new PageImpl<>(List.of());
    }




    private TicketReply convertDtoToEntity(TicketReplyDto dto){
     return TicketReply.builder().replyTime(dto.getReplyTime()).replyMessage(dto.getReplyMessage())
                .ticket(ticketService.load(dto.getTicketId())).responder(userService.load(dto.getResponderId()))
                .build();

    }

    private TicketReplyDto convertEntityToDto(TicketReply entity){
        return TicketReplyDto.builder().replyTime(entity.getReplyTime()).replyMessage(entity.getReplyMessage())
                .ticket(entity.getTicket()).responder(entity.getResponder())
                .build();

    }

    private List<TicketReplyDto> convertEntityToDtoList(List<TicketReply> ticketReplies){
        return ticketReplies.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


}
