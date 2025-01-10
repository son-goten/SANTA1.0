package com.example.santa.outgoing.controller;

import com.example.santa.outgoing.service.OutgoingService;
import com.example.santa.outgoing.vo.OutgoingDetailsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/outgoing")
@RequiredArgsConstructor
@Log4j2
public class OutgoingController {

    private final OutgoingService outgoingService;

    @GetMapping("outgoingRead")
    public String readOutgoing(Model model) {
        List<OutgoingDetailsDTO> list = outgoingService.findAllOutgoing();
        System.out.println("list >>>>> " + list);
        model.addAttribute("outgoings", list);

        return "/outgoing/outgoingRead";
    }

}
