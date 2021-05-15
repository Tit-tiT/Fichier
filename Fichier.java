import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Fichier{

    private String nom;
    private String repertoire;

    public Fichier(String nom,String repertoire ){
        this.nom=nom;
        this.repertoire=repertoire;
    }

    public Fichier(String nom){
        this(nom,".");
    }


    public void cree(){
        try {
            File myObj = new File(this.getRepertoire()+"/"+this.getNom());
            if (myObj.createNewFile()) {}
            else System.out.println("Fichier deja existant");
        } catch (IOException e) {e.printStackTrace();}
    }

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

    public void supprimer(){
        File myObj = new File(this.getRepertoire()+"/"+this.getNom());
        if (myObj.delete()) {}
        else {System.out.println("Fichier impossible a supprimer.");}
    }

    public String getNom() {
        return nom;
    }

    public String getRepertoire() {
        return repertoire;
    }
}
