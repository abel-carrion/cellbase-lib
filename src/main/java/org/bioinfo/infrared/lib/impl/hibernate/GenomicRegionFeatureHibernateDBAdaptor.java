package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicVariant;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Deprecated
public class GenomicRegionFeatureHibernateDBAdaptor extends HibernateDBAdaptor implements GenomicRegionFeatureDBAdaptor {

	private static int FEATURE_MAP_CHUNK_SIZE = 1000;

	public GenomicRegionFeatureHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	public GenomicRegionFeatureHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions) {
		return this.getAllByRegionList(regions, null);
	}

	@Override
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions, List<String> sources) {
		List<GenomicRegionFeatures> result = new ArrayList<GenomicRegionFeatures>();
		for (int i = 0; i < regions.size(); i++) {
			result.add(this.getByRegion(regions.get(i), sources));
		}
		return result;
	}


	@Override
	public GenomicRegionFeatures getByRegion(String chromosome, int start, int end) {
		return getByRegion(chromosome, start, end, null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), null);
	}

	@Override
	public GenomicRegionFeatures getByRegion(Region region, List<String> sources) {
		return getByRegion(region.getChromosome(), region.getStart(), region.getEnd(), sources);
	}

	@SuppressWarnings("unchecked")
	private List<FeatureMap> getFeatureMapsByRegion(String chromosome, int start, int end, List<String> sources, Session session) {
		int chunk_start = start / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		System.out.println("chunk start: "+chunk_start+" --- Chunk end: "+chunk_end);

		Query query;
		if (chunk_start == chunk_end){
			query = this.openSession().createQuery("select featureMap from FeatureMap as featureMap where id.chunkId = :chunk_start and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome = :CHROMOSOME");
		}
		else{
			query = this.openSession().createQuery("select featureMap from FeatureMap as featureMap where id.chunkId >= :chunk_start and id.chunkId <= :end_chunk and featureMap.start<= :endparam and featureMap.end >= :startparam and chromosome= :CHROMOSOME");
			query.setParameter("end_chunk", chunk_end);
		}
				System.out.println("Chromsoome: " + chromosome);
				System.out.println("Query: " + query.getQueryString());
		query.setParameter("CHROMOSOME", chromosome);
		query.setParameter("chunk_start", chunk_start);
		query.setParameter("startparam", start);
		query.setParameter("endparam", end);
		return (List<FeatureMap>)execute(query);
	}

	@Override
	public List<FeatureMap> getFeatureMapsByRegion(Region region) {
		System.out.println("Region: " + region.getChromosome()+":"+region.getStart()+"-"+region.getEnd());
		return getFeatureMapsByRegion(region.getChromosome(), region.getStart(), region.getEnd(), null, null);
	}


	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources, Session session) {
		//		long t0 = System.currentTimeMillis();
		List<FeatureMap> result = this.getFeatureMapsByRegion(chromosome, start, end, sources, session);
		//		System.out.println("\tDB Recovering features map: "+(System.currentTimeMillis()-t0)+" ms");
		//		t0 = System.currentTimeMillis();
		GenomicRegionFeatures genomicRegionFeatures = new GenomicRegionFeatures(new Region(chromosome, start, end), result, this.getSessionFactory()); 
		//		System.out.println("\tFILLING OBJECT: "+(System.currentTimeMillis()-t0)+" ms");
		return genomicRegionFeatures;
	}

	private GenomicRegionFeatures getByRegion(String chromosome, int start, int end, List<String> sources) {
		Session session = this.openSession();
		GenomicRegionFeatures result = this.getByRegion(chromosome, start, end, sources, session);
		session.close();
		return result;
	}





	@SuppressWarnings("unused")
	private GenomicRegionFeatures getGenomicRegionFeature(List<FeatureMap> featuresMap, Region region, List<String> sources) {
		return null;
	}



	@SuppressWarnings("unchecked")
	@Deprecated
	private List<FeatureMap> getByChunkis(List<Integer> chunks, List<String> sources, Session session) {
		Query query;
		query = session.createQuery("select featureMap from FeatureMap as featureMap where id.chunkId in :chunks");			
		query.setParameterList("chunks", chunks);
		return (List<FeatureMap>)execute(query);

	}

	@Deprecated
	@Override
	public List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants, List<String> sources) {

		Session session = this.openSession();

		List<Integer> chunks = new ArrayList<Integer>();
		for (GenomicVariant variant : variants) {
			chunks.add(variant.getPosition() / GenomicRegionFeatureHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE);


		}

		List<FeatureMap> result = this.getByChunkis(chunks, sources, session);
		session.close();

		System.out.println("Features " + result.size());
		List<GenomicRegionFeatures> resultList = new ArrayList<GenomicRegionFeatures>();
		for (GenomicVariant variant : variants) {
			List<FeatureMap> featurePerVariant = new ArrayList<FeatureMap>();
			for (FeatureMap featureMap : result) {
				if(featureMap.getChromosome().equals(variant.getChromosome())){
					if(featureMap.getStart()<= variant.getPosition() && featureMap.getEnd() >= variant.getPosition()){
						featurePerVariant.add(featureMap);
					}
				}
			}

			resultList.add(new GenomicRegionFeatures(new Region(variant.getChromosome(), variant.getPosition(), variant.getPosition()), featurePerVariant, this.getSessionFactory()));
		}
		return resultList;
	}

	@Deprecated
	@Override
	@SuppressWarnings("unchecked")
	public List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants) {
		return this.getByVariants(variants, null);
	}










}
