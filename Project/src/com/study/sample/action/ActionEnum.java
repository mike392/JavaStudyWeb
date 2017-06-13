package com.study.sample.action;

import com.study.sample.action.impl.BlockUserSubscriptionAction;
import com.study.sample.action.impl.ChangeLocaleAction;
import com.study.sample.action.impl.EmptyAction;
import com.study.sample.action.impl.LocaleAction;
import com.study.sample.action.impl.LoginAction;
import com.study.sample.action.impl.LogoutAction;
import com.study.sample.action.impl.SignupAction;
import com.study.sample.action.impl.SubscriptionAddAction;
import com.study.sample.action.impl.SubscriptionEditAction;
import com.study.sample.action.impl.SubscriptionListAction;
import com.study.sample.action.impl.SubscriptionRemoveAction;
import com.study.sample.action.impl.SubscriptionUpdateAction;
import com.study.sample.action.impl.SubscriptionViewAction;
import com.study.sample.action.impl.SubscriptionViewModifyAction;
import com.study.sample.action.impl.SubscriptionViewPayAction;
import com.study.sample.action.impl.UnblockUserSubscriptionAction;
import com.study.sample.action.impl.UserAddSubscriptionAction;
import com.study.sample.action.impl.UserEditSubscriptionAction;
import com.study.sample.action.impl.UserManagementAction;
import com.study.sample.action.impl.UserRemoveSubscriptionAction;
import com.study.sample.action.impl.UserUpdateSubscriptionAction;


public enum ActionEnum {
	LOGIN ( new LoginAction() ),
	SIGNUP(new SignupAction()),
	LOGOUT (new LogoutAction() ),
	EMPTY (new EmptyAction()),
	LOCALE_CHANGE(new ChangeLocaleAction()),
	LOCALE(new LocaleAction()),
	SUBSCRIPTION_LIST(new SubscriptionListAction()),
	SUBSCRIPTION_EDIT(new SubscriptionEditAction()),
	SUBSCRIPTION_REMOVE(new SubscriptionRemoveAction()),
	SUBSCRIPTION_UPDATE(new SubscriptionUpdateAction()),
	SUBSCRIPTION_ADD(new SubscriptionAddAction()),
	SUBSCRIPTION_VIEW(new SubscriptionViewAction()),
	SUBSCRIPTION_VIEW_MODIFY(new SubscriptionViewModifyAction()),
	SUBSCRIPTION_VIEW_PAY(new SubscriptionViewPayAction()),
	USER_MANAGEMENT(new UserManagementAction()),
	USER_ADD_SUBSCRIPTION(new UserAddSubscriptionAction()),
	USER_UPDATE_SUBSCRIPTION(new UserUpdateSubscriptionAction()),
	USER_EDIT_SUBSCRIPTION(new UserEditSubscriptionAction()),
	USER_REMOVE_SUBSCRIPTION(new UserRemoveSubscriptionAction()),
	BLOCK_USER_SUBSCRIPTION(new BlockUserSubscriptionAction()),
	UNBLOCK_USER_SUBSCRIPTION(new UnblockUserSubscriptionAction());
		
	private Action action;
		
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
