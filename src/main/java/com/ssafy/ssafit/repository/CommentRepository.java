package com.ssafy.ssafit.repository;

import java.util.List;

import com.ssafy.ssafit.dto.CommentDto;

public interface CommentRepository {

	// 추가
	public int insert(CommentDto comment);

	// 수정
	public int update(CommentDto comment);

	// 삭제
	public int delete(long id);

	// 조회
	public CommentDto select(long id);

	// 전체조회(기본은 생성순)
	public List<CommentDto> selectAll(long articleId);

}
