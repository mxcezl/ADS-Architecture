package com.ads.wscreate.traitement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.boot.logging.LogLevel;

import com.ads.wscreate.loglake.LoggerLake;
import com.ads.wscreate.pojo.Patient;

/**
 * *******************************************
 * Classe de gestion des fichier HL7
 * Specialise dans le format ORU
 * Elle permet de generer un fichier HL7 ORU
 ********************************************
 */
public class Hl7Gen {

	//format du fichier de sortie HL7 ORU vers la base de donn�es.
	//ORU out
	//MSH10 Message control ID (R)
	String ADTMSH = "MSH|^~\\&|SA_AMCK|SF_MCK|DXSRA|admission|#DATEMSG#|A01|ADT^A01|#NUMMSG#|P|2.3.1||||||8859/1|";
	//EVN
	String ADTEVN="EVN|A01|#DATEMSG#||||";
	//PID 18 Patient control Account
	String ADTPID = "PID|1||#IPP#^^^IF_MCKN||#NOM#^#PRENOM#^^^#INTIT#^^L|#NOMMAR#|#DDN#|#SEXE#|||#ADR1#^#ADR2#^#VILLE#^^#CP#^#PAYS#|#PAYS#|#TEL#|||U||#IEP#||||||||||#PAYSN#^#PAYSN#||N";
	//PV1
	String ADTPV1 = "PV1|1|I|#UF#^#CHAMBRE#^#LIT#|R|||||||||||||||#IEP#||||||||||||||||||||||||| #DDS#||||||#NUMPAS#|A|";
	//PV2
	String ADTPV2="PV2|||^^^8^^|||||||||||||||||||N";
	//ZFU
	String ADTZFU="ZFU|#UF#|#DDS#|#UFMED#|#DDS#|#UF#|#DDS#";
	//ZRE
	String ADTZRE="ZRE|||||||||";

	/*****************************
	 * VARIABLES GLOBALES
	 ****************************/
	//identite patient et data patient
	private String DATEMSG;//now
	private String NUMMSG;//auto
	private String IPP;//
	private String NOM;//
	private String PRENOM;//
	private String INTIT;
	private String NOMMAR;//
	private String DDN;//
	private String SEXE;//
	private String IEP;//
	private String ADR1;
	private String ADR2;
	private String CP;
	private String VILLE;
	private String PAYS;
	private String TEL;//
	private String PAYSN;
	private String DDS;
	private String UF;
	private String CHAMBRE;
	private String LIT;
	private String UFMED;
	private String NUMPAS;

	//newlines
	final int CR = 13;
	final int LF = 10;

	/*************************************************************
	 * constructeur initialisation
	 * initialise surtout les variables
	 * @param IPP
	 * @param NOM
	 * @param PRENOM
	 * @param INTIT
	 * @param NOMMAR
	 * @param DDN
	 * @param SEXE
	 * @param DDS
	 * @param PAYS
	 * @param IEP
	 * @param ADR2
	 * @param ADR1
	 * @param VILLE
	 * @param UFMED
	 * @param CP
	 * @param PAYSN
	 * @param TEL
	 * @param CHAMBRE
	 * @param UF
	 * @param LIT
	 *************************************************************/
	public Hl7Gen(String IPP,String NOM, String PRENOM, String INTIT,String NOMMAR, String DDN, String SEXE, String IEP, String ADR1, String ADR2, String CP, String VILLE, String PAYS, String TEL, String PAYSN, String DDS, String UF, String CHAMBRE, String LIT,String UFMED) {

		//definir ici les numero auto et dates auto
		//String DATEMSG;//now
		NUMMSG=String.valueOf(System.currentTimeMillis());
		NUMPAS=String.valueOf(System.currentTimeMillis()-100);
		Calendar c=Calendar.getInstance();
		this.IPP=IPP;
		this.NOM = NOM;
		this.PRENOM = PRENOM;
		this.INTIT=INTIT;
		this.NOMMAR = NOMMAR;
		this.DDN=DDN;
		this.SEXE = SEXE;
		this.IEP = IEP;
		this.ADR1=ADR1;
		this.ADR2=ADR2;
		this.CP=CP;
		this.VILLE=VILLE;
		this.PAYS=PAYS;
		this.TEL=TEL;
		this.PAYSN=PAYSN;
		this.DDS=DDS+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE));
		this.UF = UF;
		this.CHAMBRE=CHAMBRE;
		this.LIT=LIT;
		this.UFMED=UFMED;
		this.DATEMSG=LocalDate.now().toString()+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE));
	}


	public Hl7Gen(Patient pat) {

		//definir ici les numero auto et dates auto
		//String DATEMSG;//now
		NUMMSG=String.valueOf(System.currentTimeMillis());
		NUMPAS=String.valueOf(System.currentTimeMillis()-100);
		Calendar c = Calendar.getInstance();
		this.IPP=pat.getIPP();
		this.NOM = pat.getNOM();
		this.PRENOM = pat.getPRENOM();
		this.INTIT=pat.getINTIT();
		this.NOMMAR = pat.getNOMMAR();
		this.DDN=pat.getDDN();
		this.SEXE = pat.getSEXE();
		this.IEP = pat.getIEP();
		this.ADR1=pat.getADR1();
		this.ADR2=pat.getADR2();
		this.CP=pat.getCP();
		this.VILLE=pat.getVILLE();
		this.PAYS=pat.getPAYS();
		this.TEL=pat.getTEL();
		this.PAYSN=pat.getPAYSN();
		this.DDS=pat.getDDS()+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE));
		this.UF = pat.getUF();
		this.CHAMBRE=pat.getCHAMBRE();
		this.LIT=pat.getLIT();
		this.UFMED=pat.getUFMED();
		this.DATEMSG=LocalDate.now().toString()+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE))+":"+completeZero(c.get(Calendar.SECOND));
	}

	private String completeZero(int in) {
		String out=""+in;
		if (out.length()<2){ out="0"+in;}
		return out;
	}

	/*****************************************************
	 * convertir une date JJ/MM/AAAA en date hl7 AAAAMMJJ
	 *
	 *
	 * @param dateSimple String
	 * @return String
	 *****************************************************/
	private String Date_to_Date_HL7_court(String dateSimple) {
		//parser la date
		String JJ = dateSimple.substring(8, 10);
		String MM = dateSimple.substring(5, 7);
		String AAAA = dateSimple.substring(0, 4);
		return AAAA + MM + JJ + "000000";
	}

	/*********************************************************************
	 * convertir une date JJ/MM/AAAA HH:MM:SS en date hl7 AAAAMMJJHHMMSS
	 *
	 *
	 * @param dateComplexe String
	 * @return String
	 ********************************************************************/
	private String Date_to_Date_HL7_long(String dateComplexe) {
		//parser la date
		String JJ = dateComplexe.substring(8, 10);
		String MM = dateComplexe.substring(5, 7);
		String AAAA = dateComplexe.substring(0, 4);
		String HH = dateComplexe.substring(11, 13);
		String mm = dateComplexe.substring(14, 16);
		return AAAA + MM + JJ + HH + mm + "00";
	}

	/*********************************************************************
	 * Fonction de remplacement des tag #XXXX# pour les segments HL7
	 *
	 * @param in (Chaine de caratere du segment a remplacer
	 * @return String
	 * @throws java.lang.Exception Erreur replaceAll
	 *********************************************************************/
	private String hl7(String in) throws Exception {
		String outS = in;
		//Segment MSH
		outS = outS.replaceAll("#DATEMSG#", Date_to_Date_HL7_long(DATEMSG));
		outS = outS.replaceAll("#NUMMSG#", NUMMSG);
		outS = outS.replaceAll("#NUMPAS#", NUMPAS);
		outS = outS.replaceAll("#DATEMSG#", Date_to_Date_HL7_long(DDS));
		//segment PID/PV1
		outS = outS.replaceAll("#INTIT#", INTIT);
		outS = outS.replaceAll("#NOM#", NOM);
		outS = outS.replaceAll("#NOMMAR#", NOMMAR);
		outS = outS.replaceAll("#PRENOM#", PRENOM);
		outS = outS.replaceAll("#SEXE#", SEXE);
		outS = outS.replaceAll("#DDN#", Date_to_Date_HL7_court(DDN));
		outS = outS.replaceAll("#ADR1#", ADR1);
		outS = outS.replaceAll("#ADR2#", ADR2);
		outS = outS.replaceAll("#CP#", CP);
		outS = outS.replaceAll("#VILLE#", VILLE);
		outS = outS.replaceAll("#PAYS#", PAYS);
		outS = outS.replaceAll("#PAYSN#", PAYSN);
		outS = outS.replaceAll("#IPP#", IPP);
		outS = outS.replaceAll("#IEP#", IEP);
		outS = outS.replaceAll("#UF#", UF);
		outS = outS.replaceAll("#CHAMBRE#", CHAMBRE);
		outS = outS.replaceAll("#LIT#", LIT);
		outS = outS.replaceAll("#TEL#",TEL);
		//segment ZFU
		outS = outS.replaceAll("#UFMED#", UFMED);
		outS = outS.replaceAll("#DDS#",Date_to_Date_HL7_long(DDS));

		return outS;
	}


	/***************************************************
	 * Procedure qui permet d'ecrire le fichier hl7_oru
	 *
	 *
	 * @param outputFileName String
	 * @return boolean
	 * @throws java.lang.Exception Erreur d'ecriture sur disque
	 ***************************************************/
	public boolean create_adt(String outputFileName) throws Exception {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(outputFileName));
			out.write(hl7(ADTMSH));
			out.write(CR);
			out.write(hl7(ADTEVN));
			out.write(CR);
			out.write(hl7(ADTPID));
			out.write(CR);
			out.write(hl7(ADTPV1));
			out.write(CR);
			out.write(hl7(ADTPV2));
			out.write(CR);
			out.write(hl7(ADTZFU));
			out.write(CR);
			out.write(hl7(ADTZRE));
			out.flush();
			out.close();
			
			LoggerLake.log(LogLevel.INFO, "Patient créé avec succès.");
		} catch (Exception e) {
			LoggerLake.log(LogLevel.ERROR, "Erreur lors de la création du patient.");
			LoggerLake.log(LogLevel.ERROR, e.getMessage());
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}