package com.ads.wsrequete.postgres.logdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import com.ads.wsrequete.conf.ConfLoader;

public class PostgresLogUtils {

	// Parametres
	private static String jdbcUrl;
	private static String username;
	private static String password;

    private static final Logger LOGGER = Logger.getLogger(PostgresLogUtils.class.getName());

    private static void initWithConf() throws Exception {
    	Properties prop = ConfLoader.getConfGit();
    	if (null != prop) {
    		username = prop.getProperty("postgres.log.username");
    		password = prop.getProperty("postgres.log.password");
    		jdbcUrl = prop.getProperty("postgreS.log.jdbc.url");
    	}
    }
    
    /**
     * Permet d'executer une requête dans la BDD via une connexion préalablement établie.
     * @param connection
     * @param query
     * @return
     * @throws Exception 
     */
    public static void executeQuery(String query) throws Exception {
    	
    	initWithConf();
    	
    	Connection connection = createConnection(username, password);

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            
        } catch (Exception e) {
        	e.printStackTrace();
        }

    }
    
    /**
     * Créé une connexion avec la base de données PostgreSQL.
     * @param username
     * @param password
     * @return
     */
    private static Connection createConnection(String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                return connection;
            } else {
                LOGGER.severe("Connection to PostgreSQL failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}