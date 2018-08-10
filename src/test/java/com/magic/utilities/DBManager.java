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

public class DBManager {

	public Connection createConnectionForProperty()
	{
		String port=":3311/";
		String localIp = "jdbc:mysql://192.168.207.178";
		String username = "qc_akashkansal";
		String password = "lasnakhsakaY@720";
		String Schema = "property";
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
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

	public Map<String,String> executeQuerySRow(String query)
	{
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
					System.out.println(rowData.getColumnName(i)+":"+rs.getString(rowData.getColumnName(i)));
					map.put(rowData.getColumnName(i),rs.getString(rowData.getColumnName(i)));
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

	public static void main(String[] args) {

		DBManager db = new DBManager();
		String Query ="select * from tpsbm order by createdate desc";
		db.executeQuerySRow(Query);
	}

}
