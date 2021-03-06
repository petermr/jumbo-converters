package org.xmlcml.cml.converters.compchem.nwchem.log;

import java.io.File;
import java.io.IOException;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.nwchem.NWChemModule;
import org.xmlcml.cml.converters.util.ConverterUtils;

public class NWChemLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	private static Logger LOG = Logger.getLogger(NWChemLog2XMLConverter.class);
	private static final String BASE_URI = "classpath:/org/xmlcml/cml/converters/compchem/nwchem/log/templates/topTemplate.xml";
	
	public NWChemLog2XMLConverter() {
	}
	
	public static NWChemLog2XMLConverter createDefaultConverter() {
		return  new NWChemLog2XMLConverter(BASE_URI, "templates/topTemplate.xml");
	}
	
	public NWChemLog2XMLConverter(String baseUri, String templateName) {
		this(ConverterUtils.buildElementIncludingBaseUri(baseUri, templateName, NWChemLog2XMLConverter.class));
	}
	public NWChemLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	private void runNWTests(String dirName) {
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		if (files == null) {
			throw new RuntimeException("No files found in "+dir.getAbsolutePath());
		}
		LOG.info("Processing "+files.length+" files");
		for (File file : files) {
			if (file.getAbsolutePath().endsWith(".out")) {
				File out = new File(file.getAbsolutePath()+".cml");
//				if (!out.exists()) {
					System.out.println("converting "+file+" to "+out);
					this.convert(file, out);
//				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
             } 
        else if (args.length == 2) {
        } else {
            AbstractConverter converter = NWChemLog2XMLConverter.createDefaultConverter();
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
			out = new File("test/"+fileRoot+".raw.xml");
			converter.convert(in, out);
		} catch (Exception e) {
			System.err.println("Cannot read/convert "+in+"; "+e);
		}
	}
	
	public MimeType getInputType() {
		return NWChemModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return NWChemModule.LOG_XML_TYPE;
	}
	
	public String getDescription() {
		return "Convert NWChem log files to compchem";
	}

}
