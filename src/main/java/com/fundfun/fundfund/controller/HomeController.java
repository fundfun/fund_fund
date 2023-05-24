package com.fundfun.fundfund.controller;

import com.fundfun.fundfund.domain.user.UserAdapter;
import com.fundfun.fundfund.domain.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ModelMapper modelMapper;
    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserAdapter adapter, Model model) {

        System.out.println("adapter = " + adapter);

//        if (adapter.getUser() != null) {
//            user = modelMapper.map(adapter.getUser(), UserDTO.class);
//        }

        return "index";
    }
}