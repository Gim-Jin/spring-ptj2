package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ssafit.dto.UserDto;
import com.ssafy.ssafit.dto.VideoArticleDto;
import com.ssafy.ssafit.service.AdminService;
import com.ssafy.ssafit.service.UserService;
import com.ssafy.ssafit.service.VideoArticleService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	private final AdminService adminService;

	private final VideoArticleService videoArticleService;

	// 생성자 주입.
	public AdminController(UserService userService, AdminService adminService,
			VideoArticleService videoArticleService) {

		this.userService = userService;

		this.adminService = adminService;

		this.videoArticleService = videoArticleService;

	}

	@GetMapping("/index")
	public String adminIndex(Model model) {
			
		model.addAttribute("users", userService.getAllUser());
		model.addAttribute("articles", videoArticleService.selectAll());
		
		return "adminIndex";
	}

	@GetMapping("/login")
	public String adminLoginForm() {
		return "adminLoginForm";
	}

	@PostMapping("/login")
	public String adminLogin(@RequestParam("loginId") String loginId, @RequestParam("password") String password,
			HttpSession session) {

		if (adminService.adminLogin(loginId, password)) {

			session.setAttribute("role", "admin");

			return "redirect:/admin/index";
		}

		return "redirect:/admin/login";
	}

	@GetMapping("/logout")
	public String adminLogout(HttpSession session) {

		session.invalidate();

		return "redirect:/admin/login";

	}


	
	

	// 글 조회
	@GetMapping("/articles")
	public String articleList(Model model) {
		List<VideoArticleDto> articles = videoArticleService.selectAll();
		model.addAttribute("articles", articles);
		return "adminArticleList";
	}

	@GetMapping("/articles/registform")
	public String articleRegistForm() {
		return "articleRegistForm";
	}

	// 글쓰기(관리자만)
	@PostMapping("/articles/regist")
	public String createArticle(@ModelAttribute VideoArticleDto videoarticle) {
		videoArticleService.createArticle(videoarticle);
		return "redirect:/admin/articles";
	}

	// 글삭제(관리자만)
	@GetMapping("/articles/delete")
	public String postMethodName(@RequestParam long id) {
		videoArticleService.deleteArticle(id);
		return "redirect:/admin/articles";
	}
	
	// 영상 수정
	@GetMapping("/articles/edit")
	public String editForm(@RequestParam long id,Model model) {
		model.addAttribute("article",videoArticleService.detailArticle(id));
		return "articleEditForm";
	}
	
	// 영상 수정
	@PostMapping("/articles/edit")
	public String edit(@ModelAttribute VideoArticleDto videoArticleDto) {
		videoArticleService.updateArticle(videoArticleDto);
		return "redirect:/admin/articles";
	}

}
