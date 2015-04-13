package com.wdxxl.wechat.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * This UnitTest used to create DB and insert Some Test Data
 * @author King.Wang
 *
 */

public class WCMsgRecordDEVTest extends TestCase {

	private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private Properties props = new Properties();
   
    @Ignore
    protected void setUp() throws Exception{
    	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    	props.put("user", "wechat");
    	props.put("password", "wechat");
    }
    //First to Run this to Create Table and Schema
    @Ignore
    @Test
	public void testCreateTableAndSchema() throws Exception {
        // create and connect the database named derbyLookupDB
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.52.200.48:1521:orcl", props);
        // create a table
        stmt = conn.createStatement();
        try {
        	stmt.execute("CREATE TABLE WC_MSG_RECORD(TO_USER_NAME VARCHAR2(128) ,FROM_USER_NAME VARCHAR2(128), CREATE_TIME DATE, MSG_TYPE VARCHAR(50), CONTENT VARCHAR(512), MSG_ID VARCHAR(50))");
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	//Second to Check the Table is existed or not
    @Ignore
	@Test
	public void testTableExistAtFisrt() throws SQLException {
		conn = DriverManager.getConnection("dbc:oracle:thin:@10.52.200.48:1521:orcl", props);
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT COUNT(*) FROM WC_MSG_RECORD");
		while (rs.next()) {
			assertEquals("expect Result is 0","0",rs.getString(1));
		}
	}
	
	//Insert Test Data for Further Used.
    @Ignore
	@Test
	public void testInsertDatas()throws SQLException{
		conn = DriverManager.getConnection("dbc:oracle:thin:@10.52.200.48:1521:orcl", props);
        stmt = conn.createStatement();
        stmt.execute("insert into WC_MSG_RECORD values ('oqqZyuLXjSamIiF7mLbWtQHfADIs','oqqZyuLXjSamIiF7mLbWtQHfADIs',sysdate,'text','hello','12312312')");
	}
	
	
    @Ignore
	protected void tearDown() throws Exception {
		if(rs!=null){
			rs.close();
		}
		stmt.close();
		conn.close();
	}

}
