/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectes;

/**
 *
 * @author x2382383c
 */
public class Plat {
    String nom;
    int preu;
    Recepta recepta;

    public Plat(String nom, int preu, Recepta recepta) {
        this.nom = nom;
        this.preu = preu;
        this.recepta = recepta;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }

    public Recepta getRecepta() {
        return recepta;
    }

    public void setRecepta(Recepta recepta) {
        this.recepta = recepta;
    }

    @Override
    public String toString() {
        return "Plat{" + "nom=" + nom + ", preu=" + preu + ", recepta=" + recepta + '}';
    }
}
