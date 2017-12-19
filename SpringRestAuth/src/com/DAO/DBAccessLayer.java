package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class DBAccessLayer {


	public List<List<String>> fetchList(String sql, List<String> prepValues,List<String> prepDataType) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<String> list = null;
		int prepValueCount = prepValues.size();
		int columnCount = 0;
		List<List<String>> result = new ArrayList<>();
		try {
			conn = OracleConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < prepValueCount; i++) {

				String dataType = prepDataType.get(i);

				if (dataType.equals("string")) {
					pstmt.setString(i + 1, prepValues.get(i));
				} else if (dataType.equals("double")) {
					pstmt.setDouble(i + 1, Double.parseDouble(prepValues.get(i)));
				} else if (dataType.equals("int")) {
					pstmt.setInt(i + 1, Integer.parseInt( prepValues.get(i)));
				}
			}

			rs = pstmt.executeQuery();

			rsmd = rs.getMetaData();

			columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				list = new ArrayList<>(columnCount);

				for (int i = 1; i <= columnCount; i++) {
					list.add(i - 1, rs.getString(i));
				}
				result.add(list);

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			closeRS(rs);
			rs = null;
			if (rsmd != null) {
				rsmd = null;
			}
			closePStmt(pstmt);
			pstmt = null;
			closeConnection(conn);
			conn = null;
		}
		return result;

	}


	public int ExecuteInsertUpdate(String sql, List<String> prepValues,List<String> prepDataType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			conn = OracleConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < prepDataType.size(); i++) {

				String dataType = prepDataType.get(i);

				if (dataType.equals("string")) {
					pstmt.setString(i + 1, prepValues.get(i));
				} else if (dataType.equals("double")) {
					pstmt.setDouble(i + 1, Double.parseDouble( prepValues.get(i)));
				} else if (dataType.equals("int")) {
					pstmt.setInt(i + 1, Integer.parseInt( prepValues.get(i)));
				}
			}


			updateCount = pstmt.executeUpdate();

			if (updateCount == 0) {

				updateCount = -1;
			}
		} catch (SQLException sqlicvee) {

			updateCount = 11;
			sqlicvee.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closePStmt(pstmt);
			pstmt = null;
			closeConnection(conn);
			conn = null;
		}
		return updateCount;
	}

	private void closeRS(ResultSet rs) {
		if (rs == null)
			return;
		try {
			rs.close();
			rs = null;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void closePStmt(PreparedStatement pstmt) {
		if (pstmt == null)
			return;
		try {
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void closeConnection(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
