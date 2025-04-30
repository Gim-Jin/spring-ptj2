package com.ssafy.ssafit.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AdminLoginRequestDto {
	
	private String loginId;
	
	private String password;
	
	// Jackson이 원래 기본 생성자로 dto를 만들고 세터를 이용해서 값을 셋팅하는 듯?
	// 근데 setter를 추가하면 불변성이 깨질 위험이 있으니, 생성자에 JsonCreator라는 어노테이션을 붙여주면
	// jackson이 잡을 수 있는 것 같음. 그래서 이와 같이 작성.
	@JsonCreator
	public AdminLoginRequestDto(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

}
