package org.schorpp.planmich.dao;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;

public interface KategorieDAO {

	public abstract Kategorie getKategorieById(Integer id);

	public abstract List<Kategorie> getKategorien();

	public abstract Kategorie getKategorieByName(String name);

	public abstract void delete(Kategorie k);

	public abstract void delete(Integer id);
	
	public abstract void saveKategorie(Kategorie k);

}