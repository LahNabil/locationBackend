package emsi.projet.location.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import emsi.projet.location.entities.Agence;
import emsi.projet.location.entities.Assurance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class voitureDto {
	
	
	private int id;
	
	private String matricule;
	
	private String marque;
	
	private String modele;
	
	private int prix;
	
	
	private boolean dispo;
	
	private String couleur;
	
	private String carburant;
	
	private Date date;
	
	private String photo;
	
	
	private int assurance;
	
	private int agence;

}
