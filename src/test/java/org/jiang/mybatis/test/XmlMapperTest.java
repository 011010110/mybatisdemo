package org.jiang.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jiang.mybatis.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XmlMapperTest {

	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
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
		User user = sqlSession.selectOne("org.jiang.mybatis.mapper.UserMapper.getUserById", uuid);
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
	
}
