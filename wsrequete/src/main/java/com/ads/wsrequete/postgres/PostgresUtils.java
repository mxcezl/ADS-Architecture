package com.ads.wsrequete.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.boot.logging.LogLevel;

import com.ads.wsrequete.conf.ConfLoader;
import com.ads.wsrequete.loglake.LoggerLake;
import com.ads.wsrequete.pojo.Patient;

public class PostgresUtils {

	public static final String DELIMITER = "#@#";

	// Parametres
	private static String jdbcUrl;
	private static String username;
	private static String password;

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
    public static String executeQuery(String query) throws Exception {
    	
    	initWithConf();
    	
    	Connection connection = createConnection(username, password);
    	
        String data = "";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
            	for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {            		
            		data += rs.getString(i) + DELIMITER;
            	}
            	data = data.substring(0, data.length() - 3);
            }
            
            if (data.isEmpty()) {
            	data = "NO_RESULT";
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            data = "NO_RESULT";
            e.printStackTrace();
        }

        System.out.println(data);
        return data;
    }
    
    /**
     * Créé une connexion avec la base de données PostgreSQL.
     * @param username
     * @param password
     * @return
     * @throws Exception 
     */
    private static Connection createConnection(String username, String password) throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                return connection;
            } else {
                LoggerLake.log(LogLevel.ERROR, "Connection to PostgreSQL failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Convertit le retour de la BDD en un objet patient.
     * Si aucun patient n'a été trouvé, tous les champs du patient sont renseignés à null.
     * 
     * @param strPat
     * @return
     * @throws Exception 
     */
	public static Patient patientFromDBReturn(String strPat) throws Exception {
		
		Patient pat = new Patient();

		if (!strPat.isEmpty() && !"NO_RESULT".equals(strPat)) {
			
			List<String> patInfo = Arrays.asList(strPat.split(PostgresUtils.DELIMITER));	
			
			pat.setIPP(patInfo.get(0));
			pat.setDATEMSG(patInfo.get(1));
			pat.setNUMMSG(patInfo.get(2));
			pat.setNOM(patInfo.get(3));
			pat.setPRENOM(patInfo.get(4));
			pat.setINTIT(patInfo.get(5));
			pat.setNOMMAR(patInfo.get(6));
			pat.setDDN(patInfo.get(7));
			pat.setSEXE(patInfo.get(8));
			pat.setIEP(patInfo.get(9));
			pat.setADR1(patInfo.get(10));
			pat.setADR2(patInfo.get(11));
			pat.setCP(patInfo.get(12));
			pat.setVILLE(patInfo.get(13));
			pat.setPAYS(patInfo.get(14));
			pat.setTEL(patInfo.get(15));
			pat.setPAYSN(patInfo.get(16));
			pat.setDDS(patInfo.get(17));
			pat.setUF(patInfo.get(18));
			pat.setCHAMBRE(patInfo.get(19));
			pat.setLIT(patInfo.get(20));
			pat.setUFMED(patInfo.get(21));
			pat.setNUMPAS(patInfo.get(22));
			
			LoggerLake.log(LogLevel.INFO, "Patient récupéré de la BDD : " + pat.getNUMPAS());
	        
		}
		return pat;
	}
}