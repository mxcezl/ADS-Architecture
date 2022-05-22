package com.ads.wsrequete.pojo;

public class Patient {

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

    public Patient(String DATEMSG, String NUMMSG, String IPP, String NOM, String PRENOM, String INTIT, String NOMMAR, String DDN, String SEXE, String IEP, String ADR1, String ADR2, String CP, String VILLE, String PAYS, String TEL, String PAYSN, String DDS, String UF, String CHAMBRE, String LIT, String UFMED, String NUMPAS) {
        this.DATEMSG = DATEMSG;
        this.NUMMSG = NUMMSG;
        this.IPP = IPP;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.INTIT = INTIT;
        this.NOMMAR = NOMMAR;
        this.DDN = DDN;
        this.SEXE = SEXE;
        this.IEP = IEP;
        this.ADR1 = ADR1;
        this.ADR2 = ADR2;
        this.CP = CP;
        this.VILLE = VILLE;
        this.PAYS = PAYS;
        this.TEL = TEL;
        this.PAYSN = PAYSN;
        this.DDS = DDS;
        this.UF = UF;
        this.CHAMBRE = CHAMBRE;
        this.LIT = LIT;
        this.UFMED = UFMED;
        this.NUMPAS = NUMPAS;
    }

    public Patient() {
    	super();
    }
    
    public String getDATEMSG() {
        return DATEMSG;
    }

    public String getNUMMSG() {
        return NUMMSG;
    }

    public String getIPP() {
        return IPP;
    }

    public String getNOM() {
        return NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public String getINTIT() {
        return INTIT;
    }

    public String getNOMMAR() {
        return NOMMAR;
    }

    public String getDDN() {
        return DDN;
    }

    public String getSEXE() {
        return SEXE;
    }

    public String getIEP() {
        return IEP;
    }

    public String getADR1() {
        return ADR1;
    }

    public String getADR2() {
        return ADR2;
    }

    public String getCP() {
        return CP;
    }

    public String getVILLE() {
        return VILLE;
    }

    public String getPAYS() {
        return PAYS;
    }

    public String getTEL() {
        return TEL;
    }

    public String getPAYSN() {
        return PAYSN;
    }

    public String getDDS() {
        return DDS;
    }

    public String getUF() {
        return UF;
    }

    public String getCHAMBRE() {
        return CHAMBRE;
    }

    public String getLIT() {
        return LIT;
    }

    public String getUFMED() {
        return UFMED;
    }

    public String getNUMPAS() {
        return NUMPAS;
    }

    public void setDATEMSG(String DATEMSG) {
        this.DATEMSG = DATEMSG;
    }

    public void setNUMMSG(String NUMMSG) {
        this.NUMMSG = NUMMSG;
    }

    public void setIPP(String IPP) {
        this.IPP = IPP;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public void setINTIT(String INTIT) {
        this.INTIT = INTIT;
    }

    public void setNOMMAR(String NOMMAR) {
        this.NOMMAR = NOMMAR;
    }

    public void setDDN(String DDN) {
        this.DDN = DDN;
    }

    public void setSEXE(String SEXE) {
        this.SEXE = SEXE;
    }

    public void setIEP(String IEP) {
        this.IEP = IEP;
    }

    public void setADR1(String ADR1) {
        this.ADR1 = ADR1;
    }

    public void setADR2(String ADR2) {
        this.ADR2 = ADR2;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public void setVILLE(String VILLE) {
        this.VILLE = VILLE;
    }

    public void setPAYS(String PAYS) {
        this.PAYS = PAYS;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public void setPAYSN(String PAYSN) {
        this.PAYSN = PAYSN;
    }

    public void setDDS(String DDS) {
        this.DDS = DDS;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public void setCHAMBRE(String CHAMBRE) {
        this.CHAMBRE = CHAMBRE;
    }

    public void setLIT(String LIT) {
        this.LIT = LIT;
    }

    public void setUFMED(String UFMED) {
        this.UFMED = UFMED;
    }

    public void setNUMPAS(String NUMPAS) {
        this.NUMPAS = NUMPAS;
    }
}
