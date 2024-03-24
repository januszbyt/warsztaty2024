/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warsztaty2024;

/**
 *
 * @author s40483
 */
public class Osoba {
    private int ID;
    private String nazwisko;
    private String imie;
    private String Email;
    private String Adres;
    private String Telefon;
    private String Narodowosc;
    private String Zrodlo;
    private String Status;
    private String Link;

    public Osoba(int ID,String nazwisko, String imie, String Email, String Adres,String Telefon,String Narodowosc,String Zrodlo,String Status,String Link) {
        this.ID = ID;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.Email = Email;
        this.Adres = Adres;
        this.Telefon = Telefon;
        this.Narodowosc = Narodowosc;
        this.Zrodlo = Zrodlo;
        this.Status = Status;
        this.Link = Link;
    }

    public int getID() {
        return ID;
    }
    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getEmail() {
        return Email;
    }
    public String getAdres() {
        return Adres;
    }
    public String getTelefon() {
        return Telefon;
    }
    public String getNarodowosc() {
        return Narodowosc;
    }
    public String getZrodlo() {
        return Zrodlo;
    }
    public String getStatus() {
        return Status;
    }
    public String getLink() {
        return Link;
    }
}
