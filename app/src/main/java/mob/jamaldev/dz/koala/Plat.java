package mob.jamaldev.dz.koala;

import android.graphics.Bitmap;

/**
 * Created by bjama on 5/3/2017.
 */

public class Plat {
    private String idPlat;
    private String idCategorie;
    private String prix;
    private String tempsPreparation;
    private String disponible;
    private String description;
    private String nom;
    Bitmap image;


    public Plat(String idPlat, String idCategorie, String prix, String tempsPreparation, String disponible, String description, String nom, Bitmap image) {
        this.idPlat = idPlat;
        this.idCategorie = idCategorie;
        this.prix = prix;
        this.tempsPreparation = tempsPreparation;
        this.disponible = disponible;
        this.description = description;
        this.nom = nom;
        this.image = image;
    }

//    public Plat(String idPlat, String idCategorie, String prix) {
//        this.idPlat = idPlat;
//        this.idCategorie = idCategorie;
//        this.prix = prix;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(String idPlat) {
        this.idPlat = idPlat;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(String tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
