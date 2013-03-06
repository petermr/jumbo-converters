package org.xmlcml.cml.converters.compchem.nwchem;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2CompchemConverter;
import org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author pm286
 */
public class NWChemModule extends AbstractConverterModule {

	public static final MimeType INPUT_TYPE = new MimeType("chemical/x-nwchem-input", ObjectType.TEXT, "inp");
	public static final MimeType LOG_TYPE = new MimeType("chemical/x-nwchem-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-nwchem-xml", ObjectType.XML, "log.xml");
	private static final String PREFIX = "prefix";

	public NWChemModule() {
        super();
    }
	
    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new NWChemLog2CompchemConverter());
		converterList.add(new NWChemLog2XMLConverter());
		return converterList;
	}
	
	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
			mimeTypeList.add(INPUT_TYPE);
		}
		return mimeTypeList;
		
	}

}
