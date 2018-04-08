package mob.jamaldev.dz.koala;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by bjama on 5/3/2017.
 */

public class Categorie {
    private String idCategorie;
    private String description;
    private String nom;
    Bitmap image;

    public Categorie(String idCategorie, String description, String nom, Bitmap image) {
        this.idCategorie = idCategorie;
        this.description = description;
        this.nom = nom;
        this.image = image;
        System.out.println(this.image + ":::::::::::::::::::::");
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
