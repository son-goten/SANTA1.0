package com.example.santa.outgoing.controller;

import com.example.santa.outgoing.service.OutgoingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outgoing")
@RequiredArgsConstructor
@Log4j2
public class OutgoingController {

    private final OutgoingService outgoingService;

}
