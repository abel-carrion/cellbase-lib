package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.lib.common.Region;

public interface RegulatoryRegionDBAdaptor extends FeatureDBAdaptor {

	public List<RegulatoryRegion> getAllByRegion(String chromosome);

	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start);

	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end);

	public List<RegulatoryRegion> getAllByRegion(Region region);

	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList);
	

	public List<RegulatoryRegion> getAllByRegion(String chromosome, List<String> type);

	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, List<String> type);

	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end,List<String> type);

	public List<RegulatoryRegion> getAllByRegion(Region region, String type);

	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList,List<String> type);

	public List<RegulatoryRegion> getAllByInternalIdList(List<String> idList);

	public List<RegulatoryRegion> getAllByInternalId(String id);
	

}