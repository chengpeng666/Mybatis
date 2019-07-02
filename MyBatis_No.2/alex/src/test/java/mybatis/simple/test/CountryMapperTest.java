package mybatis.simple.test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mybatis.simple.model.Country;

public class CountryMapperTest {
	private static SqlSessionFactory sessionFactory;
	
	@BeforeClass
	public static void init(){
		try{
			//通过配置文件获取连接数据库连接信息
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			
			//通过配置信息来构建一个SqlSessionFactory
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		//通过SqlSessionFactory打开一个会话
		SqlSession openSession = sessionFactory.openSession();
		
		try{
			//openSession.selectList("selectAll");
			//List<Country> countryList = openSession.selectList("selectAll");
			Map para = new HashMap<Object,Object>();
			para.put("id",1);
			para.put("countryname","中国");
			System.out.println(openSession.selectOne("selectCountrycode",para).toString());

			//printCountryList(countryList);

		}finally{
			//关闭session会话
			openSession.close();
		}
	}
	
	private void printCountryList(List<Country> countryList){
		for(Country country : countryList){
			System.out.println(country.getId()+"--"+country.getCountryname()+"--"+country.getCountrycode());
		}
	}

}
