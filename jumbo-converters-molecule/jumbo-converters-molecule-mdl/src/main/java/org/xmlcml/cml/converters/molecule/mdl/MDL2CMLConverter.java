package org.xmlcml.cml.converters.molecule.mdl;

import java.io.File;
import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.molecule.MoleculeModule;
import org.xmlcml.cml.element.CMLCml;
import org.xmlcml.cml.element.CMLMolecule;

public class MDL2CMLConverter extends AbstractConverter {
	private static final Logger LOG = Logger.getLogger(MDL2CMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	/**
	 * converts an MDL object to CML. returns cml:cml/cml:molecule
	 * 
	 * @param lines
	 */
	public Element convertToXML(List<String> lines) {
		CMLCml cml = new CMLCml();
		if (lines != null && lines.size() > 0) {
			MDLConverter converter = new MDLConverter();
			CMLMolecule molecule = converter.readMOL(lines);
			cml.appendChild(molecule);
		}
		return cml;
	}

	private static void usage() {
		System.out.println("usage: MDL2CMLConverter <file.mdl> <file.xml>");
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
		} else {
			MDL2CMLConverter converter = new MDL2CMLConverter();
			converter.convert(new File(args[0]), new File(args[1]));
		}
		
	}
	
	public MimeType getInputType() {
		return MoleculeModule.MDL_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "convert MDL molfile to CML";
	}

}
