package com.ssafy.ssafit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ssafy.ssafit.dto.CommentDto;
import com.ssafy.ssafit.repository.CommentRepository;
import com.ssafy.ssafit.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {

		this.commentRepository = commentRepository;
	}

	@Override
	public boolean createComment(CommentDto comment) {
		return commentRepository.insert(comment) == 1 ? true : false;
	}

	@Override
	public boolean updateComment(CommentDto comment) {
		return commentRepository.update(comment) == 1 ? true : false;
	}

	@Override
	public boolean delete(long id) {
		return commentRepository.delete(id) == 1 ? true : false;
	}

	@Override
	public CommentDto select(long id) {
		return commentRepository.select(id);
	}

	@Override
	public List<CommentDto> selectAll(long commentId) {
		return commentRepository.selectAll(commentId);
	}

}
