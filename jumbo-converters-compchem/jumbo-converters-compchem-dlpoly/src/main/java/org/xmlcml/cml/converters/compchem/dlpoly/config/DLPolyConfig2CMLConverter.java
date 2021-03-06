package org.xmlcml.cml.converters.compchem.dlpoly.config;

import java.io.File;

import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.dlpoly.DLPolyModule;
import org.xmlcml.cml.element.CMLCml;

public class DLPolyConfig2CMLConverter extends AbstractConverter {

	private static final Logger LOG = Logger.getLogger(DLPolyConfig2CMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	public static final String DLPOLY_MOL_TO_CML_CONVERTER = "DLPoly Molecule to CML Converter";

	public DLPolyConfig2CMLConverter() {
		
	}
	
	/**
	 * converts an MDL object to CML. returns cml:cml/cml:molecule
	 * 
	 * @param lines
	 */
	public Element convertToXML(List<String> lines) {
		CMLCml cml = null;
		if (lines != null && lines.size() > 0) {
			ConfigProcessor processor = new ConfigProcessor();
			cml = processor.create(lines);
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
			DLPolyConfig2CMLConverter converter = new DLPolyConfig2CMLConverter();
			converter.convert(new File(args[0]), new File(args[1]));
		}
		
	}
		
	public MimeType getInputType() {
		return DLPolyModule.MOL_TYPE;
	}

	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}

	public String getDescription() {
		return DLPOLY_MOL_TO_CML_CONVERTER;
	}
}