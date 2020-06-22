package fr.gouv.rie.e2.application.bacasable.metier;

public class Projet {
    
    private long   id;
    private String nom;
    private Agent  moa;
    private Agent  amoa;
    
    public Agent getAmoa() {
        
        return amoa;
    }
    
    public void setAmoa(Agent amoa) {
        
        this.amoa = amoa;
    }
    
    public long getId() {
        
        return id;
    }
    
    public void setId(long id) {
        
        this.id = id;
    }
    
    public Agent getMoa() {
        
        return moa;
    }
    
    public void setMoa(Agent moa) {
        
        this.moa = moa;
    }
    
    public String getNom() {
        
        return nom;
    }
    
    public void setNom(String nom) {
        
        this.nom = nom;
    }
}