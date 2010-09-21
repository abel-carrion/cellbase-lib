package org.bioinfo.infrared.variation;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.variation.VariationFrequency;

public class VariationFrequencyDBManager extends DBManager {

	public VariationFrequencyDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	public VariationFrequency getBySnpId() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<VariationFrequency> getBySnpIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListByIds("select sf.name, sf.chromosome, sf.start, sf.end, sf.strand, sf.reference_allele, sf.other_allele, sf.allele_frequencies, sf.reference_allele_homozygotes, sf.allele_heterozygote, sf.other_allele_homozygote, sf.genotype_frequencies from snp_frequencies sf where sf.name = ? group by sf.name", ids, new BeanHandler(VariationFrequency.class));
	}
	
	public FeatureList<VariationFrequency> getBySnpIds(List<String> ids, double maf) {
		return null;
	}		
	
}
