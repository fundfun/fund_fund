package com.fundfun.fundfund.controller.user;

import com.fundfun.fundfund.domain.user.*;
import com.fundfun.fundfund.service.user.UserService;
import com.fundfun.fundfund.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/register")
    public String getRegisterForm(@RequestParam(value = "role", required = false) String role, Model model) {
        if(role == null){
            return "register_role";
        } else if(role.equals("COMMON")){
            model.addAttribute("role", role);
            return "register";
        } else {
            model.addAttribute("role", role);
            return "register";
        }
    }

    @PostMapping("/register")
    public String register(RegisterForm form, @RequestParam("role") String role) {

        UserDTO user = userService.register(Users.builder()
                .count(0L)
                .email(form.getEmail())
                .gender(Gender.valueOf(form.getGender()))
                .money(0L)
                .benefit(0L)
                .name(form.getName())
                .phone(form.getPhone())
                .password(bCryptPasswordEncoder.encode(form.getPassword()))
                .role(Role.valueOf(role))
                .total_investment(0L)
                .build()
        );

        log.info("[UserController] ]User Role {} has been registered.", user.getRole(), user.toString());
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal UserAdapter adapter, Model model) {
        UserDTO user = userService.findById(adapter.getUser().getId());

        model.addAttribute("user", user);
        if (user.getRole() == Role.COMMON) {
            model.addAttribute("investment", user.getOrders().stream().map(x -> x.getProduct()).collect(Collectors.toList()));
            model.addAttribute("posts", user.getPosts());

        } else if (user.getRole() == Role.FUND_MANAGER) {
            model.addAttribute("portfolios", user.getOn_vote_portfolio());
            model.addAttribute("products", user.getManaging_product());
        }
        return "index";
    }

    @GetMapping("/mypage/edit")
    public String myPageForm(@AuthenticationPrincipal UserAdapter adapter, Model model) {
        model.addAttribute("form", new UserUpdateForm());
        return "user/mypage_form";
    }

    @PostMapping("/mypage/edit")
    public String editMyPage(@AuthenticationPrincipal UserAdapter adapter, @Valid UserUpdateForm form, BindingResult result) {
        UserDTO dto = userService.findById(adapter.getUser().getId());
        userService.update(dto.getId(), UserDTO
                .builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .image(form.getImage() == null ? dto.getImage() : form.getImage())
                .email(form.getEmail() == null ? dto.getEmail() : form.getEmail())
                .phone(form.getPhone() == null ? dto.getPhone() : form.getPhone())
                .role(dto.getRole())
                .gender(dto.getGender())
                .money(dto.getMoney())
                .build());
        return "redirect:/";
    }

    @GetMapping("/user/charge")
    public String showMoney(@AuthenticationPrincipal UserAdapter adapter, Model model) {
        UserDTO dto = userService.findById(adapter.getUser().getId());
        long curPoints = dto.getMoney();
        model.addAttribute("curPoints", curPoints);
        return "user/charge";
    }

    @GetMapping("/user/myPageUser")
    public String goMyPageUser() {
        return "user/myPageUser";
    }

    @GetMapping("/user/myPageFund")
    public String goMyPageFund() {
        return "user/myPageFund";
    }
}
