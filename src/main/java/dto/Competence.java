package dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;
@Entity
public class Competence implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 3048286944318185005L;

	private String label;
	private String decription;
	private short points;
	
	public Competence(String label, String decription, short points) {
		super();
		this.label = label;
		this.decription = decription;
		this.points = points;
	}

	public Competence() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public short getPoints() {
		return points;
	}

	public void setPoints(short points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Competence [label=" + label + ", decription=" + decription + ", points=" + points + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decription == null) ? 0 : decription.hashCode());
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
		Competence other = (Competence) obj;
		if (decription == null) {
			if (other.decription != null)
				return false;
		} else if (!decription.equals(other.decription))
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
