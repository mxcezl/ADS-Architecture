package com.ads.wsupdatedelete.traitement;

import org.springframework.boot.logging.LogLevel;

import com.ads.wsupdatedelete.loglake.LoggerLake;
import com.ads.wsupdatedelete.pojo.Patient;
import com.ads.wsupdatedelete.pojo.PatientDeleteOdtOutput;
import com.ads.wsupdatedelete.pojo.PatientUpdateOdtOutput;
import com.ads.wsupdatedelete.postgres.PostgresUtils;

public class PatientTraitement {

	public static PatientUpdateOdtOutput updatePatient(Patient pat) throws Exception {
		
		String queryUpdate = PostgresUtils.createQueryUdate(pat);
		
		PatientUpdateOdtOutput patOut = new PatientUpdateOdtOutput();
		try {			
			PostgresUtils.executeQuery(queryUpdate);

			patOut.setMessage("Patient mis à jour avec succès.");
			patOut.setUpdated(true);
			
		} catch (Exception e) {
			
			LoggerLake.log(LogLevel.ERROR, "La mise à jour du patient " + pat.getNUMPAS() + " a echouée.");
			patOut.setMessage("Erreur lors de la mise à jour du patient.");
			patOut.setUpdated(false);			
		}
		
		return patOut;
	}

	public static PatientDeleteOdtOutput deletePatient(Patient pat) throws Exception {
		
		String queryDelete = PostgresUtils.createQueryDelete(pat);
		
		PatientDeleteOdtOutput patOut = new PatientDeleteOdtOutput();
		try {			
			PostgresUtils.executeQuery(queryDelete);
			
			patOut.setMessage("Patient supprimé avec succès.");
			patOut.setDeleted(true);
		} catch (Exception e) {

			LoggerLake.log(LogLevel.ERROR, "La suppression du patient " + pat.getNUMPAS() + " a echouée.");
			patOut.setMessage("Erreur lors de la suppression du patient.");
			patOut.setDeleted(false);			
		}
		
		return patOut;
	}
}
