package org.xmlcml.cml.converters.compchem.dalton;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.dalton.log.DaltonLog2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class DaltonModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-dalton-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-dalton-log-xml", ObjectType.XML, "log.xml");
	private static final String PREFIX = "dalton";
	
    public DaltonModule() {
    	super();
    }

	public List<Converter> getConverterList() {
		if (converterList == null) {
			converterList = new ArrayList<Converter>();
	        converterList.add(new DaltonLog2XMLConverter());
		}
		return converterList;
    }

	@Override
	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
		}
		return mimeTypeList;
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}
}
