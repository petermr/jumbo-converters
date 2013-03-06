package org.xmlcml.cml.converters.compchem.nwchem.log;

import java.io.File;
import java.io.IOException;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.nwchem.NWChemModule;

public class NWChemLog2CompchemConverter extends AbstractConverter {

    private static final Logger LOG = Logger.getLogger(NWChemLog2CompchemConverter.class);

    private static final String DEFAULT_TEMPLATE_RESOURCE =
            "org/xmlcml/cml/converters/compchem/NWChem/log/templates/nwchem2compchem.xml";

    private static final String BASE_URI = "classpath:/"+DEFAULT_TEMPLATE_RESOURCE;

	private NWChemLog2XMLConverter logConverter = null;
	private NWChemLogXML2CompchemConverter xmlConverter = null;


    public NWChemLog2CompchemConverter() {
    }       

    private void ensureLogConverter() {
    	if (logConverter == null) {
    		logConverter = NWChemLog2XMLConverter.createDefaultConverter();
    	}
    }
    
    private void ensureXmlConverter() {
    	if (xmlConverter == null) {
    		xmlConverter = NWChemLogXML2CompchemConverter.createDefaultConverter();
    	}
    }
    
    public void convert(File in, File out) {
    	ensureLogConverter();
    	Element xmlElement = logConverter.convertToXML(in);
    	ensureXmlConverter();
    	xmlConverter.convert(xmlElement, out);
    }

 

    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
             } 
        else if (args.length == 2) {
        	NWChemLog2CompchemConverter converter = new NWChemLog2CompchemConverter();
			converter.convert(new File(args[0]), new File(args[1]));
        } else {
            AbstractConverter converter = new NWChemLog2CompchemConverter();
//            convertFile(converter, "fukuilite");
            convertFile(converter, "bench_opt");
//			for (int i = 1; i < 4; i++) {
////		convertFile(converter, "anna"+i);
//			}
        }
    }

	private static void convertFile(AbstractConverter converter, String fileRoot) {
		File out;
		File in = null;
		try {
			in = new File("src/test/resources/compchem/nwchem/log/in/"+fileRoot+".out");
			System.out.println("converting: "+in);
			out = new File("test/"+fileRoot+".compchem.cml");
			converter.convert(in, out);
		} catch (Exception e) {
			System.err.println("Cannot read/convert "+in+"; "+e);
		}
	}
	
	public MimeType getInputType() {
		return NWChemModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "Convert NWChem log files to compchem";
	}

	private static void usage() {
		System.out.println("usage: NWChemLog2CompchemConverter <file.nw> <file.xml>");
	}
	
}
