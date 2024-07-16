package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	
	// 싱글톤 패턴 : 객체 1개만 생성해서 이용하자
	static DBService single = null;
	
	DataSource ds = null;
	
	public static DBService getInstance() {
		if(single == null) single = new DBService();	// 없으면 니 객체를 생성해라		
		return single;
	}
	
	// 외부에서 객체 생성을 못하게 하기 위해 private 접근제어자 사용
	private DBService() {
		
		try {
			// JNDI을 이용해s서 DataSource정보를 얻어온다
			// 1. InitialContext생성(JNDI->interface추출객체)
			InitialContext ic = new InitialContext();
			
			// 2. Context정보(context.xml) 얻어온다
			Context context = (Context) ic.lookup("java:comp/env");
			
			// 3. naming을 이용해서 DataSource
			ds = (DataSource) context.lookup("jdbc/oracle_test");
			
			// 2+3 한번에
			// ds = (DataSource) ic.lookup("java:comp/env/jdbc/oracle_test");
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
	}// end - Contructor
	
	public Connection getConnection() throws SQLException {
		
		// DataSource를 이용해서 BasicDataSource가 
		// 관리하고 있는 커넥션을 요청
		return ds.getConnection();
	}
	
}
