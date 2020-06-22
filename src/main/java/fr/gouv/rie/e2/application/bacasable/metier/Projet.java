package fr.gouv.rie.e2.application.bacasable.metier;

import lombok.Data;

@Data
public class Projet {
    
    private long   id;
    private String nom;
    private Agent  moa;
    private Agent  amoa;
}