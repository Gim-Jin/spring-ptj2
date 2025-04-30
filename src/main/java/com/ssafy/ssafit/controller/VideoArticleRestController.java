package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.dto.VideoArticleDto;
import com.ssafy.ssafit.service.VideoArticleService;

@RestController
@RequestMapping("/api/articles")
public class VideoArticleRestController {
	
	private final VideoArticleService videoService;
	
	public VideoArticleRestController(VideoArticleService videoService) {
		
		this.videoService = videoService;
		
	}
	
	// articles 전체 조회
	@GetMapping
	public ResponseEntity<List<VideoArticleDto>> getArticles() {
		List<VideoArticleDto> results = videoService.selectAll();
		
		return results.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(results); 
	}
}
