package org.bioinfo.infrared.core.dbsql;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.Chromosome;

public class KaryotypeDBManager extends DBManager {

	public static final String GET_ALL_CHROMOSOMES = "select distinct chromosome, min(start), max(end) from karyotype group by chromosome";
	public static final String GET_CHROMOSOME_BY_ID = "select distinct chromosome, min(start), max(end) from karyotype where chromosome = ? group by chromosome";
	
	public KaryotypeDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Chromosome> getAllChromosomes() throws Exception{
		return getFeatureList(GET_ALL_CHROMOSOMES, new BeanArrayListHandler(Chromosome.class));
	}

	@SuppressWarnings("unchecked")
	public Chromosome getChromosomeById(String id) throws Exception{
		return (Chromosome) getFeatureById(GET_CHROMOSOME_BY_ID, id, new BeanHandler(Chromosome.class));
	}	
}
