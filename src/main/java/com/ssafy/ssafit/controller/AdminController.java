package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.dto.AdminLoginRequestDto;
import com.ssafy.ssafit.service.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(description="어드민 컨트롤러", name="Admin Controller")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	@PostMapping("/login")
 	public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto, HttpSession session) {
		
		if (adminService.adminLogin(adminLoginRequestDto.getLoginId(), adminLoginRequestDto.getPassword())) {

			session.setAttribute("role", "admin");

			return ResponseEntity.ok("로그인 성공");
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 틀렸습니다.");
	}
	
	
}
