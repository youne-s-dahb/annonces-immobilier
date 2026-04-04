package services;

import model.Annonces;

import java.util.List;

public interface annonce {

    // Kat-rj3 lina list dyal l-annonces 3la hsab critere (prix, category...)
    public List<Annonces> filtrer_annonce(String type, double prixMin, double prixMax);

    // Kat-khlina n-choufo l-detail dyal annonce wa7da b-l'id dyalha
    public Annonces consulter_annonce(int id_annonce);

    // Kat-bhét b-l-kalimat l-mifta7iya (keyword) f l-cinwan aw l-wasf
    public List<Annonces> chercher_annonce(String search);

}

