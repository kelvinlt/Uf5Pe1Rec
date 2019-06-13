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
public class Recepta {
    String[] ingredients;
    String procediment;

    public Recepta(String[] ingredients, String procediment) {
        this.ingredients = ingredients;
        this.procediment = procediment;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcediment() {
        return procediment;
    }

    public void setProcediment(String procediment) {
        this.procediment = procediment;
    }

    @Override
    public String toString() {
        return "Recepta{" + "ingredients=" + ingredients + ", procediment=" + procediment + '}';
    }
}
