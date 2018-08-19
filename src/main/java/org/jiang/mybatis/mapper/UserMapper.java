package org.jiang.mybatis.mapper;

import org.jiang.mybatis.entity.User;

public interface UserMapper {

	
	User getUserById(String id);
	
	long insertUser(User user);
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
}
