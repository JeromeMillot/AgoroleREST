package fr.agrorole.dnd.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
@Entity
public class Race implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 5231838391903136590L;
	
	@Id
	@NotNull
	private String label;
	private String description;
	private String catTaille;
	private String ageMax;
	private Short ajustNiv;
	private Short vitDeplac;
	private Set<String> langues;
	@Column(name="PRED_CLA")
	private String prediClasse;
	private List<String> dons;
	@Column(name="COMP")
	private Set<String> competences;
	@Column(name="BON_SAV")
	private HashMap<String, Short> bonusSauv;
	private HashMap<String, Short> carac;
	  
	public Race(String label, String description, String catTaille, String ageMax, Short ajustNiv, Short vitDeplac,
			Set<String> langues, String prediClasse, List<String> dons, Set<String> competences,
			HashMap<String, Short> bonusSauv, HashMap<String, Short> carac) {
		super();
		this.label = label;
		this.description = description;
		this.catTaille = catTaille;
		this.ageMax = ageMax;
		this.ajustNiv = ajustNiv;
		this.vitDeplac = vitDeplac;
		this.langues = langues;
		this.prediClasse = prediClasse;
		this.dons = dons;
		this.competences = competences;
		this.bonusSauv = bonusSauv;
		this.carac = carac;
	}
	
	public Race(Race race) {
		super();
		this.label = race.label;
		this.description = race.description;
		this.catTaille = race.catTaille;
		this.ageMax = race.ageMax;
		this.ajustNiv = race.ajustNiv;
		this.vitDeplac = race.vitDeplac;
		this.langues = race.langues;
		this.prediClasse = race.prediClasse;
		this.dons = race.dons;
		this.competences = race.competences;
		this.bonusSauv = race.bonusSauv;
		this.carac = race.carac;
	}

	public Race() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatTaille() {
		return catTaille;
	}

	public void setCatTaille(String catTaille) {
		this.catTaille = catTaille;
	}

	public String getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}

	public Short getAjustNiv() {
		return ajustNiv;
	}

	public void setAjustNiv(Short ajustNiv) {
		this.ajustNiv = ajustNiv;
	}

	public Short getVitDeplac() {
		return vitDeplac;
	}

	public void setVitDeplac(Short vitDeplac) {
		this.vitDeplac = vitDeplac;
	}

	public Set<String> getLangues() {
		return langues;
	}

	public void setLangues(Set<String> langues) {
		this.langues = langues;
	}

	public String getPrediClasse() {
		return prediClasse;
	}

	public void setPrediClasse(String prediClasse) {
		this.prediClasse = prediClasse;
	}

	public List<String> getDons() {
		return dons;
	}

	public void setDons(List<String> dons) {
		this.dons = dons;
	}

	public Set<String> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<String> competences) {
		this.competences = competences;
	}

	public HashMap<String, Short> getBonusSauv() {
		return bonusSauv;
	}

	public void setBonusSauv(HashMap<String, Short> bonusSauv) {
		this.bonusSauv = bonusSauv;
	}

	public HashMap<String, Short> getCarac() {
		return carac;
	}

	public void setCarac(HashMap<String, Short> carac) {
		this.carac = carac;
	}

	@Override
	public String toString() {
		return "Race [label=" + label + ", description=" + description + ", catTaille=" + catTaille + ", ageMax="
				+ ageMax + ", ajustNiv=" + ajustNiv + ", vitDeplac=" + vitDeplac + ", langues=" + langues
				+ ", prediClasse=" + prediClasse + ", dons=" + dons + ", competences=" + competences + ", bonusSauv="
				+ bonusSauv + ", carac=" + carac + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageMax == null) ? 0 : ageMax.hashCode());
		result = prime * result + ((ajustNiv == null) ? 0 : ajustNiv.hashCode());
		result = prime * result + ((bonusSauv == null) ? 0 : bonusSauv.hashCode());
		result = prime * result + ((carac == null) ? 0 : carac.hashCode());
		result = prime * result + ((catTaille == null) ? 0 : catTaille.hashCode());
		result = prime * result + ((competences == null) ? 0 : competences.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dons == null) ? 0 : dons.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((langues == null) ? 0 : langues.hashCode());
		result = prime * result + ((prediClasse == null) ? 0 : prediClasse.hashCode());
		result = prime * result + ((vitDeplac == null) ? 0 : vitDeplac.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (ageMax == null) {
			if (other.ageMax != null)
				return false;
		} else if (!ageMax.equals(other.ageMax))
			return false;
		if (ajustNiv == null) {
			if (other.ajustNiv != null)
				return false;
		} else if (!ajustNiv.equals(other.ajustNiv))
			return false;
		if (bonusSauv == null) {
			if (other.bonusSauv != null)
				return false;
		} else if (!bonusSauv.equals(other.bonusSauv))
			return false;
		if (carac == null) {
			if (other.carac != null)
				return false;
		} else if (!carac.equals(other.carac))
			return false;
		if (catTaille == null) {
			if (other.catTaille != null)
				return false;
		} else if (!catTaille.equals(other.catTaille))
			return false;
		if (competences == null) {
			if (other.competences != null)
				return false;
		} else if (!competences.equals(other.competences))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dons == null) {
			if (other.dons != null)
				return false;
		} else if (!dons.equals(other.dons))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (langues == null) {
			if (other.langues != null)
				return false;
		} else if (!langues.equals(other.langues))
			return false;
		if (prediClasse == null) {
			if (other.prediClasse != null)
				return false;
		} else if (!prediClasse.equals(other.prediClasse))
			return false;
		if (vitDeplac == null) {
			if (other.vitDeplac != null)
				return false;
		} else if (!vitDeplac.equals(other.vitDeplac))
			return false;
		return true;
	}
}
