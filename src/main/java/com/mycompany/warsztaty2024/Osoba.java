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
    private String nazwisko;
    private String imie;
    private double wiek;

    public Osoba(String nazwisko, String imie, double wiek) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.wiek = wiek;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public double getWiek() {
        return wiek;
    }
    
}
