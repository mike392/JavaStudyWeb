package com.study.sample.logic;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.dao.UserDAO;
import com.study.sample.entity.User;
import com.study.sample.manager.MessageManager;
import com.study.sample.util.InputValidator;
import com.study.sample.util.StringHashProvider;

public class SignupLogic extends Logic {

	public static boolean successfullSignup(HttpServletRequest request) {
		boolean result = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypedPassword = request.getParameter("re-typed password");		
		if (InputValidator.isValidUsername(username) && InputValidator.isValidPassword(password) && InputValidator.isValidPasswordConfirm(password, retypedPassword))
		{
			String passwordHash = StringHashProvider.getHashValue(password);
			User user = new User();
			user.setLogin(username);
			user.setPassword(passwordHash);
			UserDAO dao = new UserDAO();
			if (dao.add(username, passwordHash)){
				request.setAttribute(MessageManager.getProperty("message.signup.success"), "success");
				result = true;
			} else {
				request.setAttribute(MessageManager.getProperty("message.signup.failure"), "failure");
				result = true;
			}
		} else {
			if (!InputValidator.isValidUsername(username)){
				request.setAttribute(MessageManager.getProperty("message.username.failure"), "failure");
				result = true;
			} 
			if (!InputValidator.isValidPassword(password)){
				request.setAttribute(MessageManager.getProperty("message.password.failure"), "failure");
				result = true;
			} 
			if (!InputValidator.isValidPasswordConfirm(password, retypedPassword)){
				request.setAttribute(MessageManager.getProperty("message.retyped.failure"), "failure");
				result = true;
			} 
		}
		return result;
	}

}
