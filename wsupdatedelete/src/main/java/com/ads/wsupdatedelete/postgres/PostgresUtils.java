package com.ads.wsupdatedelete.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import com.ads.wsupdatedelete.conf.ConfLoader;
import com.ads.wsupdatedelete.pojo.Patient;

public class PostgresUtils {

	public static final String DELIMITER = "#@#";

	// Parametres
	private static String jdbcUrl;
	private static String username;
	private static String password;

    private static final Logger LOGGER = Logger.getLogger(PostgresUtils.class.getName());

    private static void initWithConf() throws Exception {
    	Properties prop = ConfLoader.getConfGit();
    	if (null != prop) {
    		username = prop.getProperty("postgres.pat.username");
    		password = prop.getProperty("postgres.pat.password");
    		jdbcUrl = prop.getProperty("postgreS.pat.jdbc.url");
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
            
            connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new Exception(e.getMessage());
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
    
	public static String createQueryUdate(Patient pat) {
		String baseQuery = "UPDATE public.patient SET ipp='?1', datemsg='?2', nummsg='?3', nom='?4', prenom='?5', intit='?6', nommar='?7', ddn='?8', sexe='?9', iep='?10', adr1='?11', adr2='?12', cp='?13', ville='?14', pays='?15', tel='?16', paysn='?17', dds='?18', uf='?19', chambre='?20', lit='?21', ufmed='?22', numpas='?23' WHERE ";
		
		baseQuery = replaceParams(baseQuery, pat);
		
		baseQuery += "patient.nom = '" + pat.getNOM() + "' ";
		baseQuery += "AND patient.prenom = '" + pat.getPRENOM() + "';";
		
		return baseQuery;
	}

	private static String replaceParams(String query, Patient pat) {
		
		query = query.replace("?10", pat.getIEP());
		query = query.replace("?11", pat.getADR1());
		query = query.replace("?12", pat.getADR2());
		query = query.replace("?13", pat.getCP());
		query = query.replace("?14", pat.getVILLE());
		query = query.replace("?15", pat.getPAYS());
		query = query.replace("?16", pat.getTEL());
		query = query.replace("?17", pat.getPAYSN());
		query = query.replace("?18", pat.getDDS());
		query = query.replace("?19", pat.getUF());
		query = query.replace("?20", pat.getCHAMBRE());
		query = query.replace("?21", pat.getLIT());
		query = query.replace("?22", pat.getUFMED());
		query = query.replace("?23", pat.getNUMPAS());
		
		query = query.replace("?1", pat.getIPP());
		query = query.replace("?2", pat.getDATEMSG());
		query = query.replace("?3", pat.getNUMMSG());
		query = query.replace("?4", pat.getNOM());
		query = query.replace("?5", pat.getPRENOM());
		query = query.replace("?6", pat.getINTIT());
		query = query.replace("?7", pat.getNOMMAR());
		query = query.replace("?8", pat.getDDN());
		query = query.replace("?9", pat.getSEXE());
		
		return query;
	}

	public static String createQueryDelete(Patient pat) {
		String baseQuery = "DELETE FROM public.patient WHERE ";
		
		
		baseQuery += "patient.nom = '" + pat.getNOM() + "' ";
		baseQuery += "AND patient.prenom = '" + pat.getPRENOM() + "';";
		
		return baseQuery;
	}
}