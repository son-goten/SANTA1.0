package com.example.santa.incoming.controller;

import com.example.santa.incoming.service.IncomingService;
import com.example.santa.incoming.vo.IncomingDTO;
import com.example.santa.incoming.vo.IncomingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("incoming")
@RequiredArgsConstructor
@Log4j2
public class IncomingController {

    private final IncomingService incomingService;

    @GetMapping("incomingRead")
    public String incomingRead(Model model) {
        System.out.println("===========================================OKAY===================");
        List<IncomingDTO> list = incomingService.selectAllIncoming();
        System.out.println("=============================================" + list.size() + "==================");
        System.out.println(list);
        model.addAttribute("list", list);
        return "/incoming/incomingRead";
    }

    @PostMapping("readProduct")
    @ResponseBody
    public List<IncomingDTO> readProduct(@RequestBody IncomingDTO incomingDTO, Model model) {
        System.out.println("=========================OKAY READPRODUCT===============");
        List<IncomingDTO> list = incomingService.selectAllIncomingByProductName(incomingDTO.getProductName());
        System.out.println("=============list========" + list);
        return list; //[json, json, json]
    }
}
