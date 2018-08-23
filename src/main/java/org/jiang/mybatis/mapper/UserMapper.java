package org.jiang.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jiang.mybatis.entity.User;

public interface UserMapper {

	
	User getUserById(String id);
	
	User getUserByIdName(String id,String name);
	
	User getUserByIdMapParamParamAnno(@Param("id")String id,@Param("name")String name);
	
	User getUserByIdMapParam(@Param("id")String id,@Param("mapParam")Map<String,Object> mapParam);
	
	User getUserByMapParam(Map<String,Object> mapParam);
	
	User getUserByObjectUserMap(@Param("user")User user,@Param("mapParam")Map<String,Object> param);
	
	User getUserByListParam(List<Map<String,Object>> listParam);
	
	User getUserByIdReturnResultMap(String id);
	
	long insertUser(User user);
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
}
