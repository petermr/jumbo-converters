package org.xmlcml.cml.converters.compchem.turbomole;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.turbomole.log.TurbomoleLog2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class TurbomoleModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-turbomole-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-tuebomole-log-xml", ObjectType.XML, "log.xml");
	private static final String PREFIX = "turbomole";
	
    public TurbomoleModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new TurbomoleLog2XMLConverter());
		return converterList;
	}
	
	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
		}
		return mimeTypeList;
	}

	
}
