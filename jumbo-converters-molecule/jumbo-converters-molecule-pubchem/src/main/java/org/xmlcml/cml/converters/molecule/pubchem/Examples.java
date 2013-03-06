package org.xmlcml.cml.converters.molecule.pubchem;

import java.io.File;

import org.xmlcml.cml.converters.Converter;

public class Examples {

	public void pubchem2cml() {
		Converter converter = new PubchemXML2CMLConverter();
		converter.convert(
				new File("src/test/resources/org/xmlcml/cml/converters/molecule/pubchem/in/CID_679.xml"),
				new File("target/pubchem/out/CID_679.cml"));
	}
	
	public void pubchem2cmlAll() {
		Converter converter = new PubchemXML2CMLConverter();
		File[] files = new File("src/test/resources/org/xmlcml/cml/converters/molecule/pubchem/in").listFiles();
		for (File file : files) {
			if (file.getPath().endsWith(".xml")) {
				System.out.println("file "+file);
				converter.convert(
						file,
						new File("target/pubchem/out/"+file.getPath()));
			}
		}
	}
	
	
	public static void main(String[] args) {
		new Examples().pubchem2cml();
	}
}
