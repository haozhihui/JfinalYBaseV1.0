package com.wide.common.model;

import java.util.List;

import com.wide.common.model.base.BaseUserRole;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class UserRole extends BaseUserRole<UserRole> {
	public static final UserRole dao = new UserRole();

	public List<UserRole> findByRoleId(String id) {
		// TODO Auto-generated method stub
		List<UserRole> list =find("select * from sys_user_role where role_id = ? ",id);
		return list;
	}
}
