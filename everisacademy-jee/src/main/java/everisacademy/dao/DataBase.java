package everisacademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.log4j.Logger;

public class DataBase {
	private static final Logger logger = Logger.getLogger(DataBase.class);
	private static String url = "jdbc:mysql://localhost:3306/boot?";
	private static String user = "root";
	private static String password = "";
	
	private DataBase() {
	}
	
	//Create connection
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			logger.error(ex);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting getConnection()");
			logger.debug("returning: " + connection);
		}
		return connection;
	}
	
	//create connection JBDCROWSET
	public static JdbcRowSet getRowSetConnection() {
        try(JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            jdbcRowSet.setUrl(url);
            jdbcRowSet.setUsername(user);
            jdbcRowSet.setPassword(password);
            return jdbcRowSet;
        } catch (SQLException ex) {
        	logger.error(ex);
        }
        return null;
    }
	
	//create connection CachedRowSet
    public static CachedRowSet getRowSetConnectionCached() {
        try (CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();){
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(user);
            cachedRowSet.setPassword(password);
            return cachedRowSet;
        } catch (SQLException ex) {
        	logger.error(ex);
        }
        return null;
    }

	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public static void close(Connection connection, PreparedStatement ps) {
		close(connection);
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public static void close(Connection connection, Statement stmt) {
		close(connection);
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public static void close(Connection connection, Statement stmt, ResultSet rs) {
		close(connection, stmt);
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public static void close(Connection connection, PreparedStatement ps, Statement stmt, ResultSet rs) {
		close(connection, stmt, rs);
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}
