package fr.gouv.rie.e2.application.bacasable.persistance;

import fr.gouv.rie.e2.application.bacasable.metier.Projet;
import fr.gouv.rie.e2.application.commun.persistance.AbstractDatabase;

import java.util.List;

public class ProjetService extends AbstractDatabase<Projet> {
    
    public static void main(String[] args) {
    
        ProjetService projetService = new ProjetService();
        List<Projet>  projets       = projetService.queryList(projetService.query().all());
        System.out.println("projets = " + projets);
    }
}