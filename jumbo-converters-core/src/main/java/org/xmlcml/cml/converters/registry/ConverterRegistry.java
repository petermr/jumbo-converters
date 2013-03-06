package org.xmlcml.cml.converters.registry;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;

/**
 * @author Sam Adams
 */
public class ConverterRegistry {

    public static final String CONVERTER_FILE = "META-INF/jumbo-converters";

    /** create singleton registry
     */
    private static ConverterRegistry CONVERTER_REGISTRY = new ConverterRegistry();
    
    private Map<TypePair, List<Converter>> converterMap = 
    		new LinkedHashMap<TypePair, List<Converter>>();
    private List<Converter> converterList = new ArrayList<Converter>();
    private Set<MimeType> typeSet = new HashSet<MimeType>();
    private Map<String, Set<MimeType>> typesBySuffixMap = new HashMap<String, Set<MimeType>>();

    public static synchronized ConverterRegistry getDefaultConverterRegistry() {
    	if (CONVERTER_REGISTRY == null) {
    		CONVERTER_REGISTRY = new ConverterRegistry();
    	}
    	return CONVERTER_REGISTRY;
    }
    private ConverterRegistry() {
        createConvertersList();
        registerConverters();
    }

	private void createConvertersList() {
        converterList = new ArrayList<Converter>();
		try {
        	ClassLoader ldr = ConverterRegistry.class.getClassLoader();

            Enumeration<URL> e = ldr.getResources(CONVERTER_FILE);
            for (URL url : Collections.list(e)) {
                InputStream is = url.openStream();
                try {
                    for (String line : IOUtils.readLines(is)) {
                        line = stripComments(line);
                        String convertersName = line.trim();
                        if (convertersName.length() > 0) {
                            try {
                            	System.out.println("Name: "+convertersName);
                                Class<?> clazz = Class.forName(convertersName);
                                AbstractConverterModule converterAndTypeLists = (AbstractConverterModule) clazz.newInstance();
                                converterList.addAll(converterAndTypeLists.getConverterList());
                            } catch (Exception ex) {
                                System.err.println("Error loading converter: "+ex);
                                ex.printStackTrace();
                            }
                        }
                    }
                } finally {
                    IOUtils.closeQuietly(is);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading converter files");
            e.printStackTrace();
        }
	}
	
	private void registerConverters() {
        for (Converter converter : converterList) {
        	register(converter);
    		register(converter.getInputType());
    		register(converter.getOutputType());
        }
	}

	private String stripComments(String line) {
		int comment = line.indexOf('#');
		if (comment >= 0) {
		    line = line.substring(0, comment);
		}
		return line.trim();
	}
    
    public Map<TypePair, List<Converter>> getMap() {
    	return converterMap;
    }


    public List<Converter> findConverters(String intype, String outtype) {
        TypePair t = new TypePair(intype, outtype);
        List<Converter> converterList = converterMap.get(t);
        return converterList;
    }

    public Converter findSingleConverter(String intype, String outtype) {
    	List<Converter> converterList = findConverters(intype, outtype);
    	return (converterList == null || converterList.size() != 1) ? null : converterList.get(0);
    }

    public List<Converter> findConverters(MimeType intype, MimeType outtype) {
    	return findConverters(intype.toString(), outtype.toString());
    }


    public List<Converter> getConverterList() {
        return converterList;
    }

    private synchronized void register(Converter converter) {
        MimeType intype = converter.getInputType();
        MimeType outtype = converter.getOutputType();
        if (intype != null && outtype != null) {
	        TypePair t = new TypePair(intype.toString(), outtype.toString());
	        List<Converter> list = converterMap.get(t);
	        if (list == null) {
	        	list = new ArrayList<Converter>();
	        	converterMap.put(t,  list);
	        }
	        list.add(converter);
        } else {
        	System.out.println("NULL types for "+converter.getClass()+" ("+intype+", "+outtype+")");
        }
    }

    private synchronized void register(MimeType type) {
    	typeSet.add(type);
    	List<String> extensions = type.getExtensions();
    	for (String extension : extensions) {
    		Set<MimeType> types = typesBySuffixMap.get(extension);
    		if (types == null) {
    			types = new HashSet<MimeType>();
    			typesBySuffixMap.put(extension, types);
    		}
//    		System.out.println("TYPE "+types);
    		types.add(type);
    	}
    }
    
	public Set<MimeType> getTypes(String suffix) {
		return typesBySuffixMap.get(suffix);
	}
    
	public MimeType getSingleTypeFromSuffix(String suffix) {
		Set<MimeType> types = typesBySuffixMap.get(suffix);
		return (types != null && types.size() == 1) ? (MimeType) types.toArray()[0] : null;
	}
	
	public MimeType getSingleTypeFromFilename(String filename) {
		int idx = filename.lastIndexOf(".");
		return (idx == -1) ? null : getSingleTypeFromSuffix(filename.substring(idx+1));
	}
	
	public String getSingleMimeTypeFromFilename(String filename) {
		MimeType type = getSingleTypeFromFilename(filename);
		return (type == null) ? null : type.getMimeType();
	}

	public void add(Converter converter) {
		converterList.add(converter);
	}
    
}
