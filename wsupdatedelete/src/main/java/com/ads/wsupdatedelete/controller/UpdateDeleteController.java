package com.ads.wsupdatedelete.controller;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.wsupdatedelete.loglake.LoggerLake;
import com.ads.wsupdatedelete.pojo.Patient;
import com.ads.wsupdatedelete.pojo.PatientDeleteOdtOutput;
import com.ads.wsupdatedelete.pojo.PatientUpdateOdtOutput;
import com.ads.wsupdatedelete.traitement.PatientTraitement;

@RestController
@RequestMapping("/api")
public class UpdateDeleteController {

	@PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public PatientUpdateOdtOutput updatePatient(@RequestBody Patient pat) throws Exception {
		
		LoggerLake.log(LogLevel.INFO, "Modification du patient" + pat.getNUMPAS());
		
		checkInputs(pat);
		
		PatientUpdateOdtOutput ret = PatientTraitement.updatePatient(pat);

		LoggerLake.log(LogLevel.INFO, "Modification effectuée avec succès.");
		
		return ret;
	}

	@DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public PatientDeleteOdtOutput deletePatient(@RequestBody Patient pat) throws Exception {
		
		checkInputs(pat);
		
		PatientDeleteOdtOutput ret = PatientTraitement.deletePatient(pat);
		
		return ret;
	}

	private void checkInputs(Patient pat) throws Exception {
		if (null == pat || !Patient.isValid(pat)
				|| null == pat.getNOM() || pat.getNOM().isEmpty()
				|| null == pat.getPRENOM() || pat.getPRENOM().isEmpty()) {
			throw new Exception("Le patient en paramètre est incorrect. (nom ou prenom null).");
		}
	}
}