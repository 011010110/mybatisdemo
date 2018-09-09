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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheMapperTest {

	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
	Logger logger = LoggerFactory.getLogger("cacheMapperTest===========================");
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
	
	/**
	 * 测试一级缓存
	 * 一级缓存，存在与sqlSession会话期间
	 */
	@Test
	public void namespaceCacheTest() {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("开启另外的sqlsession，执行同样的查询操作");
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserMapper userMapperOtherSession = sqlSession2.getMapper(UserMapper.class);
		
		User user = userMapper.getUserById(uuid);
		System.out.println(user.toString());
		sqlSession.close();
		
		User user2 = userMapperOtherSession.getUserById(uuid);
		System.out.println(user2.toString());
		System.out.println("由此可见同样在没有开启二级缓存情况下，不同sqlSession中的相同查询操作不会共用缓存");
		sqlSession2.close(); 
	}
	
	
}
