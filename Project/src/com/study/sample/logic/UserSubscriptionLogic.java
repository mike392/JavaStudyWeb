package com.study.sample.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.dao.UserDAO;
import com.study.sample.dao.UserSubscriptionDAO;
import com.study.sample.entity.User;
import com.study.sample.entity.UserSubscription;
import com.study.sample.manager.MessageManager;
import com.study.sample.util.InputValidator;

public class UserSubscriptionLogic extends Logic {

	public static boolean addUserSubscription(HttpServletRequest request) {
		boolean result = false;
		UserSubscription sub = new UserSubscription();
		sub.setUsername(request.getParameter("username"));
		sub.setSubname(request.getParameter("subname"));
		request.setAttribute("sub", sub);
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		if (dao.add(sub.getUsername(), sub.getSubname())){
			getUserList(request);
			SubscriptionLogic.getSubscriptionList(request);
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionadd.success"), "success");
			result = true;
		} else {
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionadd.failure"), "failed");
			result = true;
		}
		return result;
	}
	
	public static boolean getUserSubscriptionToEdit(HttpServletRequest request) {
		UserSubscription sub = new UserSubscription();
		sub.setUsername(request.getParameter("username"));
		sub.setSubname(request.getParameter("subname"));
		request.setAttribute("sub", sub);
		boolean result = getUserList(request) && SubscriptionLogic.getSubscriptionList(request);
		return result;
	}
	
	public static boolean removeUserSubscription(HttpServletRequest request) {
		boolean result = false;
		UserSubscription sub = new UserSubscription();
		sub.setUsername(request.getParameter("username").trim());
		sub.setSubname(request.getParameter("subname").trim());
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		if (dao.remove(sub.getUsername(), sub.getSubname())){
			getUserSubscriptions(request);
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionremove.success"), "success");
			result = true;
		} else {
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionremove.failure"), "failed");
			result = true;
		}
		return result;
	}
	
	public static boolean updateUserSubscription(String userName, HttpServletRequest request) {
		boolean result = false;
		UserSubscription sub = new UserSubscription();
		sub.setUsername(userName.trim());
		sub.setSubname(request.getParameter("subname").trim());
		sub.setBlocked(request.getParameter("blocked").trim());
		sub.setBalance(Double.parseDouble(request.getParameter("balance").trim()));
		request.setAttribute("sub", sub);
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		if (dao.update(sub.getSubname(), sub.getUsername())){
			SubscriptionLogic.getSubscriptionList(request);
			getUserList(request);
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionupdate.success"), "success");
			result = true;	
		} else {
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionupdate.failure"), "failed");
			result = true;
		}
		return result;
	}
	
	public static boolean payUserSubscription(String userName, HttpServletRequest request) {
		boolean result = false;
		String payment = request.getParameter("payment");
		if (InputValidator.isPositiveDouble(payment)){
			UserSubscriptionDAO dao = new UserSubscriptionDAO();
			if (dao.pay(payment, userName)){
				getSubscriptionForUser(request);
				request.setAttribute(MessageManager.getProperty("message.usersubscriptionpay.success"), "success");
				result = true;	
			} else {
				request.setAttribute(MessageManager.getProperty("message.usersubscriptionpay.failure"), "failed");
				result = true;
			}
		} else {
			getSubscriptionForUser(request);
			request.setAttribute(MessageManager.getProperty("message.posaitivenumeric.failure"), "failure");
			result = true;
		}
		return result;
	}
	
	public static boolean getUserSubscriptions(HttpServletRequest request){
		boolean result = false;
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		List<UserSubscription> userSubs = dao.findAll();
		if (userSubs != null){
			request.setAttribute("userSubs", userSubs);
			result = true;
		}	
		return result;
	}
	
	public static boolean blockUserSubscription(HttpServletRequest request) {
		boolean result = false;
		UserSubscription sub = new UserSubscription();
		sub.setUsername(request.getParameter("username").trim());
		sub.setSubname(request.getParameter("subname").trim());
		request.setAttribute("sub", sub);
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		if (dao.block(sub.getUsername(), sub.getSubname())){
			getUserSubscriptions(request);
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionblock.success"), "success");
			result = true;
		} else {
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionblock.failure"), "failed");
			result = true;
		}
		return result;
	}
	
	public static boolean unblockUserSubscription(HttpServletRequest request) {
		boolean result = false;
		UserSubscription sub = new UserSubscription();
		sub.setUsername(request.getParameter("username").trim());
		sub.setSubname(request.getParameter("subname").trim());
		request.setAttribute("sub", sub);
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		if (dao.unblock(sub.getUsername(), sub.getSubname())){
			getUserSubscriptions(request);
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionunblock.success"), "success");
			result = true;
		} else {
			request.setAttribute(MessageManager.getProperty("message.usersubscriptionunblock.failure"), "failed");
			result = true;
		}
		return result;
	}
	
	public static boolean getSubscriptionForUser(HttpServletRequest request) {
		boolean result = true;
		UserSubscriptionDAO dao = new UserSubscriptionDAO();
		String user = request.getSession().getAttribute("user").toString();
		UserSubscription sub = dao.findSubscription(user); 
		if (sub != null){
			SubscriptionLogic.getSubscriptionList(request);
			request.setAttribute("sub", sub);
			result = true;
		} 
		return result;
	}
	
	public static boolean getUserList(HttpServletRequest request){
		boolean result = false;
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		if (users != null){
			request.setAttribute("users", users);
			result = true;
		}	
		return result;
	} 

}
