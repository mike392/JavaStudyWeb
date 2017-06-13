package com.study.sample.action;

import com.study.sample.action.impl.ChangeLocaleAction;
import com.study.sample.action.impl.EmptyAction;
import com.study.sample.action.impl.LocaleAction;
import com.study.sample.action.impl.LoginAction;
import com.study.sample.action.impl.LogoutAction;
import com.study.sample.action.impl.SignupAction;

public enum ActionEnum {
	LOGIN ( new LoginAction() ),
	SIGNUP(new SignupAction()),
	LOGOUT (new LogoutAction() ),
	EMPTY (new EmptyAction()),
	LOCALECHANGE(new ChangeLocaleAction()),
	LOCALE(new LocaleAction());
		
	Action action;
		
	private ActionEnum(Action action){
		this.setAction(action);
	}
		
	public void setAction(Action action) {
		this.action = action;
	}
		
	public Action getAction() {
		return action;
	}
}