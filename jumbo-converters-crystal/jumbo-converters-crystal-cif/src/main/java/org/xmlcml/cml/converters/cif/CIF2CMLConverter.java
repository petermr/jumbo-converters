package org.xmlcml.cml.converters.cif;

import java.io.File;
import java.util.List;

import nu.xom.Element;

import org.xmlcml.cif.CIF;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;


/** 
 * Converts a CIF into CML
 * using the other converters available
 * @author nwe23
 *
 */
public class CIF2CMLConverter extends AbstractConverter{

		
	private static final String REG_MESSAGE = "CIF to CML converter";

	public CIF2CMLConverter() {
		
	}
	
	/**
	 * converts CIF file (as lines) to CML.
	 * This involves conversion to (a) CIFXML (b) raw CML (c) conventionCML
	 * @param stringList
	 * @return "complete CML" 
	 */
	public Element convertToXML(List<String> stringList){
		CIF cifxml = this.cif2cifxml(stringList);
		Element rawCML = this.cifxml2cml(cifxml);
		Element cml = this.cml2compcml(rawCML);
		return cml;
	}

	private CIF cif2cifxml(List<String> stringList){
		CIF2CIFXMLConverter cif2cifxml=new CIF2CIFXMLConverter();
		CIF cif = cif2cifxml.parseLegacy(stringList);
		return cif;
	}
	
	private Element cifxml2cml(CIF cif){
		CIFXML2CMLConverter cifxml2cml = new CIFXML2CMLConverter();
		return cifxml2cml.convertToXML(cif);
	}
	
	private Element cml2compcml(Element cml){
		RawCML2CompleteCMLConverter conv = new RawCML2CompleteCMLConverter();
		return conv.convertToXML(cml);
	}

	public MimeType getInputType() {
		return CIFModule.CIF_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return REG_MESSAGE;
	}
	
	private static void usage() {
		System.out.println("usage: CIF2CMLConverter <file.mdl> <file.xml>");
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
		} else {
			CIF2CMLConverter converter = new CIF2CMLConverter();
			converter.convert(new File(args[0]), new File(args[1]));
		}
		
	}
	

}


