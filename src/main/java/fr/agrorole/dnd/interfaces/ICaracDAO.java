package fr.agrorole.dnd.interfaces;

import java.util.List;

import fr.agrorole.dnd.dto.Carac;

public interface ICaracDAO {

	public List<Carac> getListCarac();
	public Carac getCaracByKw(String kw);
}
