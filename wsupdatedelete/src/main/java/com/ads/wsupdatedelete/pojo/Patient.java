package com.ads.wsupdatedelete.pojo;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {  
    private String DATEMSG; // Now
    private String NUMMSG; // Auto
    private String IPP;
    private String NOM;
    private String PRENOM;
    private String INTIT;
    private String NOMMAR;
    private String DDN;
    private String SEXE;
    private String IEP;
    private String ADR1;
    private String ADR2;
    private String CP;
    private String VILLE;
    private String PAYS;
    private String TEL;
    private String PAYSN;
    private String DDS;
    private String UF;
    private String CHAMBRE;
    private String LIT;
    private String UFMED;
    private String NUMPAS; 
    
    public static boolean isValid(Object o) {
        boolean allNull = true;
        try {
            for (Field f : o.getClass().getDeclaredFields()){
                allNull &= f.get(o) == null;
            }
        } catch (IllegalAccessException ignored) {
            return true;
        }

        return !allNull;
    }
}