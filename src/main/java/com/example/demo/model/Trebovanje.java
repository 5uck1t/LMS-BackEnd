package com.example.demo.model;


public class Trebovanje {
    private String id;
    private String datum;
    private String kolicina;
    private String rob;

    public Trebovanje() {}

    public Trebovanje(String id, String datum, String kolicina, String rob) {
        this.id = id;
        this.datum = datum;
        this.kolicina = kolicina;
        this.rob = rob;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDatum() { return datum; }
    public void setDatum(String datum) { this.datum = datum; }

    public String getKolicina() { return kolicina; }
    public void setKolicina(String kolicina) { this.kolicina = kolicina; }

    public String getRob() { return rob; }
    public void setRob(String rob) { this.rob = rob; }
}
