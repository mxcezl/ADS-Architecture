package com.ads.wsrequete.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ads.wsrequete.loglake.LoggerLake;
import com.ads.wsrequete.pojo.Patient;
import com.ads.wsrequete.postgres.PostgresUtils;

@RestController
public class PatientController {

	@ResponseBody
    @GetMapping("/api/search")
	/**
	 * Ressource de recherche d'un patient via Nom, Prenom, DDN.
	 * @param nom
	 * @param prenom
	 * @param ddn
	 * @return
	 * @throws Exception
	 */
    public Patient getInfoPatiens(@RequestParam(required = false) String nom, @RequestParam(required = false) String prenom, @RequestParam(required = false) String ddn) throws Exception {

        Map<String, String> params = fillParams(nom, prenom, ddn);
        
        LoggerLake.log(LogLevel.INFO, "Réception d'une requête avec les paramètres : " + params.toString());

        checkParams(params);

        String request = "SELECT ipp, datemsg, nummsg, nom, prenom, intit, nommar, ddn, sexe, iep, adr1, adr2, cp, ville, pays, tel, paysn, dds, uf, chambre, lit, ufmed, numpas FROM patient WHERE ";
        boolean first = true;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (null != param.getValue()) {
                if (first) {
                    request += "patient." + param.getKey() + " = '" + param.getValue() + "'";
                } else {
                    request += " AND " + "patient." + param.getKey() + " = '" + param.getValue() + "'";
                }
                first = false;
            }
        }
        request += ";";

        String resultAsString = PostgresUtils.executeQuery(request);
        
        return PostgresUtils.patientFromDBReturn(resultAsString);
    }

	/**
	 * Fonction de contrôle.
	 * Renvoie une exception si aucun paramètre n'est renseigné.
	 * 
	 * @param params
	 * @throws Exception
	 */
    private void checkParams(Map<String, String> params) throws Exception {
        if (params.values().stream().allMatch(v -> null == v || v.isEmpty())) {
            throw new Exception("Aucun paramètre renseigné");
        }
    }

    /**
     * Vérification des paramètres en entree du service.
     * Determine quels paramètres sont renseignés et lesquels pourront être
     * urilisés pour la recherche.
     * 
     * @param nom
     * @param prenom
     * @param ddn
     * @return
     */
    private Map<String, String> fillParams(String nom, String prenom, String ddn) {
        Map<String, String> params = new HashMap<>();
        params.put("nom", (null != nom && !nom.isEmpty()) ? nom : null);
        params.put("prenom", (null != prenom && !prenom.isEmpty()) ? prenom : null);
        params.put("ddn", (null != ddn && !ddn.isEmpty()) ? ddn : null);
        return params;
    }
}
