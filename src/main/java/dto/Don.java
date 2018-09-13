package dto;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class Don implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 5704950739371294904L;
	@Id
	private String label;
	private String description;
	private HashMap<String, Short> bonuses;
	
	public Don(String label, String description, HashMap<String, Short> bonuses) {
		super();
		this.label = label;
		this.description = description;
		this.bonuses = bonuses;
	}

	public Don() {
		super();
		// TODO Auto-generated constructor stub
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

	public HashMap<String, Short> getBonuses() {
		return bonuses;
	}

	public void setBonuses(HashMap<String, Short> bonuses) {
		this.bonuses = bonuses;
	}

	@Override
	public String toString() {
		return "Don [label=" + label + ", description=" + description + ", bonuses=" + bonuses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Don other = (Don) obj;
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	

}
