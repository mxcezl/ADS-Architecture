package com.ads.wsupdatedelete.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientOdtIn {  
    private String nom;
    private String prenom;
    private String ddn;
}