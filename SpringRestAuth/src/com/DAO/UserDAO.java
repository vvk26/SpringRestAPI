package com.DAO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	DBAccessLayer db = null;

	public UserDAO() {
		db = new DBAccessLayer();
	}

	public boolean checkLogin(String userName, String password){

		String sql = "Select count(*) FROM USERDETAILS WHERE USERNAME = ? AND PASSWRD = ?";

		List<String> values = new ArrayList<>();
		values.add(userName);
		values.add(password);

		List<String> datatypes = new ArrayList<>();
		datatypes.add("string");
		datatypes.add("string");

		List<List<String>> result = db.fetchList(sql, values, datatypes);

		String flag = result.get(0).get(0);

		if(1 == Integer.parseInt(flag))
			return true;
		else
			return false;



	}

	public String getIntrest(String userName, String password){

		String sql = "Select INTEREST FROM USERDETAILS WHERE USERNAME = ?";

		List<String> values = new ArrayList<>();
		values.add(userName);

		List<String> datatypes = new ArrayList<>();
		datatypes.add("string");

		List<List<String>> result = db.fetchList(sql, values, datatypes);

		String interest = result.get(0).get(0);

		return interest;


	}

	public int insertIntrest(String userName, String interest){

		String sql = "UPDATE USERDETAILS SET INTEREST = ? WHERE USERNAME = ?";

		List<String> values = new ArrayList<>();
		values.add(interest);
		values.add(userName);

		List<String> datatypes = new ArrayList<>();
		datatypes.add("string");
		datatypes.add("string");

		int result = db.ExecuteInsertUpdate(sql, values, datatypes);


		return result;


	}

	public int insertUser(String userName, String password){

		String sql = "INSERT INTO USERDETAILS(USERNAME,PASSWRD) VALUES (?,?)";

		List<String> values = new ArrayList<>();
		values.add(userName);
		values.add(password);

		List<String> datatypes = new ArrayList<>();
		datatypes.add("string");
		datatypes.add("string");

		int result = db.ExecuteInsertUpdate(sql, values, datatypes);


		return result;


	}

}
