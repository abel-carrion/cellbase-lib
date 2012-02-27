package org.bioinfo.infrared.lib.common;

import java.util.ArrayList;
import java.util.List;

public class GenomicVariant {

	private String chromosome;
	private int position;
	private String alternative;
	
	public GenomicVariant(String chromosome, int position, String alternative) {
		this.chromosome = chromosome;
		this.position = position;
		this.alternative = alternative;
	}

	public static GenomicVariant parseVariant(String variantString) {
		GenomicVariant genomicVariant = null;
		if(variantString != null && !variantString.equals("")) {
			//	if(regionString.indexOf(':') != -1) {
			String[] fields = variantString.split("[:-]", -1);
			if(fields.length == 3) {
				genomicVariant = new GenomicVariant(fields[0], Integer.parseInt(fields[1]), fields[2]);
			}else {
				genomicVariant = null;
			}
			//	}else {
			//		genomicVariant = new GenomicVariant(regionString, 0, "");
			//	}
		}
		return genomicVariant;
	}

	public static List<GenomicVariant> parseVariants(String variantsString) {
		List<GenomicVariant> genomicVariants = null;
		if(variantsString != null && !variantsString.equals("")) {
			String[] regionItems = variantsString.split(",");
			genomicVariants = new ArrayList<GenomicVariant>(regionItems.length);
			String[] fields;
			for(String regionString: regionItems) {
//				if(regionString.indexOf(':') != -1) {
					fields = regionString.split("[:-]", -1);
					if(fields.length == 3) {
						genomicVariants.add(new GenomicVariant(fields[0], Integer.parseInt(fields[1]), fields[2]));
					}else {
						genomicVariants.add(null);
					}
//				}else {
//					genomicVariants.add(new GenomicVariant(regionString, 0, new String()));
//				}
			}	
		}
		return genomicVariants;
	}

	/**
	 * 
	 * @param variantList
	 * @return A comma separated string with all the regions. If parameter is null then a null objects is returned, an empty string is returned if parameter size list is 0 
	 */
	public static String parseRegionList(List<GenomicVariant> variantList) {
		if(variantList == null) {
			return null;
		}else {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<variantList.size()-1; i++) {
				if(variantList.get(i) != null) {
					sb.append(variantList.get(i).toString()).append(",");					
				}else {
					sb.append("null,");
				}
			}
			if(variantList.get(variantList.size()-1) != null) {
				sb.append(variantList.get(variantList.size()-1).toString());					
			}else {
				sb.append("null");
			}

			return sb.toString();
		}
	}


	@Override
	public String toString() {
		return chromosome+":"+position+":"+this.getAlternative(); 
	}


	/**
	 * @return the chromosome
	 */
	public String getChromosome() {
		return chromosome;
	}

	/**
	 * @param chromosome the chromosome to set
	 */
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}


	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}


	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	public String getAlternative() {
		return alternative;
	}

}
