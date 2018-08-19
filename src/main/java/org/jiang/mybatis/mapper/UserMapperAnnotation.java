package org.jiang.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.jiang.mybatis.entity.User;

public interface UserMapperAnnotation {

	@Select("select * from test_hibernate_user where id = #{id}")
	User getUserById(String id);
	
	long insertUser(User user);
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
}
