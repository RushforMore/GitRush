package recEngine;
import java.sql.*;
import java.util.*;

/**
 * DAManager manages database operations.
 * @author Zhu Cai
 *
 */
public class DBManager {

	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost/";
	final static String user = "root";
	final static String pswd = "";

	/**
	 * Creates a table.
	 * @param tableName The table we want to create.
	 */
	public static void createTable(String tableName){

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + tableName, user, pswd);
			stmt = conn.createStatement();
			String drop = "DROP TABLE IF EXISTS " + tableName;
			stmt.executeUpdate(drop);
			String create = "CREATE TABLE " + tableName + 
					" (title VARCHAR(500), menu VARCHAR(500), recipe VARCHAR(500))";
			stmt.executeUpdate(create);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Updates an existing table.
	 * @param res The restaurant we want to inject into table.
	 * @param tableName The table we want to update.
	 */
	public static void updateTable(Restaurant res, String tableName){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + tableName, user, pswd);
			stmt = conn.createStatement();
			ArrayList<String> menu = res.getMenu();
			ArrayList<String> recipe = res.getRecipe();
			//			System.out.println("Starting inserting");
			String insert;
			for(int i = 0; i < menu.size(); i++){
				insert = "INSERT INTO " + tableName + " (title, menu, recipe) VALUES ('" +
						res.getName() + "','" + menu.get(i) + "','" + recipe.get(i) + "')";
				stmt.executeUpdate(insert);
			}
			//			System.out.println("finish inserting");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Makes recommendations.
	 * @param key The keyword for recommendation.
	 * @param tableName The table we want to consult from.
	 */
	public static void recommend(String key, String tableName){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + tableName, user, pswd);
			stmt = conn.createStatement();
			String search = "SELECT title, menu, recipe FROM " + tableName;
			ResultSet res = stmt.executeQuery(search);
			String menu;
			String recipe;
			while(res.next()){
				menu = res.getString("menu");
				recipe = res.getString("recipe");
				if(menu.toLowerCase().contains(key) || recipe.toLowerCase().contains(key)){
					System.out.println(menu + " from " + res.getString("title"));
				}
			}

			//			System.out.println("finish searching");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
