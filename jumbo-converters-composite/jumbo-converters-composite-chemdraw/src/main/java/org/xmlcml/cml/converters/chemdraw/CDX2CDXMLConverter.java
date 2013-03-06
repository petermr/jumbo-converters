package org.xmlcml.cml.converters.chemdraw;

import java.io.ByteArrayInputStream;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.chemdraw.CDX2CDXML;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;

public class CDX2CDXMLConverter extends AbstractConverter {

	private static final Logger LOG = Logger.getLogger(CDX2CDXMLConverter.class);
	public static final String REG_MESSAGE = "Chemdraw CDX to CDXML conversion";
	static {
		LOG.setLevel(Level.INFO);
	}
	
	/**
	 * converts a CDK object to CML. returns cml:cml/cml:molecule
	 * 
	 * @param bytes
	 */
	public Element convertToXML(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		CDX2CDXML cd = new CDX2CDXML();
		try {
			cd.parseCDX(bais);
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse CDX", e);
		}
		return cd.getCDXMLObject();
// translates to ...		
//		Object cdx2Cdxml = null;
//		Class cdx2CdxmlClass = null;
//		Element result = null;
//		try {
//			cdx2CdxmlClass = Class.forName("org.xmlcml.cml.chemdraw.CDX2CDXML");
//			cdx2Cdxml = cdx2CdxmlClass.newInstance();
//		} catch (ClassNotFoundException e) {
//			getConverterLog().addToLog(Level.ERROR, "Cannot find org.xmlcml.cml.chemdraw package in classpath");
//		} catch (Exception e) {
//			getConverterLog().addToLog(Level.ERROR, "Cannot instantiate org.xmlcml.cml.chemdraw package in classpath");
//		}
//		if (cdx2Cdxml != null) {
//			try {
//				Method parseCDX = cdx2CdxmlClass.getMethod("parseCDX", InputStream.class);
//				parseCDX.invoke(cdx2Cdxml, bais);
//			} catch (Exception e) {
//				getConverterLog().addToLog(Level.ERROR, "cannot invoke parseCDX: "+e.getMessage());
//				throw new RuntimeException(e);
//			}
//			try {
//				Method getCDXMLObject = cdx2CdxmlClass.getMethod("getCDXMLObject");
//				result = (Element) getCDXMLObject.invoke(cdx2Cdxml);
//			} catch (Exception e) {
//				getConverterLog().addToLog(Level.ERROR, "cannot invove parseCDX: "+ e.getMessage());
//				throw new RuntimeException(e);
//			}
//		} else {
//			throw new RuntimeException("Cannot load org.xmlcml.cml.chemdraw package; check libraries");
//		}
//		return (Element) result;
	}
	
	public MimeType getInputType() {
		return ChemdrawModule.CDX_TYPE;
	}
	
	public MimeType getOutputType() {
		return ChemdrawModule.CDXML_TYPE;
	}
	
	public String getDescription() {
		return REG_MESSAGE;
	}


}
