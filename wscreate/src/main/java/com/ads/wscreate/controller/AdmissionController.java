package com.ads.wscreate.controller;

import java.util.Date;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.wscreate.loglake.LoggerLake;
import com.ads.wscreate.pojo.Patient;
import com.ads.wscreate.traitement.Hl7Gen;

@RestController
@RequestMapping("/api")
public class AdmissionController {

	/**
	 * Ressource de création d'un patient
	 *  - Route = POST /api/adm
	 *  
	 * @param pat
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/adm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public String creerPatient(@RequestBody Patient pat) throws Exception {
		
		LoggerLake.log(LogLevel.INFO, "Création d'un nouveau patient.");
		LoggerLake.log(LogLevel.INFO, pat.toString());
		
		// Création du nom du fichier HL7.
		String filename = new Date().toString()
				.replaceAll(" ", "_")
				.replaceAll(":", "-") + ".hl7";
		
		LoggerLake.log(LogLevel.INFO, "Nouveau patient stocké dans le fichier " + filename);

		new Hl7Gen(pat).create_adt(filename);
		
		return "OK";
	}
}
