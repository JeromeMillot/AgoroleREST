package dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class Classe implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -5873631848656235329L;
	@Id	
	private String label;
    private short BAB;
    private short jetRef;
    private short jetVig;
    private short jetVol;
    private short shortDV;
    private boolean profane;
    private boolean divin;
    private boolean prestige;
    private String description;
    
	public Classe(String label, short bAB, short jetRef, short jetVig, short jetVol, short shortDV, boolean profane,
			boolean divin, boolean prestige, String description) {
		super();
		this.label = label;
		BAB = bAB;
		this.jetRef = jetRef;
		this.jetVig = jetVig;
		this.jetVol = jetVol;
		this.shortDV = shortDV;
		this.profane = profane;
		this.divin = divin;
		this.prestige = prestige;
		this.description = description;
	}

	public Classe() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public short getBAB() {
		return BAB;
	}

	public void setBAB(short bAB) {
		BAB = bAB;
	}

	public short getJetRef() {
		return jetRef;
	}

	public void setJetRef(short jetRef) {
		this.jetRef = jetRef;
	}

	public short getJetVig() {
		return jetVig;
	}

	public void setJetVig(short jetVig) {
		this.jetVig = jetVig;
	}

	public short getJetVol() {
		return jetVol;
	}

	public void setJetVol(short jetVol) {
		this.jetVol = jetVol;
	}

	public short getShortDV() {
		return shortDV;
	}

	public void setShortDV(short shortDV) {
		this.shortDV = shortDV;
	}

	public boolean isProfane() {
		return profane;
	}

	public void setProfane(boolean profane) {
		this.profane = profane;
	}

	public boolean isDivin() {
		return divin;
	}

	public void setDivin(boolean divin) {
		this.divin = divin;
	}

	public boolean isPrestige() {
		return prestige;
	}

	public void setPrestige(boolean prestige) {
		this.prestige = prestige;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Classe [label=" + label + ", BAB=" + BAB + ", jetRef=" + jetRef + ", jetVig=" + jetVig + ", jetVol="
				+ jetVol + ", shortDV=" + shortDV + ", profane=" + profane + ", divin=" + divin + ", prestige="
				+ prestige + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + BAB;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (divin ? 1231 : 1237);
		result = prime * result + jetRef;
		result = prime * result + jetVig;
		result = prime * result + jetVol;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (prestige ? 1231 : 1237);
		result = prime * result + (profane ? 1231 : 1237);
		result = prime * result + shortDV;
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
		Classe other = (Classe) obj;
		if (BAB != other.BAB)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (divin != other.divin)
			return false;
		if (jetRef != other.jetRef)
			return false;
		if (jetVig != other.jetVig)
			return false;
		if (jetVol != other.jetVol)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (prestige != other.prestige)
			return false;
		if (profane != other.profane)
			return false;
		if (shortDV != other.shortDV)
			return false;
		return true;
	}
	
    
}
