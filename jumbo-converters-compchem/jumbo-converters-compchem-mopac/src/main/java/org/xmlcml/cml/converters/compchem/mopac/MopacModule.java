package org.xmlcml.cml.converters.compchem.mopac;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.mopac.auxx.MopacAux2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class MopacModule extends AbstractConverterModule {

	public static final MimeType AUX_TYPE = new MimeType("chemical/x-mopac-aux", ObjectType.TEXT, "aux");
	public static final MimeType AUX_XML_TYPE = new MimeType("chemical/x-mopac-aux-xml", ObjectType.XML, "aux.xml");
	private static final String PREFIX = "mopac";

	public MopacModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new MopacAux2XMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(AUX_TYPE);
			mimeTypeList.add(AUX_XML_TYPE);
		}
		return mimeTypeList;
	}
	
}
