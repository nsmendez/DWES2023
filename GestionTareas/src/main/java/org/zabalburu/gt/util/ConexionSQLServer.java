package org.zabalburu.gt.util;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public final class ConexionSQLServer {
	private static Connection cnn = null;

	public static Connection getConexion() {
		if (cnn == null) {
			SQLServerDataSource ds = new SQLServerDataSource();
	        ds.setUser("sa");
	        ds.setPassword("tiger");
	        ds.setServerName("localhost");
	        ds.setPortNumber(1234);
	        ds.setDatabaseName("Northwind");
                ds.setTrustServerCertificate(true); // Necesario en las nuevas versiones de SQL Server si no tenemos un certificado de seguridad
	        try {
	        		cnn = ds.getConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return cnn;
	}
}
