package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.dto.CommentDto;

public interface CommentService {

	public boolean createComment(CommentDto comment);

	public boolean updateComment(CommentDto comment);

	public boolean delete(long id);

	public CommentDto select(long id);

	public List<CommentDto> selectAll(long articleId);


}
