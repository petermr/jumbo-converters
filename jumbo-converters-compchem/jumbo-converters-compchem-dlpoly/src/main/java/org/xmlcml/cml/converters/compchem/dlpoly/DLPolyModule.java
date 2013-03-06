package org.xmlcml.cml.converters.compchem.dlpoly;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.dlpoly.config.DLPolyConfig2CMLConverter;
import org.xmlcml.cml.converters.compchem.dlpoly.log.DLPolyLog2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author pm286
 */
public class DLPolyModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-dlpoly-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-dlpoly-log-xml", ObjectType.XML, "log.xml");
	public static final MimeType MOL_TYPE = new MimeType("chemical/x-dlpoly-config", ObjectType.TEXT, "config");
	private static final String PREFIX = "dlpoly";
	
    public DLPolyModule() {
    	super();
    }

	public List<Converter> getConverterList() {
		if (converterList == null) {
			converterList = new ArrayList<Converter>();
			converterList.add(new DLPolyLog2XMLConverter());
			converterList.add(new DLPolyConfig2CMLConverter());
		}
		return converterList;
    }

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
			mimeTypeList.add(MOL_TYPE);
		}
		return mimeTypeList;
	}

	public String getPrefix() {
		return PREFIX;
	}
	
}
