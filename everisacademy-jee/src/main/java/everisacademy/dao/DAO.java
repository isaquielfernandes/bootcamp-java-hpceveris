package everisacademy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO {
	protected Connection conn = DataBase.getConnection();
	protected PreparedStatement ps = null;
	protected CallableStatement cs = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
}
