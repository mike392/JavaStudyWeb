package com.study.sample.action;

import com.study.sample.action.impl.EmptyAction;
import com.study.sample.action.impl.LoginAction;
import com.study.sample.action.impl.LogoutAction;
import com.study.sample.action.impl.ParseAction;
import com.study.sample.action.impl.SignupAction;
import com.study.sample.action.impl.TimeAction;
import com.study.sample.action.impl.UploadAction;

public enum ActionEnum {
	LOGIN ( new LoginAction() ),
	SIGNUP(new SignupAction()),
	LOGOUT (new LogoutAction() ),
	PARSE (new ParseAction()),
	TIME (new TimeAction()),
	UPLOAD(new UploadAction()),
	EMPTY (new EmptyAction());
		
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
