package uf5pe1rec;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import objectes.Recepta;
import objectes.Plat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Uf5Pe1Rec {

    public static void main(String[] args) {
        HashMap<String, Plat> amanidaPlats = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document inDoc = builder.parse(new File("src/receptes.xml"));
            NodeList plats = inDoc.getElementsByTagName("plat");

            for (int i = 0; i < plats.getLength(); i++) {
                Element elem1 = (Element) plats.item(i);
                if (elem1.getAttribute("grup").equals("amanides")) {
                    //nom
                    NodeList nom = elem1.getElementsByTagName("nom");
                    Element elem2 = (Element) nom.item(0);
                    String nomPlat = elem2.getFirstChild().getNodeValue();
                    //preu
                    NodeList preu = elem1.getElementsByTagName("preu");
                    Element elem3 = (Element) preu.item(0);
                    int preuPlat = Integer.parseInt(elem3.getFirstChild().getNodeValue());
                    //ingredients
                    NodeList ingredient = elem1.getElementsByTagName("ingredient");
                    String[] nIngredientes = new String[ingredient.getLength()];
                    for (int j = 0; j < ingredient.getLength(); j++) {
                        Element elem4 = (Element) ingredient.item(j);
                        String nIngredient = elem4.getFirstChild().getNodeValue();
                        nIngredientes[j] = nIngredient;
                    }
                    //procediment
                    NodeList procediment = elem1.getElementsByTagName("procediment");
                    Element elem5 = (Element) procediment.item(0);
                    String nProcediment = elem5.getFirstChild().getNodeValue();

                    //colocar en objectes y en hasmap
                    Recepta nRecepta = new Recepta(nIngredientes, nProcediment);
                    Plat nPlat = new Plat(nomPlat, preuPlat, nRecepta);

                    amanidaPlats.put(nomPlat, nPlat);

                } else {
                    
                }
            }
            //Test de Hashmap amb els plats amb amanides
            for (Map.Entry<String, Plat> entry : amanidaPlats.entrySet()) {
                String key = entry.getKey();
                Plat value = entry.getValue();
                System.out.println(key + value);
            }
            System.out.println("-----------------------");
            //Creacio del document xml amb el hashmap
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document documentOut = documentBuilder.newDocument();
            //Creacio del root del xml
            Element root = documentOut.createElement("Plats");
            documentOut.appendChild(root);
            //creacio de tots els altres parametres del plat
            for (Map.Entry<String, Plat> entry : amanidaPlats.entrySet()) {
                Element plat = documentOut.createElement("plat");
                root.appendChild(plat);
                Element nomPlat = documentOut.createElement("nom");
                plat.appendChild(nomPlat);
                Text textNom = documentOut.createTextNode(entry.getValue().getNom());
                nomPlat.appendChild(textNom);
                Element preuPlat = documentOut.createElement("preu");
                plat.appendChild(preuPlat);
                Text textPreu = documentOut.createTextNode(String.valueOf(entry.getValue().getPreu()));
                preuPlat.appendChild(textPreu);
                Element recepta = documentOut.createElement("recepta");
                plat.appendChild(recepta);
                Element ingredients = documentOut.createElement("ingredients");
                recepta.appendChild(ingredients);

                Recepta receptaTemp = entry.getValue().getRecepta();
                String[] ingredientesTemp = receptaTemp.getIngredients();
                for (int j = 0; j < ingredientesTemp.length; j++) {
                    Element ingredient = documentOut.createElement("ingredient");
                    ingredients.appendChild(ingredient);
                    Text nomIngredient = documentOut.createTextNode(ingredientesTemp[j]);
                    ingredient.appendChild(nomIngredient);
                }
                Element procediment = documentOut.createElement("procediment");
                recepta.appendChild(procediment);
                Text textProcediment = documentOut.createTextNode(entry.getValue().getRecepta().getProcediment());
                procediment.appendChild(textProcediment);

            }
            //Insercio al xml nou
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource origen = new DOMSource(documentOut);
            StreamResult desti = new StreamResult(new File("src/ordenada.xml"));
            transformer.transform(origen, desti);

            //Ordenar el hasmap amb treeset

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
