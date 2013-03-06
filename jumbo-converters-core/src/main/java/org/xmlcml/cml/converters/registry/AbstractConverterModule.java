package org.xmlcml.cml.converters.registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.attribute.DictRefAttribute;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.element.CMLDictionary;
import org.xmlcml.cml.tools.DictionaryTool;
import org.xmlcml.euclid.Util;



public abstract class AbstractConverterModule {

	private static final Logger LOG = Logger.getLogger(AbstractConverterModule.class);
	
	private static String DICT_XML = "Dict.xml";
	private static String NAMESPACE_ROOT = "http://www.xml-cml.org/dict/";
	
	protected List<Converter> converterList = new ArrayList<Converter>();
	
	protected Set<MimeType> typeSet = null;
	protected List<MimeType> mimeTypeList = new ArrayList<MimeType>();
	ModuleResource moduleResource = null;

	/** by default register modules with ConverterRegistry.CONVERTER_REGISTRY
	 * 
	 */
	public AbstractConverterModule() {
		this(ConverterRegistry.getDefaultConverterRegistry());
	}
	
	private CMLDictionary dictionary;

	public DictionaryTool getDictionaryTool() {
		getDictionary();
		return DictionaryTool.getOrCreateTool(dictionary);
	}
	
	public CMLDictionary getDictionary() {
		if (dictionary == null) {
			String dictionaryResource = getDictionaryResource();
			try {
				InputStream inputStream = org.xmlcml.euclid.Util.getInputStreamFromResource(dictionaryResource);
				CMLElement cmlElement = (CMLElement) CMLUtil.parseQuietlyIntoCML(inputStream);
				Nodes dictionaryNodes = cmlElement.query(".//*[local-name()='dictionary']");
				dictionary = (dictionaryNodes.size() == 1) ?
						(CMLDictionary) dictionaryNodes.get(0) : null;
			} catch (Exception e) {
				throw new RuntimeException("cannot read dictionary: "+dictionaryResource, e);
			}
		}
		return dictionary;
		
	}
	
	protected String getDictionaryResource() {
		return getLocalDictionaryName();
	}

	public void addNamespaceDeclaration(CMLElement cml) {
		cml.addNamespaceDeclaration(this.getPrefix(), this.getNamespace());
	}

	public void addDictRef(CMLElement element, String entryId, boolean checkDictionary) {
		String dictRef = DictRefAttribute.createValue(this.getPrefix(), entryId);
		if (checkDictionary) {
			checkAgainstDictionary(element, dictRef);
		}
		element.setAttribute("dictRef", dictRef);
	}
	
	private void checkAgainstDictionary(CMLElement element, String name) {
		DictionaryTool dictionaryTool = getDictionaryTool();
		String entryId = name.toLowerCase();
		if (dictionaryTool != null) {
			if (!dictionaryTool.isIdInDictionary(entryId)) {
				LOG.warn("entryId "+entryId+" not found in dictionary: "+dictionaryTool);
			}
		}
	}

	public AbstractConverterModule(ConverterRegistry converterRegistry) {
        getMimeTypeList();
        getConverterList();
	    typeSet = new HashSet<MimeType>();
	    addConverterClassesAndTypes(converterRegistry);
    }

	public static String getResourceRoot(Class clazz) {
		String packageRoot = (clazz == null) ? null :
			clazz.getPackage().getName().replaceAll(Util.S_BACKSLASH+Util.S_PERIOD, Util.S_SLASH);
		return packageRoot;
	}
	
	protected String getResourceRoot() {
		return getResourceRoot(this.getClass());
	}
	
	
    public abstract List<MimeType> getMimeTypeList();
	public abstract List<Converter> getConverterList();
	public abstract String getPrefix();
	public String getNamespace() {
		return NAMESPACE_ROOT+getPrefix();
	}

    public String getLocalDictionaryName() {
    	return getResourceRoot()+getPrefix()+DICT_XML;
    }

    public String getDictionaryUrlName() {
    	return null;
    }
    
	protected void addConverterClassesAndTypes(ConverterRegistry converterRegistry) {
		if (converterRegistry == null) {
			throw new RuntimeException("Null converterRegistry");
		}
		List<Converter> converters = getConverterList();
		for (Converter converter : converters) {
			if (converter == null) {
				System.err.println("trying to add null converter");
			} else {
				converterRegistry.add(converter);
				typeSet.add(converter.getInputType());
				typeSet.add(converter.getOutputType());
			}
		}
	}

}
