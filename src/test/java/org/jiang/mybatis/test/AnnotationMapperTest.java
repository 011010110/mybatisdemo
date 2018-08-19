package org.jiang.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

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

public class AnnotationMapperTest {
	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
	UserMapper userMapper;

	@Before
	public void beforeInit() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			System.out.println("获取配置文件失败！");
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
		Assert.assertNotNull(userMapper);
	}

	@After
	public void after() {
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void userSelectTest() {
		User user = userMapper.getUserById("123");
		Assert.assertNotNull(user);
		System.out.println(user.toString());
	}
	
	@Test
	public void updateUserTest() {
		User user = new User();
		user.setId("123");
		user.setUserName("mapperupdate");
		boolean result = userMapper.updateUser(user);
		Assert.assertTrue(result);
	}
}
