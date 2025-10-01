package com.example.board.controller;

import com.example.board.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 공개 메모 페이지
    @GetMapping("/isPublic")
    public String isPublicPage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member); // 로그인 계정 유지
        }
        return "isPublic"; // → templates/isPublic.html
    }
}