package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.dto.UserDto;
import com.ssafy.ssafit.dto.UserLoginRequestDto;
import com.ssafy.ssafit.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		
		this.userService = userService;
		
	}
	
	
	// 유저 정보 요청 받아왔을 때 그냥 email을 아이디로 잡을껄 
	@GetMapping("/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email") String email) {
		
		UserDto result = userService.getUserByEmail(email);
		
		return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> registUser(@RequestBody UserDto user) {
		
		return userService.registUser(user) ? ResponseEntity.ok("등록이 완료되었습니다.") : ResponseEntity.badRequest().body("잘못된 요청입니다.");
	}
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		
		List<UserDto> results = userService.getAllUser();
		
		return results.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(results);
		
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<?> modifyUser(@PathVariable("email") String email, @RequestBody UserDto userDto) {
		
		return userService.modifyUser(userDto) ? ResponseEntity.ok("수정이 완료되었습니다.") : ResponseEntity.badRequest().body("잘못된 요청입니다.");
		
		
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
		
		return userService.withdrawUser(email) ? ResponseEntity.ok("수정이 완료되었습니다.") : ResponseEntity.badRequest().body("잘못된 요청입니다.");
	
	}
	
	// TODO : 쿠키에 대한 처리가 필요할 것 같은데.. 흠......현재는 토큰 안쓰고 그냥 깡으로 session에 박고 쿠키로 관리할 예정임.
	// 문제는 백에서의 세션이랑 프론트에서의 세션이 똑같이 관리가 되냐임.. 일단은 GPT한테 물어본대로 처리해봄.
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) {
		
		
		UserDto loginedUser = userService.login(userLoginRequestDto.getUserEmail(), userLoginRequestDto.getUserPassword());
		if(loginedUser != null) {
			
			session.setAttribute("email", loginedUser.getUserEmail());
			
			session.setAttribute("nickname", loginedUser.getUserNickName());
			
			session.setAttribute("role", "user");
			
			session.setAttribute("id",loginedUser.getUserId());
			
			return ResponseEntity.ok("로그인 성공");
			
		} 
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 틀렸습니다.");
		
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("로그아웃 완료");
	}
	
	
	
}
