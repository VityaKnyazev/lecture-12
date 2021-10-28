package by.itacademy.javaenterprise.knyazev.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class DbConnection {
	
	private DataSource hikariDataSource;	
	private static DbConnection dbConnection;
		
	private DbConnection() { }
	
	public static DbConnection getDBO() {
		if (dbConnection == null) {
			synchronized (DbConnection.class) {
				if (dbConnection == null) {
					dbConnection = new DbConnection();
				}
			}			
		}
				
		return dbConnection;
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection =  hikariDataSource.getConnection();
		connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		return connection;
	}	
	
	public void setHikariDataSource(HikariDataSource hikariDataSource) {
		this.hikariDataSource = hikariDataSource;
	}
}
