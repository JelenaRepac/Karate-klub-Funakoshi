/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Predstavlja klasu koja sluzi za ostvarivanje konekcije sa bazom.
 * @author Jelena Repac
 */
public class DbConnectionFactory {
	/**
	 * Objekat kalse Connection
	 */
    private Connection connection;
    /**
     * Staticka instanca klase DbConnectionFactory
     */
    private static DbConnectionFactory instance;

    /**
     * Besparametarski konstruktor
     */
    private DbConnectionFactory() {
    }
	/**
	 * Singleton metoda
	 * @return instancu klase DbConnectionFactory
	 */
    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    /**
     * Metoda koja vraca konekciju sa bazom.
     * @return konekcija sa bazom
     * @throws Exception Ukoliko dodje do greske prilikom konektovanja sa bazom
     */
    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/main/resources/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
