package com.magic.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.relevantcodes.extentreports.LogStatus;

public class DBManager {

	public Connection createConnectionForProperty()
	{
		String port=":3311/";
		String localIp = "jdbc:mysql://192.168.207.178";
		String username = "app_readuser";
		String password = "@ppr3@dU$eROnu@tdEEbee";
		String Schema = "property";
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(localIp+port+Schema,username,password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(con==null)
		{
			System.out.println("No Connection");
		}

		return con;
	}

	public void executeUpdate()
	{
		String Query1 = "update property.tpmvt set ATTEMPTCOUNT=0 where mvtmobile IN (9891699692,9953902086,8826399999, 8963485946, 8377974608, 7065524041, 7065524043, 9977007799, 9717787952, 9650584940, 7053549558, 9999999919, 8979921513,8979921510,8979921511,8979921512,8979921514,8979921515,8979921513)";
		String Query2 = "update tpappnotifconfig set NCNOTIFFREQ=0  where NCAPPCNDRFNUM IN (9891699692,9953902086,8826399999, 8963485946, 8377974608, 7065524041, 7065524043, 9977007799, 9717787952, 9650584940, 7053549558, 9999999919, 8979921513,8979921510,8979921511,8979921512,8979921514,8979921515,8979921513)";
		String Query3 = "update tpubi_login_attempt set ubilafailcount=0  where UbiLAUbiLogin IN ('rishproperty','vaibhuec@gmail.com','abc@abc.abc','lheeranandani@hotmail.com','mbmobiletesters@gmail.com','sanity20aug@mailinator.com','sanity21aug@mailinator.com','magic.aamir@gmail.com','testing4adobe@gmail.com','kansalb@mailinator.com','ashulivep@gmail.com','magicbricks@mb.com','temp_temp_8189199087@indiatimes.com','akash.kansal@yopmail.com')";
		Connection c = createConnectionForProperty();
		Statement stm = null;
		try {
			stm = c.createStatement();
			stm.executeUpdate(Query1);
			stm.executeUpdate(Query2);
			stm.executeUpdate(Query3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Update(String Query) {
		System.out.println("Updating:- "+Query);
		Connection c = createConnectionForProperty();
		Statement stm = null;
		try {
			stm = c.createStatement();
			stm.executeUpdate(Query);
			closeConnection(c, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Map<String,String> executeQuerySRow(String query)
	{
		//System.out.println("dbdbdbdbdbdbdbdbdbdbdbdbdbddbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdb");
		System.out.println(query);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}
		Connection c = createConnectionForProperty();
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rowData;
		Map<String,String> map = null;

		try {
			stm = c.createStatement();
			rs = stm.executeQuery(query+" limit 1");
			rowData = rs.getMetaData();
			int column = rowData.getColumnCount();
			while (rs.next())
			{
				map = new LinkedHashMap<String,String>();
				for(int i=1;i<column;i++)
				{
					System.out.println(rowData.getTableName(i)+"."+rowData.getColumnLabel(i)+":"+rs.getString(rowData.getColumnLabel(i)));
					map.put(rowData.getTableName(i)+"."+rowData.getColumnLabel(i),rs.getString(rowData.getColumnLabel(i)));
				}
			}
			closeConnection(c, rs, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<LinkedHashMap<String,String>> executeQueryAllRow(String query)
	{
		Connection c = createConnectionForProperty();
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rowData;
		List<LinkedHashMap<String, String>> list = new LinkedList<LinkedHashMap<String,String>>();
		LinkedHashMap<String, String> map = null;
		try {
			stm = c.createStatement();
			rs = stm.executeQuery(query);
			rowData = rs.getMetaData();
			int column = rowData.getColumnCount();
			while (rs.next())
			{
				map = new LinkedHashMap<String,String>();
				for(int i=1;i<column;i++)
				{
					map.put(rowData.getColumnName(i),rs.getString(rowData.getColumnName(i)));
				}
				list.add(map);
			}
			closeConnection(c, rs, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public void closeConnection(Connection con,ResultSet rs,Statement st)
	{
		try
		{
			con.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
		System.out.println("Connection Closed");
	}

	public void executeMobilEmailUpdate(String email,String mobile,String ubirfnum) {


		System.out.println("Updating Mobile and email id");
		String Query1 = "update property.tpubi set UBIPRIMARYMOBILE="+mobile+" where UBILOGIN=\""+email+"\" and UBIRFNUM="+ubirfnum;
		System.out.println(Query1);
		Connection c = createConnectionForProperty();
		Statement stm = null;
		try {
			stm = c.createStatement();
			stm.executeUpdate(Query1);
			closeConnection(c, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void closeConnection(Connection con,Statement st)
	{
		closeConnection(con,null,st);
	}

	/*public static void main(String[] args) {

		DBManager db = new DBManager();
		String Query ="select * from tpsbm order by createdate desc";
		db.executeQuerySRow(Query);
	}*/
	
	public void otpMobileReset(String mobileNo)	
	{
		Update("update property.tpmvt set ATTEMPTCOUNT=0,MVTOTPUSED='N' where mvtmobile IN ('"+mobileNo+"')");
		Update("update tpappnotifconfig set NCNOTIFFREQ=0  where NCAPPCNDRFNUM IN ('"+mobileNo+"')");				
		ExtentTestManager.getTest().log(LogStatus.INFO,"Otp Reset",mobileNo);
	}

	public String getMobileOtp(String mobileNo)
	{

		Map<String, String> otpMap = executeQuerySRow("Select * from property.tpmvt where mvtmobile='"+mobileNo+"'");
		String otp = otpMap.get("tpmvt.EXFIELD2");
		return otp;
			
	}
}
