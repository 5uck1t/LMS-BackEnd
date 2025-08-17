package com.example.demo.dto;

import java.util.Date;

public class UpisDTO {
    private String godinaStudija;     // npr "Prva", "Druga", ...
    //private String skolskaGodina;     // npr "2022/23"
    private Date datumUpisa;

    public UpisDTO() {
    }

    public UpisDTO(String godinaStudija, Date datumUpisa) {
        this.godinaStudija = godinaStudija;
       // this.skolskaGodina = skolskaGodina;
        this.datumUpisa = datumUpisa;
    }

	public String getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

//	public String getSkolskaGodina() {
//		return skolskaGodina;
//	}
//
//	public void setSkolskaGodina(String skolskaGodina) {
//		this.skolskaGodina = skolskaGodina;
//	}

	public Date getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

    // Getteri i setteri...
}
