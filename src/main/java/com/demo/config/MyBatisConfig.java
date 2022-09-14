package com.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	
	private final DatabaseConfig db;
	
	@Autowired
	public MyBatisConfig(DatabaseConfig db) {
		this.db = db;
	}

	@Bean
	public SqlSessionFactory sqlSession() throws Exception{
		var config = new SqlSessionFactoryBean();
		config.setDataSource(db.dataSource());
		return config.getObject();
	}
}
