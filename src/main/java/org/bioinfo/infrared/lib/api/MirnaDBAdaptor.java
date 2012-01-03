package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.MirnaGene;
import org.bioinfo.infrared.core.cellbase.MirnaMature;
import org.bioinfo.infrared.core.cellbase.MirnaTarget;
import org.bioinfo.infrared.lib.common.Region;

public interface MirnaDBAdaptor {

	
	public List<MirnaGene> getMiRnaGeneByName(String geneName);
	
	public List<List<MirnaGene>> getAllMiRnaGenesByNameList(List<String> geneNames);
	
	public List<MirnaGene> getAllMiRnaGenesByMiRnaMature(String miRnaMatureName);
	
	public List<List<MirnaGene>> getAllMiRnaGenesByMiRnaMatureList(List<String> miRnaMatureNameList);
	
	public List<MirnaGene> getAllMiRnaGenesByDisease(String disease);
	
	public List<List<MirnaGene>> getAllMiRnaGenesByDiseaseList(List<String> diseaseList);
	
	public List<MirnaGene> getAllMiRnaGenesByGeneName(String geneName);
	
	public List<List<MirnaGene>> getAllMiRnaGenesByGeneNameList(List<String> geneNames);
	
	

	public List<MirnaMature> getMiRnaMatureByName(String miRnaMatureName);
	
	public List<List<MirnaMature>> getAllMiRnaMaturesByNameList(List<String> miRnaMatureNameList);
	
	public List<MirnaMature> getAllMiRnaMaturesByMiRnaGene(String miRnaGeneName);
	
	public List<List<MirnaMature>> getAllMiRnaMaturesByMiRnaGeneList(List<String> miRnaGeneNameList);
	
	public List<MirnaMature> getAllMiRnaMaturesByGeneName(String geneName);
	
	public List<List<MirnaMature>> getAllMiRnaMaturesByGeneNameList(List<String> geneNames);

	
	
	public List<MirnaTarget> getAllMiRnaTargetsByMiRnaMature(String id);

	public List<List<MirnaTarget>> getAllMiRnaTargetsByMiRnaMatureList(List<String> ids);
	
	public List<MirnaTarget> getAllMiRnaTargetsByMiRnaGene(String geneName);
	
	public List<List<MirnaTarget>> getAllMiRnaTargetsByMiRnaGeneList(List<String> geneNames);
	
	public List<MirnaTarget> getAllMiRnaTargetsByGeneName(String geneName);
	
	public List<List<MirnaTarget>> getAllMiRnaTargetsByGeneNameList(List<String> geneNames);
	
	public List<MirnaTarget> getAllMiRnaTargetsByPosition(String chromosome, int start);
	
	public List<MirnaTarget> getAllMiRnaTargetsByRegion(String chromosome, int start, int end);
	
	public List<MirnaTarget> getAllMiRnaTargetsByRegion(Region region);

	public List<List<MirnaTarget>> getAllMiRnaTargetsByRegionList(List<Region> regionList);

	
	
	public List<MirnaDisease> getAllMiRnaDiseasesByMiRnaGene(String mirbaseId);

	public List<List<MirnaDisease>> getAllMiRnaDiseasesByMiRnaGeneList(List<String> mirbaseId);
	
	public List<MirnaDisease> getAllMiRnaDiseasesByMiRnaMature(String mirbaseId);

	public List<List<MirnaDisease>> getAllMiRnaDiseasesByMiRnaMatureList(List<String> mirbaseId);

	
	
	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationBySourceList(List<String> sourceList);


}
