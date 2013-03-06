package org.xmlcml.cml.converters.compchem.castep;

import java.util.ArrayList;

import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.castep.log.CastepLog2XMLConverter;
import org.xmlcml.cml.converters.compchem.castep.mol.CastepMol2CMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author pm286
 */
public class CastepModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-castep-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-castep-log-xml", ObjectType.XML, "log.xml");
	public static final MimeType MOL_TYPE = new MimeType("chemical/x-castep-mol", ObjectType.TEXT, "mol");
	private static final String PREFIX = "castep";
	
    public CastepModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new CastepLog2XMLConverter());
		converterList.add(new CastepMol2CMLConverter());
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
	
}
