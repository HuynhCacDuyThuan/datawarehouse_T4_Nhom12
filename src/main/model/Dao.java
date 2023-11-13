package src.main.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;


import src.main.Connect.Database;

public class Dao {
	public ArrayList<Mart> cheacked(String doamin, String date) {
		ArrayList<Mart> arrayList = new ArrayList<Mart>();
		try {
			Connection connection = Database.connection();
			String query = "select  datesx, domain,provice, giai_db, giai_nhat ,giai_nhi1 ,giai_ba1,giai_ba2 ,giai_tu1,giai_tu2,giai_tu3,giai_tu4,giai_tu5,giai_tu6,giai_tu7 ,giai_nam1 , giai_sau1,giai_sau2,giai_sau3,giai_bay1, giai_tam from dmart where domain =? and datesx =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, doamin);
			statement.setString(2, date);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Mart mart = new Mart(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getString(12), resultSet.getString(13),
						resultSet.getString(14), resultSet.getString(15), resultSet.getString(16),
						resultSet.getString(17), resultSet.getString(18) , resultSet.getString(19) ,resultSet.getString(20),resultSet.getString(21));
				arrayList.add(mart);
			}
		} catch (SQLException e) {
			// Xử lý ngoại lệ
			e.printStackTrace();
		}
		return arrayList;
	}

	public int count(String domain, String date) {
		int result = 0;

		try {
			Connection connection = Database.connection();
			String query = "SELECT COUNT(provice) FROM dmart WHERE domain = ? AND datesx = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, domain);
			statement.setString(2, date);
			ResultSet resultSet = statement.executeQuery();

			// Assuming you have a single result, so you can use an if statement
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}

			// Close resources
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// Handle the exception (e.g., log or throw a custom exception)
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		Dao dao = new Dao();
		ArrayList<Mart> arrayList = dao.cheacked("M.Nam", "2023-09-30");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).getDomain());
			System.out.println(arrayList.get(i).getGiai_sau2());
		}
		System.out.println(dao.count("M.Nam", "2023-09-30"));

	}
}
