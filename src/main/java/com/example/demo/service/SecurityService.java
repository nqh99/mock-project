package com.example.demo.service;

import javax.servlet.http.HttpSession;

public interface SecurityService {
	public String findLoggedInUsername();

	void autoLogin(String username, String password, HttpSession session);

}
