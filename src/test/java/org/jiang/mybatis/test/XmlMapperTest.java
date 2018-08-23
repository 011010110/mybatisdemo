package org.jiang.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jiang.mybatis.entity.User;
import org.jiang.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlMapperTest {

	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
	Logger logger = LoggerFactory.getLogger("xmlMapperTest===========================");
	private String uuid = "51cfad92-20ef-4c49-8503-c4ec6f8ef819-test";
	@Before
	public void beforeInit() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
		inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			System.out.println("获取配置文件失败！");
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void after() {
		sqlSession.close();
	}
	
	
	@Test
	public void insertUserTest() {
		User user = new User();
		user.setId(uuid);
		user.setUserName("mybatisTest2");
		user.setUserPwd("123456");
		int result = sqlSession.insert("org.jiang.mybatis.mapper.UserMapper.insertUser", user);
		System.out.println("result="+result);
		sqlSession.commit();
	}
	
	@Test
	public void xmlSelectTest() {
		Map<String,Object> params = new HashMap<>();
		params.put("username", "mybatisTest2");
		User user = sqlSession.selectOne("org.jiang.mybatis.mapper.UserMapper.getUserById", params);
		if(user!=null) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void updateUserTest() {
		User user = new User();
		user.setId(uuid);
		user.setUserName("mybatisTestUpdate");
		user.setUserPwd("123456Update");
		int result = sqlSession.update("org.jiang.mybatis.mapper.UserMapper.updateUser", user);
		System.out.println("result="+result);
		sqlSession.commit();
	}
	@Test
	public void deleteUserTest() {
		User user = new User();
		user.setId(uuid);
		int result = sqlSession.delete("org.jiang.mybatis.mapper.UserMapper.deleteUser", user);
		System.out.println("result="+result);
		sqlSession.commit();
	}
	
	@Test
	public void selectUserByIdTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserById("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test");
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试多个参数
	 */
	@Test
	public void getUserByIdNameTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserByIdName("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test","mybatisTest23");
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试map参数
	 */
	@Test
	public void getUserByMapParamTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String,Object> param = new HashMap<>();
		param.put("id", "51cfad92-20ef-4c49-8503-c4ec6f8ef819-test");
		param.put("name", "mybatisTest23");
		User user = userMapper.getUserByMapParam(param);
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试list参数
	 */
	@Test
	public void getUserByListParamTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String,Object> param = new HashMap<>();
		param.put("id", "51cfad92-20ef-4c49-8503-c4ec6f8ef819-test");
		param.put("name", "mybatisTest23");
		List<Map<String,Object>> paramList = new ArrayList<>();
		paramList.add(param);
		User user = userMapper.getUserByListParam(paramList);
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试多个参数,添加@Param注解
	 */
	@Test
	public void getUserByIdMapParamParamAnnoTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserByIdMapParamParamAnno("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test","mybatisTest23");
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试多个参数,添加且包含map类型参数
	 */
	@Test
	public void getUserByIdMapParamTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String,Object> param = new HashMap<>();
		param.put("name", "mybatisTest23");
		User user = userMapper.getUserByIdMapParam("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test",param);
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	
	/**
	 * 测试多个参数,参数分别为POJO与Map
	 */
	@Test
	public void getUserByObjectUserMapTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User paramUser = new User();
		paramUser.setId("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test");
		Map<String,Object> param = new HashMap<>();
		param.put("name", "mybatisTest23");
		User user = userMapper.getUserByObjectUserMap(paramUser,param);
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	/**
	 * 测试ResultMap
	 */
	@Test
	public void getUserByIdReturnResultMapTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserByIdReturnResultMap("51cfad92-20ef-4c49-8503-c4ec6f8ef819-test");
		Assert.assertNotNull(user);
		logger.debug("userinfo:"+user.toString());
		System.out.println(user.toString());
	}
	
	
}
