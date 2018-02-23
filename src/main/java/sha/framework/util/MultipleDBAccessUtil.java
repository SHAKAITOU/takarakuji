package sha.framework.util;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sha.framework.db.DBConfig;

@Service
public class MultipleDBAccessUtil {
	
	private SqlSessionTemplate db1SQlSesstion;
	
	private SqlSessionTemplate db2SQlSesstion;
	

	private List<Class<? extends Object>> alldb1Classes;
	

	private List<Class<? extends Object>> alldb2Classes;

	@Autowired
	private void getDB1SqlSession(@Qualifier(DBConfig.DOMAIN_SQLSESSION_BEAN_NAME) SqlSessionTemplate sqlSession) {
		this.db1SQlSesstion = sqlSession;
		alldb1Classes = ClassFinder.find(DBConfig.DOMAIN_MAPPER_PACKAGE);
	}
	
	
	@Autowired
	private void getDB2SqlSession(@Qualifier(DBConfig.LOTO_SQLSESSION_BEAN_NAME) SqlSessionTemplate sqlSession) {
		this.db2SQlSesstion = sqlSession;
		alldb2Classes = ClassFinder.find(DBConfig.LOTO_MAPPER_PACKAGE);
	}
	
	
	@SuppressWarnings("unchecked")
	public Object getMapper(@SuppressWarnings("rawtypes") Class clazz) {
		if(alldb1Classes.contains(clazz)) {
			return this.db1SQlSesstion.getMapper(clazz);
		}else if(alldb2Classes.contains(clazz)) {
			return this.db2SQlSesstion.getMapper(clazz);
		}else {
			return null;
		}
	}
	
	public SqlSessionTemplate getSqlSession(String sqlSessionNm) {
		if(sqlSessionNm.equals(DBConfig.DOMAIN_SQLSESSION_BEAN_NAME)) {
			return db1SQlSesstion;
		}else {
			return db2SQlSesstion;
		}
	}
}
