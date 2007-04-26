package org.schorpp.planmich.dao;

import org.schorpp.planmich.domain.Plandatum;


public interface PlandatumDAO {

	public abstract Plandatum getPlandatum(Integer id);
	public abstract Integer savePlandatum(Plandatum m);

}