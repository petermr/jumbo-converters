package org.xmlcml.cml.converters.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.molecule.cml.CMLTransformMolecule;
import org.xmlcml.cml.converters.registry.ConverterRegistry;
import org.xmlcml.euclid.Util;

/**
 * @author Sam Adams
 */
public class ConverterCli {

	private static Logger LOG = Logger.getLogger(Converter.class);
	
	private int argCount = 0;
	private String converterName = null;
	private String inputType = null;
	private String inputName = null;
	private String inputUrl = null;
	private String outputType = null;
	private String outputName = null;
	private String startDirName = null;
	private String xpath = null;
	private List<String> editList = new ArrayList<String>();
	private Converter converter = null;
	private List<File> infiles;
	private File infile;
	private InputStream inStream;
	private File outfile;

	
	public ConverterCli() {
	}
	
	private void processArgs(String[] args) {
		
		
		parseArgs(args);
		getInputAndOutputTypes();
		try {
			getInputAndOutputs();
		} catch (Exception e) {
			throw new RuntimeException("cannot open inputs", e);
		}
		getConverter(args);
		convert(args);
	}

	private void getInputAndOutputs() throws Exception {
		if (startDirName != null) {
			Globber globber = new Globber(inputName, startDirName);
			infiles = globber.walkTree();
		} else if (inputUrl != null) {
			inStream = new URL(inputUrl).openStream();
			outfile = new File(outputName);
		} else {
			infile = createInfile(inputName);
			inStream = new FileInputStream(infile);
			outfile = new File(outputName);
		}
	}

	private void getConverter(String[] args) {
		if (converterName != null) {
			try {
				converter = (Converter) Class.forName(converterName).newInstance();
			} catch (Exception e) {
				throw new RuntimeException("Cannot create converter: "+converterName, e);
			}
		} else if (inputType.equals(MimeType.CML.getMimeType()) && outputType.equals(MimeType.CML.getMimeType())) {
			if (editList.size() > 0) {
				processEditList(infile, outfile);
			}
		} else {
			converter = ConverterRegistry.getDefaultConverterRegistry().findSingleConverter(inputType, outputType);
		}
		if (converter == null) {
			printConverters(args, inputType, outputType);
			throw new RuntimeException("Cannot find converter");
		}

	}

	private void convert(String[] args) {
		if (!(converter instanceof CMLTransformMolecule)) {
			if (inStream != null) {
				converter.convert(inStream, outfile);
			} else if (infiles != null) {
				int idx = outputName.indexOf(".");
				String outputName0 = outputName.substring(idx); // make suffix
				for (File infile0 : infiles) {
					File outfile0 = new File(infile0.getAbsolutePath().toString()+Util.S_PERIOD+outputName0);
					converter.convert(infile0, outfile0);
				}
			}
		}
	}

	private void parseArgs(String[] args) {
		argCount = 0;
		while (true) {
			if (argCount >= args.length) {
				break;
			} else if ("-c".equals(args[argCount])) {
				converterName = args[++argCount]; argCount++;
			} else if ("-d".equals(args[argCount])) {
				startDirName = args[++argCount]; argCount++;
			} else if ("-e".equals(args[argCount])) {
				createEditList(args);
			} else if ("-i".equals(args[argCount])) {
				inputName = args[++argCount]; argCount++;
			} else if ("-it".equals(args[argCount])) {
				inputType = args[++argCount]; argCount++;
			} else if ("-o".equals(args[argCount])) {
				outputName = args[++argCount]; argCount++;
			} else if ("-ot".equals(args[argCount])) {
				outputType = args[++argCount]; argCount++;
			} else if ("-u".equals(args[argCount])) {
				inputUrl = args[++argCount]; argCount++;
			} else {
				System.err.println("Cannot parse: "+args[argCount++]);
			}
		}
	}

	private void createEditList(String[] args) {
		argCount++;
		while (argCount < args.length) {
			String arg = args[argCount++];
			if (arg.startsWith(Util.S_MINUS)) {
				break;
			}
			editList.add(arg);
		}
	}

	private void processEditList(File infile, File outfile) {
		Element element = CMLUtil.parseQuietlyIntoCML(infile);
		if (element == null) {
			throw new RuntimeException("Cannot find/read/parse: "+infile);
		}
		if (xpath == null) {
			xpath = "//cml:molecule";
		}
		CMLTransformMolecule transformer = new CMLTransformMolecule();
		for (String command : editList) {
			transformer.transformMolecule(command, element, xpath);
		}
		try {
			CMLUtil.debug(element, new FileOutputStream(outfile), 0);
		} catch (Exception e) {
			throw new RuntimeException("Cannot find/write output: "+outfile, e);
		}
		converter = transformer;
	}

	private void getInputAndOutputTypes() {
		if (inputType == null) {
			if (inputName != null) {
				inputType = ConverterRegistry.getDefaultConverterRegistry().getSingleMimeTypeFromFilename(inputName);
			}
		}
		if (outputType == null) {
			outputType = ConverterRegistry.getDefaultConverterRegistry().getSingleMimeTypeFromFilename(outputName);
		}
		if (inputType == null || outputType == null) {
			throw new RuntimeException("Cannot find or deduce I/O types");
		}
		if ((inputName == null && inputUrl == null) || outputName == null) {
			throw new RuntimeException("Cannot find or deduce I/O streams");
		}
	}

	private void printConverters(String[] args, String inputType,
			String outputType) {
		System.err.println("cannot find converter for: "+Util.concatenate(args, " "));
		List<Converter> converterList = ConverterRegistry.getDefaultConverterRegistry().findConverters(inputType, outputType);
		if (converterList.size() > 1) {
			System.out.println("More than one converter");
			for (Converter conv : converterList) {
				System.out.println(">> "+conv+" "+conv.getDescription());
			}
		}
	}

	private File createInfile(String inputName) {
		File infile = new File(inputName);
		try {
			LOG.info("input file: "+infile.getAbsolutePath());
			System.out.println("input file: "+infile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot find file: ", e);
		}
		return infile;
	}

	public static Set<MimeType> getTypesFromFilename(String inputName) {
		Set<MimeType> types = null;
		if (inputName != null) {
			int idx = inputName.lastIndexOf(".");
			if (idx != -1) {
				String suffix = inputName.substring(idx+1);
				types = ConverterRegistry.getDefaultConverterRegistry().getTypes(suffix);
			}
		}
		return types;
	}

	private static void usage() {
		System.out.println("Usage:");
		System.out.println("   -i gau-arc foo.arc -o cml foo.cml");
		System.out.println();
		System.out.println("Available converters:");
		for (Converter info : ConverterRegistry.getDefaultConverterRegistry().getConverterList()) {
		    System.out.println(info.getInputType()+"\t"+info.getOutputType());
		}
	}

    public static void main(String[] args) {
    	ConverterCli cli = new ConverterCli();
        if (args.length == 0) {
            usage();
        } else if (args.length == 1) {
            String[] args1 = args[0].trim().split(Util.S_WHITEREGEX);
            cli.processArgs(args1);
        } else {
            cli.processArgs(args);
        }
    }

}
