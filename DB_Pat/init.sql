CREATE TABLE IF NOT EXISTS patient (
    IPP VARCHAR(250),
    DATEMSG VARCHAR(250),
    NUMMSG VARCHAR(250),
    NOM VARCHAR(250) NOT NULL,
    PRENOM VARCHAR(250) NOT NULL,
    INTIT VARCHAR(250),
    NOMMAR VARCHAR(250),
    DDN VARCHAR(250),
    SEXE VARCHAR(250),
    IEP VARCHAR(250),
    ADR1 VARCHAR(250),
    ADR2 VARCHAR(250),
    CP VARCHAR(250),
    VILLE VARCHAR(250),
    PAYS VARCHAR(250),
    TEL VARCHAR(250),
    PAYSN VARCHAR(250),
    DDS VARCHAR(250) NOT NULL,
    UF VARCHAR(250),
    CHAMBRE VARCHAR(250),
    LIT VARCHAR(250),
    UFMED VARCHAR(250),
    NUMPAS VARCHAR(250)
);

INSERT INTO public.patient (ipp, datemsg, nummsg, nom, prenom, intit, nommar, ddn, sexe, iep, adr1, adr2, cp, ville, pays, tel, paysn, dds, uf, chambre, lit, ufmed, numpas) VALUES ('TEST_IPP', 'TEST_DATEMSG', 'TEST_NUMMSG', 'ZOLNIERUCK', 'Maxence', 'M', 'TEST_NOMMAR', '21-08-2000', 'H', 'TEST_IEP', '16 rue des Lilas', 'TEST_ADR2', '59300', 'Valenciennes', 'France', '+33622669052', 'TEST_PAYSN', 'TEST_DDS', 'TEST_UF0', 'TEST_CHAMBRE', 'TEST_LIT', 'TEST_UFMED', '1');
INSERT INTO public.patient (ipp, datemsg, nummsg, nom, prenom, intit, nommar, ddn, sexe, iep, adr1, adr2, cp, ville, pays, tel, paysn, dds, uf, chambre, lit, ufmed, numpas) VALUES ('TEST_IPP', 'TEST_DATEMSG', 'TEST_NUMMSG', 'XU', 'Thierry', 'M', 'TEST_NOMMAR', '15-07-2000', 'H', 'TEST_IEP', '44 avenue des Fleurs', 'TEST_ADR2', '59300', 'Valenciennes', 'France', '+33645965812', 'TEST_PAYSN', 'TEST_DDS', 'TEST_UF0', 'TEST_CHAMBRE', 'TEST_LIT', 'TEST_UFMED', '2');
INSERT INTO public.patient (ipp, datemsg, nummsg, nom, prenom, intit, nommar, ddn, sexe, iep, adr1, adr2, cp, ville, pays, tel, paysn, dds, uf, chambre, lit, ufmed, numpas) VALUES ('TEST_IPP', 'TEST_DATEMSG', 'TEST_NUMMSG', 'VIDREQUIN', 'Josue', 'M', 'TEST_NOMMAR', '03-04-2000', 'H', 'TEST_IEP', '1 boulevard des Rues', 'TEST_ADR2', '59300', 'Valenciennes', 'France', '+33666875326', 'TEST_PAYSN', 'TEST_DDS', 'TEST_UF0', 'TEST_CHAMBRE', 'TEST_LIT', 'TEST_UFMED', '3');
INSERT INTO public.patient (ipp, datemsg, nummsg, nom, prenom, intit, nommar, ddn, sexe, iep, adr1, adr2, cp, ville, pays, tel, paysn, dds, uf, chambre, lit, ufmed, numpas) VALUES ('TEST_IPP', 'TEST_DATEMSG', 'TEST_NUMMSG', 'CONSIGNY', 'Maxime', 'M', 'TEST_NOMMAR', '01-11-2000', 'H', 'TEST_IEP', '6 rue des Bandits', 'TEST_ADR2', '59300', 'Valenciennes', 'France', '+33626874498', 'TEST_PAYSN', 'TEST_DDS', 'TEST_UF0', 'TEST_CHAMBRE', 'TEST_LIT', 'TEST_UFMED', '4');
