package fr.agrorole.dnd.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class PJ implements Serializable {
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -8723363296193191856L;
	@Id
	@Column(name="NAME")
    private String nom;
    private int age;
    @Column(name="WGT")
    private int poids;
    @Column(name="XP")
    private long experience;
    @Column(name="COULP")
    private String couleurPeau;
    @Column(name="COULY")
    private String couleurYeux;
    @Column(name="COULC")
    private String couleurCheveux;
    private String race;
    private HashMap<String, Short> classes;
    @Column(name="FOR")
    private short force;
    @Column(name="DEX")
    private short dexterite;
    @Column(name="CON")
    private short constitution;
    @Column(name="INT")
    private short intelligence;
    @Column(name="SAG")
    private short sagesse;
    @Column(name="CHA")
    private short charisme;
    private List<String> dons;
    @Column(name="COMP")
    private HashMap<String, Short> competences;
    @Column(name="SAUV_VOL")
    private short sauvVolonte;
    @Column(name="SAUV_VIG")
    private short sauvVigueur;
    @Column(name="SAUV_REF")
    private short sauvReflexe;
    private short CA = 10;
    @Id
    @Column(name="JOUEUR")    
    private String user;
	

	public PJ(String nom, int age, int poids, long experience, String couleurPeau, String couleurYeux,
			String couleurCheveux, String race, HashMap<String, Short> classes, short force, short dexterite,
			short constitution, short intelligence, short sagesse, short charisme, List<String> dons,
			HashMap<String, Short> competences, short sauvVolonte, short sauvVigueur, short sauvReflexe, short cA,
			String user) {
		super();
		this.nom = nom;
		this.age = age;
		this.poids = poids;
		this.experience = experience;
		this.couleurPeau = couleurPeau;
		this.couleurYeux = couleurYeux;
		this.couleurCheveux = couleurCheveux;
		this.race = race;
		this.classes = classes;
		this.force = force;
		this.dexterite = dexterite;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.sagesse = sagesse;
		this.charisme = charisme;
		this.dons = dons;
		this.competences = competences;
		this.sauvVolonte = sauvVolonte;
		this.sauvVigueur = sauvVigueur;
		this.sauvReflexe = sauvReflexe;
		CA = cA;
		this.user = user;
	}

	public PJ() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public long getExperience() {
		return experience;
	}

	public void setExperience(long experience) {
		this.experience = experience;
	}

	public String getCouleurPeau() {
		return couleurPeau;
	}

	public void setCouleurPeau(String couleurPeau) {
		this.couleurPeau = couleurPeau;
	}

	public String getCouleurYeux() {
		return couleurYeux;
	}

	public void setCouleurYeux(String couleurYeux) {
		this.couleurYeux = couleurYeux;
	}

	public String getCouleurCheveux() {
		return couleurCheveux;
	}

	public void setCouleurCheveux(String couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public HashMap<String, Short> getClasses() {
		return classes;
	}

	public void setClasses(HashMap<String, Short> classes) {
		this.classes = classes;
	}

	public short getForce() {
		return force;
	}

	public void setForce(short force) {
		this.force = force;
	}

	public short getDexterite() {
		return dexterite;
	}

	public void setDexterite(short dexterite) {
		this.dexterite = dexterite;
	}

	public short getConstitution() {
		return constitution;
	}

	public void setConstitution(short constitution) {
		this.constitution = constitution;
	}

	public short getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(short intelligence) {
		this.intelligence = intelligence;
	}

	public short getSagesse() {
		return sagesse;
	}

	public void setSagesse(short sagesse) {
		this.sagesse = sagesse;
	}

	public short getCharisme() {
		return charisme;
	}

	public void setCharisme(short charisme) {
		this.charisme = charisme;
	}

	public List<String> getDons() {
		return dons;
	}

	public void setDons(List<String> dons) {
		this.dons = dons;
	}
	
	public HashMap<String, Short> getCompetences() {
		return competences;
	}

	public void setCompetences(HashMap<String, Short> competences) {
		this.competences = competences;
	}

	public short getSauvVolonte() {
		return sauvVolonte;
	}

	public void setSauvVolonte(short sauvVolonte) {
		this.sauvVolonte = sauvVolonte;
	}

	public short getSauvVigueur() {
		return sauvVigueur;
	}

	public void setSauvVigueur(short sauvVigueur) {
		this.sauvVigueur = sauvVigueur;
	}

	public short getSauvReflexe() {
		return sauvReflexe;
	}

	public void setSauvReflexe(short sauvReflexe) {
		this.sauvReflexe = sauvReflexe;
	}

	public short getCA() {
		return CA;
	}

	public void setCA(short cA) {
		CA = cA;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CA;
		result = prime * result + age;
		result = prime * result + charisme;
		result = prime * result + ((classes == null) ? 0 : classes.hashCode());
		result = prime * result + ((competences == null) ? 0 : competences.hashCode());
		result = prime * result + constitution;
		result = prime * result + ((couleurCheveux == null) ? 0 : couleurCheveux.hashCode());
		result = prime * result + ((couleurPeau == null) ? 0 : couleurPeau.hashCode());
		result = prime * result + ((couleurYeux == null) ? 0 : couleurYeux.hashCode());
		result = prime * result + dexterite;
		result = prime * result + ((dons == null) ? 0 : dons.hashCode());
		result = prime * result + (int) (experience ^ (experience >>> 32));
		result = prime * result + force;
		result = prime * result + intelligence;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + poids;
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + sagesse;
		result = prime * result + sauvReflexe;
		result = prime * result + sauvVigueur;
		result = prime * result + sauvVolonte;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		PJ other = (PJ) obj;
		if (CA != other.CA)
			return false;
		if (age != other.age)
			return false;
		if (charisme != other.charisme)
			return false;
		if (classes == null) {
			if (other.classes != null)
				return false;
		} else if (!classes.equals(other.classes))
			return false;
		if (competences == null) {
			if (other.competences != null)
				return false;
		} else if (!competences.equals(other.competences))
			return false;
		if (constitution != other.constitution)
			return false;
		if (couleurCheveux == null) {
			if (other.couleurCheveux != null)
				return false;
		} else if (!couleurCheveux.equals(other.couleurCheveux))
			return false;
		if (couleurPeau == null) {
			if (other.couleurPeau != null)
				return false;
		} else if (!couleurPeau.equals(other.couleurPeau))
			return false;
		if (couleurYeux == null) {
			if (other.couleurYeux != null)
				return false;
		} else if (!couleurYeux.equals(other.couleurYeux))
			return false;
		if (dexterite != other.dexterite)
			return false;
		if (dons == null) {
			if (other.dons != null)
				return false;
		} else if (!dons.equals(other.dons))
			return false;
		if (experience != other.experience)
			return false;
		if (force != other.force)
			return false;
		if (intelligence != other.intelligence)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (poids != other.poids)
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (sagesse != other.sagesse)
			return false;
		if (sauvReflexe != other.sauvReflexe)
			return false;
		if (sauvVigueur != other.sauvVigueur)
			return false;
		if (sauvVolonte != other.sauvVolonte)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
