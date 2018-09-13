package dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Carac implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 4302627952858755647L;
	@Id
	private String label;
	private String description;
	private short points;
	
	public Carac(String label, String description, short points) {
		super();
		this.label = label;
		this.description = description;
		this.points = points;
	}

	public Carac() {
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

	public short getPoints() {
		return points;
	}

	public void setPoints(short points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Carac [label=" + label + ", description=" + description + ", points=" + points + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + points;
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
		Carac other = (Carac) obj;
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
		if (points != other.points)
			return false;
		return true;
	}
	

}
