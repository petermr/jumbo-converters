package org.xmlcml.cml.converters.molecule.mdl;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLBuilder;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.cml.CMLSelector;
import org.xmlcml.cml.converters.molecule.MoleculeModule;
import org.xmlcml.cml.converters.molecule.mdl.MDLConverter.CoordType;
import org.xmlcml.cml.element.CMLMolecule;

public class CML2MDLConverter extends AbstractConverter {
	
	private static final Logger LOG = Logger.getLogger(CML2MDLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	private MDLConverter mdlConverter;

	public MDLConverter getMdlConverter() {
		return mdlConverter;
	}

	public void setMdlConverter(MDLConverter mdlConverter) {
		this.mdlConverter = mdlConverter;
	}
	
	public CML2MDLConverter() {
		mdlConverter = new MDLConverter();
	}

	/**
	 * converts a CML object to MDL. assumes a single CMLMolecule as descendant
	 * of root
	 * 
	 * @param xml
	 */
	public List<String> convertToText(Element xml) {
		CMLElement cml = CMLBuilder.ensureCML(xml);
		CMLMolecule molecule = new CMLSelector(cml).getToplevelMoleculeDescendant(true);
		List<String> lines = new ArrayList<String>();
		if (molecule != null) {
			mdlConverter.setCoordType(CoordType.EITHER);
			mdlConverter.writeMOL(lines, molecule);
		}
		return lines;
	}

	public MimeType getInputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public MimeType getOutputType() {
		return MoleculeModule.MDL_TYPE;
	}
	
	public String getDescription() {
		return "convert CML to MDL molfile";
	}

}
