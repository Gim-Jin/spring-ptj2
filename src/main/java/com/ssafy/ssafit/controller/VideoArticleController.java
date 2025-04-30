package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.dto.VideoArticleDto;
import com.ssafy.ssafit.service.VideoArticleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "게시글 컨트롤", name = "Article Controller")
@RestController
@RequestMapping("/api/articles")
public class VideoArticleController {
	
	private final VideoArticleService videoService;
	
	public VideoArticleController(VideoArticleService videoService) {
		
		this.videoService = videoService;
		
	}
	
	// articles 전체 조회
	@GetMapping
	public ResponseEntity<?> getArticles(
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String part) {
		 
		List<VideoArticleDto> results = null;
		
		if(sort != null && sort.equals("viewcnt")) {
			results = videoService.selectAllByviewcnt();
		} else if(title != null) {
			results = videoService.searchByTitle(title);
		} else if(keyword != null) {
			results = videoService.searchByKeyword(keyword);
		} else if(part != null) {
			results = videoService.selectVideosByPart(part);
		} else {
			results = videoService.selectAll();
		}
			
		for (VideoArticleDto article : results) {
			String url = article.getVideoArticleUrl();
			String videoId = url.substring(url.lastIndexOf("/") + 1);
			article.setVideoArticleUrl(videoId);
		}
		
		return results.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(results); 
	}
	
	@GetMapping("/{articleId}")
	public ResponseEntity<?> getArticle(@PathVariable("articleId") Long articleId) {
		
		VideoArticleDto result = videoService.detailArticle(articleId);
		
		return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> registArticle(@RequestBody VideoArticleDto videoArticleDto) {
		
		return videoService.createArticle(videoArticleDto) ? ResponseEntity.ok("생성 완료") : ResponseEntity.badRequest().body("잘못된 입력입니다."); 
	}
	
	@PutMapping
	public ResponseEntity<?> modifyArticle(@RequestBody VideoArticleDto videoArticleDto) {
		return videoService.updateArticle(videoArticleDto) ? ResponseEntity.ok("수정 완료") : ResponseEntity.badRequest().body("잘못된 입력입니다.");
	}
	
	@DeleteMapping("/{articleId}")
	public ResponseEntity<?> deleteArticle(@PathVariable("articleId") Long articleId) {
		return videoService.deleteArticle(articleId) ? ResponseEntity.ok("삭제 완료") : ResponseEntity.badRequest().body("잘못된 입력입니다.");
	}
	
}
