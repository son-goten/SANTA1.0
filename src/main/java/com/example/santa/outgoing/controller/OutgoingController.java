package com.example.santa.outgoing.controller;

import com.example.santa.outgoing.service.OutgoingService;
import com.example.santa.outgoing.vo.OutgoingDTO;
import com.example.santa.transit.vo.TransitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/outgoing")
@RequiredArgsConstructor
@Log4j2
public class OutgoingController {

    private final OutgoingService outgoingService;

    @GetMapping("/outgoingRead")
    public String readOutgoing(Model model) {
        List<OutgoingDTO> list = outgoingService.findAllOutgoing();
        System.out.println("list >>>>> " + list);
        model.addAttribute("list", list);

        return "/outgoing/outgoingRead";
    }

    @PostMapping("readProduct")
    @ResponseBody
    public List<OutgoingDTO> readProduct(@RequestBody OutgoingDTO outgoingDTO, Model model) {
        System.out.println("=========================OKAY READPRODUCT===============");
        List<OutgoingDTO> list = outgoingService.findOutgoingByBranchName(outgoingDTO.getBranchName());
        System.out.println("=============list========" + list);
        return list;
    }

    @PostMapping("readCategory")
    @ResponseBody
    public List<OutgoingDTO> readCategory(@RequestBody OutgoingDTO outgoingDTO, Model model) {
        System.out.println("=========================OKAY READ CATEGORY===============");
        List<OutgoingDTO> list = outgoingService.findOutgoingByCategory(outgoingDTO.getProductCategory());
        System.out.println("=============list========" + list);
        return list;

    }

    @GetMapping("/outgoingApprove")
    public String readOutgoingApprove(Model model) {
        List<OutgoingDTO> list = outgoingService.findAllOutgoing();
        System.out.println("list >>>>> " + list);
        model.addAttribute("list", list);

        return "/outgoing/outgoingApprove";
    }


}
