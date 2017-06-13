package com.study.sample.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.study.sample.dao.SubscriptionDAO;
import com.study.sample.dao.UserSubscriptionDAO;
import com.study.sample.entity.Subscription;
import com.study.sample.manager.MessageManager;
import com.study.sample.util.InputValidator;


public class SubscriptionLogic extends Logic {

	public static boolean addSubscription(HttpServletRequest request) {
		Subscription sub = new Subscription();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		boolean result = true;
		if (InputValidator.isAlphaString(name) && InputValidator.isPositiveDouble(price)){
			sub.setName(name);
			sub.setPrice(Double.parseDouble(price));
			request.setAttribute("sub", sub);
			SubscriptionDAO dao = new SubscriptionDAO();
			if (dao.add(sub)){
				request.setAttribute(MessageManager.getProperty("message.subscriptionadd.success"), "success");
			} else {
				request.setAttribute(MessageManager.getProperty("message.subscriptionadd.failure"), "failed");
			}
		} else {
			if (!InputValidator.isAlphaString(name)){
				request.setAttribute(MessageManager.getProperty("message.alpha.failure"), "failure");
				result = true;
			} 
			if (!InputValidator.isPositiveDouble(price)){
				request.setAttribute(MessageManager.getProperty("message.posaitivenumeric.failure"), "failure");
				result = true;
			}
		}
		return result;
	}
	
	public static boolean getSubscriptionToEdit(HttpServletRequest request) {
		boolean result = true;
		SubscriptionDAO dao = new SubscriptionDAO();
		String subscriptionName = request.getParameter("name");
		Subscription sub = dao.findSubscription(subscriptionName); 
		if (sub != null){
			request.setAttribute("sub", sub);
		} 
		return result; 
	}
	
	public static boolean getSubscriptionList(HttpServletRequest request){
		SubscriptionDAO dao = new SubscriptionDAO();
		List<Subscription> res = dao.findAll(); 
		request.setAttribute("res", res);
		return true;
		}
	
	public static boolean removeSubscription(HttpServletRequest request) {
		boolean result = true;
		String name = request.getParameter("name");
		UserSubscriptionDAO userSubDAO = new UserSubscriptionDAO();
		SubscriptionDAO dao = new SubscriptionDAO();
		if (dao.remove(name) && userSubDAO.removeForGivenSubscription(name)){
			getSubscriptionList(request);
			request.setAttribute(MessageManager.getProperty("message.subscriptionremove.success"), "success");
		} else {
			request.setAttribute(MessageManager.getProperty("message.subscriptionremove.failure"), "failed");
		}
		return result;
	}
	
	public static boolean updateSubscription(HttpServletRequest request) {
		Subscription sub = new Subscription();
		String name = request.getParameter("name");
		String prevName = request.getParameter("prevname");
		String price = request.getParameter("price");
		if (InputValidator.isAlphaString(name) && InputValidator.isPositiveDouble(price)){
			sub.setName(name);
			sub.setPrice(Double.parseDouble(price));
			request.setAttribute("sub", sub);
			SubscriptionDAO dao = new SubscriptionDAO();
			if (dao.update(sub, prevName)){
				request.setAttribute(MessageManager.getProperty("message.subscriptionupdate.success"), "success");
			} else {
				request.setAttribute(MessageManager.getProperty("message.subscriptionupdate.failure"), "failed");
			}
		} else {
			if (!InputValidator.isAlphaString(name)){
				request.setAttribute(MessageManager.getProperty("message.alpha.failure"), "failure");
			} 
			if (!InputValidator.isPositiveDouble(price)){
				request.setAttribute(MessageManager.getProperty("message.posaitivenumeric.failure"), "failure");
			}
		}
		return true;
	}
}
