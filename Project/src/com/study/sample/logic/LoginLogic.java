package com.study.sample.logic;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.dao.UserDAO;
import com.study.sample.entity.UserType;
import com.study.sample.util.StringHashProvider;

public class LoginLogic extends Logic{

	public static boolean checkLogin(String login, String pass, HttpServletRequest request) {
		boolean result = false;
		UserDAO dao = new UserDAO();
		String password = StringHashProvider.getHashValue(pass);
		UserType role = dao.checkUserPassword(login, password);
		if (role != null){
			request.getSession().setAttribute("role", role.toString());
			request.getSession().setAttribute("user", login);
			result = true;
		}
		return result;

		}
	}


