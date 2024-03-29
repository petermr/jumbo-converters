package org.xmlcml.cml.converters.compchem.gaussian.log;

import java.io.File;
import java.io.IOException;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.gaussian.GaussianModule;

public class GaussianLog2CompchemConverter extends AbstractConverter {

    private static final Logger LOG = Logger.getLogger(GaussianLog2CompchemConverter.class);

    private static final String DEFAULT_TEMPLATE_RESOURCE =
            "org/xmlcml/cml/converters/compchem/gaussian/log/templates/gaussian2compchem.xml";

    private static final String BASE_URI = "classpath:/"+DEFAULT_TEMPLATE_RESOURCE;

	private GaussianLog2XMLConverter logConverter;
	private GaussianLogXML2CompchemConverter xmlConverter;

    public GaussianLog2CompchemConverter() {
    }

    /** signature to distinguish from no-args constructor
     * 
     * @param dummy
     */
    private GaussianLog2CompchemConverter(int dummy) {
    	logConverter = GaussianLog2XMLConverter.createDefaultConverter();
    	xmlConverter = GaussianLogXML2CompchemConverter.createDefaultConverter();
    }

    public static GaussianLog2CompchemConverter createDefaultConverter() {
    	return new GaussianLog2CompchemConverter(999);
    }

    private void ensureLogConverter() {
    	if (logConverter == null) {
    		logConverter = GaussianLog2XMLConverter.createDefaultConverter();
    	}
    }
    
    private void ensureXmlConverter() {
    	if (xmlConverter == null) {
    		xmlConverter = GaussianLogXML2CompchemConverter.createDefaultConverter();
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
            GaussianLog2CompchemConverter converter = new GaussianLog2CompchemConverter();
//            converter.runTests(args[0]);
        } else if (args.length == 2) {
        } else {
            AbstractConverter converter = new GaussianLog2CompchemConverter();
//            convertFile(converter, "jobTest");
//            convertFile(converter, "anna0");
			for (int i = 1; i <= 4; i++) {
				convertFile(converter, "anna"+i);
			}
        }
    }

	private static void convertFile(AbstractConverter converter, String fileRoot) {
		File out;
		File in = null;
		try {
			in = new File("src/test/resources/compchem/gaussian/log/in/"+fileRoot+".log");
			System.out.println("converting: "+in);
			out = new File("test/"+fileRoot+".compchem.xml");
			if (in.exists()) {
				converter.convert(in, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot read/convert "+in+"; "+e);
		}
	}

	public MimeType getInputType() {
		return GaussianModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}

	public String getDescription() {
		return "Convert Gaussian Log to CML-compchem";
	}

}
