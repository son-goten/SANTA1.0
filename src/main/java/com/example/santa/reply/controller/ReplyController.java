package com.example.santa.reply.controller;



import com.example.santa.reply.service.ReplyService;
import com.example.santa.reply.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("reply") //contextpath/cart/cart
    public String reply() {
        System.out.println("reply 화면 요청>>>>>>>>>>>>>>>> ");
        return "board/read";
    }

    @PostMapping("create2")
    @ResponseBody //html이 아닌 것을 응답할때
    public boolean create2(@RequestBody ReplyVO replyVO) {
        System.out.println("-----" + replyVO);
        int result = replyService.insertReply(replyVO);
        if (result == 1) {
            return true; //http의 응답으로 true가 전달
        }else{
            return false;
        }
    }

    //@RequestBody
    // 자바스크립트에서 json으로 보낸 것을 vo에 넣어줌.
    @PutMapping("update")
    @ResponseBody
    //html이 아닌 return한 데이터를 http에 넣어서 전송해줘.
    public boolean updateReply(@RequestBody ReplyVO replyVO){
        System.out.println(replyVO);
        int result = replyService.updateReply(replyVO);
        if (result == 1) {
            return true; //http의 응답으로 true가 전달
        }else{
            return false;
        }
    } //reply update




}