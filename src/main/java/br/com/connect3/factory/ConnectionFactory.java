package br.com.connect3.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private ConnectionFactory() {
		throw new UnsupportedOperationException();
	}

	public static Connection getConnection() {
		// 1 - Declarar objeto conexão (irá receber uma conexão após executar os passos abaixo)
		Connection connection = null;

		try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties")) {//pegar valores declarados na application.properties

			Properties prop = new Properties();
			prop.load(input);
			
			String driver = prop.getProperty("spring.datasource.url");
            String user = prop.getProperty("spring.datasource.username");
            String password = prop.getProperty("spring.datasource.password");            
			
			try {
				connection = DriverManager.getConnection(driver, user, password);
			} catch (SQLException e) {
				System.out.println("FALHA ao tentar criar conexão");
				throw new RuntimeException(e);
			}

		} catch (IOException e) {
			System.out.println("FALHA ao tentar carregar aquivos de propriedades");
			e.printStackTrace();
		}
		return connection;
	}

}
