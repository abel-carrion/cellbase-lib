package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.SpliceSite;

public class SpliceSiteDBManager extends DBManager {
	
	public static final String GET_ALL_BY_SNP = "select * from splice_site where snp_name = ? ";
	
	public SpliceSiteDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	
	
	@SuppressWarnings("unchecked")
	public FeatureList<SpliceSite> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_ALL_BY_SNP, snpId, new BeanArrayListHandler(SpliceSite.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<SpliceSite>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_ALL_BY_SNP, snpIds, new BeanArrayListHandler(SpliceSite.class));
	}
//	
//	@SuppressWarnings("unchecked")
//	public SpliceSitesList getAllByTranscriptId(String transId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new SpliceSitesList((List<SpliceSites>)getFeatureList("select * from splice_sites where ensembl_transcript_id = ? ", transId, new BeanArrayListHandler(SpliceSites.class)).getElements());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public SpliceSitesList getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new SpliceSitesList((List<SpliceSites>)getFeatureList("select * from splice_sites where ensembl_gene_id = ? ", geneId, new BeanArrayListHandler(SpliceSites.class)).getElements());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<SpliceSitesList> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<FunctionalPropertyList> featList = getListOfFeatureList("select * from splice_sites where snp_id = ? ",snpIds, new BeanArrayListHandler(SpliceSites.class));
//		List<SpliceSitesList> SpliceSitesList = new ArrayList<SpliceSitesList>(featList.size());
//		for(FunctionalPropertyList pupas: featList) {
//			SpliceSitesList.add(new SpliceSitesList((List<SpliceSites>)pupas.getElements()));
//		}
//		return SpliceSitesList;
//	}
	
}