package org.schorpp.planmich.dao;

import java.util.List;

import org.schorpp.planmich.domain.Mandant;

public interface MandantDAO {

	public abstract Mandant getMandantById(Integer id);
	public abstract Integer saveMandant(Mandant m);
	public abstract List<Mandant> getMandanten();
	public abstract Mandant getMandantByName(String name);
	public abstract void delete(Mandant m);
	public abstract void update(Mandant m);

}