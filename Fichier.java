import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Java permettant de Creer/Modifier/Lire/Supprimmer des fichiers en java.
 */
public class Fichier{

    private String nom;
    private String repertoire;

    /**
     * Constructeur de fichier.
     * @param nom nom du fichier.
     * @param repertoire répertoire ou sera positionné le fichier.
     */
    public Fichier(String nom,String repertoire ){
        this.nom=nom;
        this.repertoire=repertoire;
    }

    /**
     * Constructeur de fichier avec comme répertoire : ".".
     * @param nom nom du fichier.
     */
    public Fichier(String nom){
        this(nom,".");
    }


    /**
     * Créer le fichier.
     */
    public void cree(){
        try {
            File myObj = new File(this.getRepertoire()+"/"+this.getNom());
            if (!myObj.createNewFile()) System.out.println("Fichier deja existant");
        } catch (IOException e) {e.printStackTrace();}
    }

    /**
     * Écrit sur le fichier à la ligne donnée en parametre.
     * @param data donnée à écrire dans le fichier.
     * @param ligne ligne où écrire dans le fichier.
     */
    public void ecrire(String data,int ligne){
        try {
            String[] tab = this.toTab();
            boolean pass=false;
            FileWriter myWriter = new FileWriter(this.getRepertoire()+"/"+this.getNom());
            StringBuilder write= new StringBuilder();
            int i=1;
            if(tab.length==0){
                while(i!=ligne){
                    write.append("\n");
                    i++;
                }
                write.append(data);
                pass=true;
            }
            else{
                for(String autre : tab){
                    if(ligne==i){
                        write.append(data).append("\n");
                        pass=true;
                    }
                    else{
                        write.append(autre).append("\n");
                    }
                    i++;
                }
            }
            if(!pass){
                while(i!=ligne){
                    write.append("\n");
                    i++;
                }
                write.append(data);
            }
            myWriter.write(write.toString());
            myWriter.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    /**
     * Lis le fichier en l'affichant dans la console.
     */
    public void lire(){
        try {
            File myObj = new File(this.getRepertoire()+"/"+this.getNom());
            Scanner myReader = new Scanner(myObj);
            int i=1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(i+" "+data);
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforme le fichier en tableau de String.
     * @return un tableau de String contenant tout le fichier ligne par ligne.
     */
    public String[] toTab(){
        ArrayList<String> liste = new ArrayList<>();
        try {
            File myObj = new File(this.getRepertoire()+"/"+this.getNom());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                liste.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            liste.add("error");
            e.printStackTrace();
        }
        return liste.toArray(new String[liste.size()]);
    }

    /**
     * Supprime le fichier.
     */
    public void supprimer(){
        File myObj = new File(this.getRepertoire()+"/"+this.getNom());
        if (!myObj.delete()) System.out.println("Fichier impossible a supprimer.");
    }

    /**
     * Retorune le nom du fichier.
     * @return le nom du fichier.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorune le répertoire du fichier.
     * @return le répertoire du fichier.
     */
    public String getRepertoire() {
        return repertoire;
    }
}
