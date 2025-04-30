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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.dto.CommentDto;
import com.ssafy.ssafit.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/articles")
public class CommentRestController {
	
	private final CommentService commentService;
	
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/{articleId}/comments")
	public ResponseEntity<?> registComment(@PathVariable("articleId") Long articleId,@RequestBody CommentDto commentDto,HttpSession session) {
		
		commentDto.setUserId(Long.parseLong(session.getAttribute("id").toString()));
		
		commentDto.setVideoArticleId(articleId);
		
		return commentService.createComment(commentDto) ? ResponseEntity.ok("댓글 생성 완료") : ResponseEntity.badRequest().body("잘못된 입력입니다.");
	}
	
	@GetMapping("/{articleId}/comments")
	public ResponseEntity<?> getComments(@PathVariable("articleId") Long articleId) {
		
		List<CommentDto> results = commentService.selectAll(articleId);
		return results.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(results);
		
	}
	
	@PutMapping("/{articleId}/comments/{commentId}")
	public ResponseEntity<?> modifyComment(@PathVariable("articleId") Long articleId,@PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
		
		commentDto.setCommentId(commentId);
		
		commentDto.setVideoArticleId(articleId);
		
		return commentService.updateComment(commentDto) ? ResponseEntity.ok("수정 완료") : ResponseEntity.badRequest().body("잘못된 입렵입니다.");
	}
	
	@DeleteMapping("/{articleId}/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
		return commentService.delete(commentId) ? ResponseEntity.ok("삭제 완료") : ResponseEntity.badRequest().body("잘못된 입력입니다.");
	}
	
}
