package org.xmlcml.cml.converters.spectrum;

import java.util.ArrayList;

import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType.ObjectType;
//import org.xmlcml.cml.converters.MimeType;
//import org.xmlcml.cml.converters.registry.AbstractConverterModule;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;


/**
 * @author Sam Adams
 */
public class SpectrumModule extends AbstractConverterModule {
	public static final String AGILENT_IN = null;
	public final static String JDX = "jcamp-dx";
	public static final String OSCAR = "oscar_data";
	public static final String SVG = "spectrum_svg";
	public static final String CML_HNMR = "hnmr_cml";
	public static final String AGILENT = "agilent_gcms";
	public static final String CML = "cml_spect";
	public static final String BRUKER_JDX = "Bruker JDX";
	
	public static final String SPECTRUM = "spectrum";
	public static final String FULL_SPECTRUM_DIR = "src/test/resources/spectrum";
	public static final String AGILENT_DIR = SPECTRUM+"/agilent";
	public static final String BRUKER_DIR = SPECTRUM+"/bruker";
	public static final String GRAPHICS_DIR = SPECTRUM+"/graphics";
	public static final String GRAPHICS_SVG2CML_DIR = GRAPHICS_DIR+"/svg2cml";
	public static final String JDX_DIR = SPECTRUM+"/jdx";
	public static final String JDX_CML2JDX_DIR = JDX_DIR+"/cml2jdx";
	public static final String JDX_JDX2CML_DIR = JDX_DIR+"/jdx2cml";
	public static final String OSCAR_DIR = SPECTRUM+"/oscar";
	public static final String SVG_DIR = SPECTRUM+"/svg";
	public static final String SVG_CML2SVG_DIR = SVG_DIR+"/cml2svg";
	public static final String SVG_HNMR2SVG_DIR = SVG_DIR+"/hnmr2svg";
	
	public static final MimeType AGILENT_TYPE = new MimeType("chemical/x-agilent", ObjectType.TEXT, "dat");
	public static final MimeType BRUKER_JDX_TYPE = new MimeType("chemical/x-bruker", ObjectType.TEXT, "jdx");
	public static final MimeType OSCAR_XML_TYPE = new MimeType("chemical/x-oscar-xml", ObjectType.XML, "xml");
	public static final MimeType JDX_TYPE = new MimeType("chemical/x-jcampdx", ObjectType.TEXT, "jdx");

	private static final String PREFIX = "spectrum";

    public String getPrefix() {
    	return PREFIX;
    }

    public SpectrumModule() {
    	super();
    }

	public List<Converter> getConverterList() {
		if (converterList == null) {
		    converterList = new ArrayList<Converter>();
		    try {
    	    converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.bruker.Bruker2XMLConverter").newInstance());
	        converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.jdx.cml2jdx.CMLSpect2JDXConverter").newInstance());
	        converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.jdx.jdx2cml.JDX2CMLConverter").newInstance());
	        converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.jdx.oscar.OSCAR2CMLSpectConverter").newInstance());
		    } catch (Exception e) {
		    	throw new RuntimeException("Cannot create class: ", e);
		    }
//	      converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.jdx.CMLHNMRSpect2SVGConverter").newInstance());
//	      converterList.add((Converter) Class.forName("org.xmlcml.cml.converters.spectrum.jdx.CMLSpect2SVGConverter").newInstance());
		}
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(AGILENT_TYPE);
			mimeTypeList.add(BRUKER_JDX_TYPE);
			mimeTypeList.add(JDX_TYPE);
			mimeTypeList.add(OSCAR_XML_TYPE);
		}
		return mimeTypeList;
	}
	
}
