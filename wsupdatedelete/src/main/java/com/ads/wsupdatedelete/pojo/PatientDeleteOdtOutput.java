package com.ads.wsupdatedelete.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDeleteOdtOutput {

	private String message;
	private boolean deleted;

}
