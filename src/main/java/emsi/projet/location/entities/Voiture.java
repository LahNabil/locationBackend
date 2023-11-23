package emsi.projet.location.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Voiture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String matricule;
	private String marque;
	private String modele;
	private int prix;
	private boolean dispo;
	private String couleur;
	private String carburant;
	private Date date;
	
	@OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "assurance_id", unique = true)
	private Assurance assurance;
	
	@ManyToOne
	@JoinColumn(name="agence_id")
	private Agence agence;
	
	
	
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public boolean isDispo() {
		return dispo;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getCarburant() {
		return carburant;
	}
	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Assurance getAssurance() {
		return assurance;
	}
	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}
	
	
	
	

}
