package com.example.santa.transit.controller;

import com.example.santa.transit.service.TransitService;
import com.example.santa.transit.vo.TransitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/transit")
@RequiredArgsConstructor
@Log4j2
public class TransitController {

    private final TransitService transitService;

    @Value("${tmap.api.key}")
    private String tmapApiKey;

//    @GetMapping("/transit/mapOptimization")
//    @ResponseBody
//    public List<TransitDTO> mapOptimization() {
//        List<TransitDTO> list = new ArrayList<>();
//        list.add(new TransitDTO());
//        return list;
//    }

    @GetMapping("/mapOptimization")
    public String getMapPage(Model model) {
        model.addAttribute("tmapApiKey", tmapApiKey);
        System.out.println("tmapKey >>>>>>>> " + tmapApiKey);
        return "/transit/mapOptimization";
    }


    @GetMapping("/transitRead")
    public String readTransit(Model model) {
        List<TransitDTO> list = transitService.findAllTransit();
        System.out.println("list >>>>> " + list);
        model.addAttribute("list", list);

        return "/transit/transitRead";
    }

    @GetMapping("/transitApprove")
    public String readTransitApprove(Model model) {
        List<TransitDTO> list = transitService.findAllTransit();
        System.out.println("list >>>>> " + list);
        model.addAttribute("list", list);

        return "/transit/transitApprove";
    }

    @RestController
    @RequestMapping("/map")
    public class MapController {

        private final String KAKAO_API_KEY = "YOUR_KAKAO_REST_API_KEY"; // 카카오 REST API 키

        @PostMapping("/directions")
        public ResponseEntity<String> getDirections(@RequestBody Map<String, Object> requestData) {
            String apiUrl = "https://apis-navi.kakaomobility.com/v1/waypoints/directions";

            try {
                // HTTP 요청 설정
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);

                // RestTemplate으로 API 호출
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

                return ResponseEntity.ok(response.getBody());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    }






}
