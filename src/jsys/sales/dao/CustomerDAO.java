package jsys.sales.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsys.sales.entity.Customer;

public class CustomerDAO {
	/** データベースの接続 */
	private Connection con;

	/**
	 * コンストラクタ
	 * @param con データベースの接続
	 */
	public CustomerDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 得意先を1件検索する。
	 * @param customerCode 得意先コード
	 * @return 得意先
	 * @throws SQLException
	 */
	public Customer findCustomer(String customerCode) throws SQLException {
		String sql = "SELECT * FROM customer WHERE customer_code = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Customer customer = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customerCode);
			res =  stmt.executeQuery();

			// 検索結果がある場合、戻り値に設定する。
			if(res.next()) {
				customer = new Customer(
					res.getString("customer_code"),
					res.getString("customer_name"),
					res.getString("customer_telno"),
					res.getString("customer_postalcode"),
					res.getString("customer_address"),
					res.getInt("discount_rate"),
					res.getBoolean("delete_flag"));

			}else {
				System.out.println("!!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(res != null) {
				res.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}

		return customer;
	}
}
