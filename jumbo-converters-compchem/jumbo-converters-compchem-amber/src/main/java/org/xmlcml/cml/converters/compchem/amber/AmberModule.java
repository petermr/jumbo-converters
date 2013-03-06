package org.xmlcml.cml.converters.compchem.amber;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.amber.in.AmberFF2XMLConverter;
import org.xmlcml.cml.converters.compchem.amber.in.AmberFFXML2CMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class AmberModule extends AbstractConverterModule {

	public static final MimeType FF_TYPE = new MimeType("chemical/x-amber-ff", ObjectType.TEXT, "ff");
	public static final MimeType FF_XML_TYPE = new MimeType("chemical/x-amber-ff-xml", ObjectType.XML, "xml");
	public static final MimeType MDOUT_XML_TYPE = new MimeType("chemical/x-amber-mdout-xml", ObjectType.XML, "xml");
	private static final String PREFIX = "amber";
	
    public AmberModule(){
    }
    
    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new AmberFF2XMLConverter());
		converterList.add(new AmberFFXML2CMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(FF_TYPE);
			mimeTypeList.add(FF_XML_TYPE);
			mimeTypeList.add(MDOUT_XML_TYPE);
		}
		return mimeTypeList;
	}
	
	
}
