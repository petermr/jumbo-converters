package org.xmlcml.cml.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nu.xom.Element;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MimeType {

	private static final Logger LOG = Logger.getLogger(MimeType.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	public static final MimeType NULL = new MimeType("null", ObjectType.NULL, "null");
	public static final MimeType DIRECTORY = new MimeType("directory", ObjectType.DIRECTORY, "directory");
	public static final MimeType INDEX = new MimeType("index", ObjectType.INDEX, "index");
	public static final MimeType LIST = new MimeType("list", ObjectType.LIST, "list");
	
	public static final MimeType CDX = new MimeType("chemical/x-cdx", ObjectType.BYTES,	"cdx");
	public static final MimeType CDXML = new MimeType("chemical/x-cdxml", ObjectType.XML,	"cdxml", "cdx.xml");
	public static final MimeType CIF = new MimeType("chemical/x-cif", ObjectType.TEXT,	"cif");
	public static final MimeType CML = new MimeType("chemical/x-cml", ObjectType.XML,			"cml", "cml.xml");
	public static final MimeType CSV = new MimeType("text/csv", ObjectType.TEXT,	"csv");
	public static final MimeType DALTON = new MimeType("chemical/x-dalton", ObjectType.TEXT,	".out");
	public static final MimeType FOO = new MimeType("chemical/x-foo", ObjectType.TEXT,	"foo");
	public static final MimeType FOO_XML = new MimeType("chemical/x-foo-xml", ObjectType.XML,	"foo.xml");
	public static final MimeType GAMESSUK_PUNCH = new MimeType("chemical/x-gamessuk-punch", ObjectType.TEXT,	"gamessuk.punch");
	public static final MimeType GAMESSUK_PUNCH_XML = new MimeType("chemical/x-gamessuk-punch-xml", ObjectType.XML,	"gamessuk.punch.xml");
	public static final MimeType GAMESSUS_INPUT = new MimeType("chemical/x-gamessus-input", ObjectType.TEXT,	"gamessus.input");
	public static final MimeType GAMESSUS_INPUT_XML = new MimeType("chemical/x-gamessus-input-xml", ObjectType.XML,	"gamessus.input.xml");
	public static final MimeType GAMESSUS_PUNCH = new MimeType("chemical/x-gamessus-punch", ObjectType.TEXT,	"gamessus.punch");
	public static final MimeType GAMESSUS_PUNCH_XML = new MimeType("chemical/x-gamessus-punch-xml", ObjectType.XML,	"gamessus.punch.xml");
	public static final MimeType GAU_ARC = new MimeType("chemical/x-gaussian-archive", ObjectType.TEXT,			"gau", "gau.arc");
	public static final MimeType GAU_IN = new MimeType("chemical/x-gaussian-input", ObjectType.TEXT,			"gau.in");
	public static final MimeType GAU_LOG = new MimeType("chemical/x-gaussian-log", ObjectType.TEXT,			"gau.log", "g03");
	public static final MimeType HTML = new MimeType("text/html", ObjectType.TEXT,			"html", "htm");		
	public static final MimeType IMAGE = new MimeType("image", ObjectType.BYTES,					"img");
	public static final MimeType JDX = new MimeType("chemical/x-jcamp-dx", ObjectType.TEXT,			"jdx");
	public static final MimeType MDL = new MimeType("chemical/x-mdl-molfile",			ObjectType.TEXT, "mol");
	public static final MimeType N3 = new MimeType("text/n3; charset=utf-8", ObjectType.BYTES, "n3");
	public static final MimeType NAME = new MimeType("text/text",			ObjectType.TEXT, "name");
	public static final MimeType NWCHEM = new MimeType("chemical/x-nwchem", ObjectType.TEXT,	"nwchem");
	public static final MimeType NWCHEM_XML = new MimeType("chemical/x-nwchem-xml", ObjectType.XML,	"nwchem.xml");
	public static final MimeType OWL = new MimeType("application/rdf+xml",			ObjectType.XML, "owl");
	public static final MimeType PDF = new MimeType("image/pdf",			ObjectType.BYTES, "pdf");
	public static final MimeType PNG = new MimeType("image/png",			ObjectType.BYTES, "png");
	public static final MimeType RDFXML = new MimeType("chemical/x-rdfxml", ObjectType.XML,			"rdf.xml", "rdf");
	public static final MimeType RPT = new MimeType("misc/rpt", ObjectType.TEXT, "rpt");
	public static final MimeType SDF = new MimeType("chemical/x-mdl-sdffile",			ObjectType.TEXT, "sdf");
	public static final MimeType SVG = new MimeType("image/svg+xml", ObjectType.XML, "svg");
	public static final MimeType SVGBYTES = new MimeType("image/svg+xml", ObjectType.BYTES, "svgbytes");
	public static final MimeType TXT = new MimeType("text/txt", ObjectType.TEXT, "txt");
	public static final MimeType XHTML = new MimeType("text/xhtml", ObjectType.XML,			"xhtml");
	public static final MimeType XML = new MimeType("application/xml", ObjectType.XML, "xml");
	public static final MimeType XYZ = new MimeType("chemical/x-xyz", ObjectType.TEXT, "xyz");
	
	private static Set<MimeType> typeList = new HashSet<MimeType>();
	private static Map<String, Set<MimeType>> extensionMap = 
		new HashMap<String, Set<MimeType>>();
	private static Map<String, MimeType> typeMap = 
		new HashMap<String, MimeType>();
	static {
		typeList.add(NULL);
		typeList.add(DIRECTORY);
		typeList.add(INDEX);
		typeList.add(LIST);  
		
		typeList.add(CDX);
		typeList.add(CDXML);
		typeList.add(CIF);
		typeList.add(CML);
		typeList.add(CSV);
		typeList.add(DALTON);
		typeList.add(FOO);
		typeList.add(FOO_XML);
		typeList.add(GAMESSUK_PUNCH);
		typeList.add(GAMESSUK_PUNCH_XML);
		typeList.add(GAMESSUS_PUNCH);
		typeList.add(GAMESSUS_PUNCH_XML);
		typeList.add(GAU_ARC);
		typeList.add(GAU_IN);
		typeList.add(GAU_LOG);
		typeList.add(HTML);
		typeList.add(JDX);
		typeList.add(MDL);
		typeList.add(N3);
		typeList.add(NAME);
		typeList.add(NWCHEM);
		typeList.add(NWCHEM_XML);
		typeList.add(OWL);
		typeList.add(PDF);
		typeList.add(PNG);
		typeList.add(RDFXML);
		typeList.add(RPT);
		typeList.add(SDF);
		typeList.add(SVG);
		typeList.add(SVGBYTES);
		typeList.add(TXT);
		typeList.add(XHTML);
		typeList.add(XML);
		typeList.add(XYZ);
		
		for (MimeType type : typeList) {
			for (String extension : type.getExtensions()) {
				Set<MimeType> types = extensionMap.get(extension);
				if (types == null) {
					types = new HashSet<MimeType>();
					extensionMap.put(extension, types);
				}
				types.add(type);
			}
			String t = type.getDefaultExtension().toUpperCase();
			typeMap.put(t, type);
		}
		
	};
	
	public static MimeType getType(String t) {
		return (t == null) ? null : typeMap.get(t.toUpperCase());
	}

	/**
	 * ----------possible other ones with chemical mime-------------
	 * chemical/x-cdx cdx ChemDraw eXchange file example.cdx
	 * http://www.camsoft.com/plugins/ chemical/x-chemdraw chm ChemDraw file
	 * example.chm http://www.camsoft.com/plugins/ chemical/x-cif cif
	 * Crystallographic Interchange Format example.cif example_cif
	 * http://www.bernstein-plus-sons.com/software/rasmol/ chemical/x-chem3d c3d
	 * Chem3D Format example.c3d CambridgeSoft chemical/x-cml cml Chemical
	 * Markup Language example.cml http://cml.sourceforge.net/
	 * chemical/x-daylight-smiles smi Smiles Format example.smi example_smi
	 * http://www.daylight/dayhtml/smiles/index.html chemical/x-gamess-input
	 * inp, gam GAMESS Input format example.inp example_inp
	 * http://www.msg.ameslab.gov/GAMESS/Graphics/MacMolPlt.shtml
	 * chemical/x-gaussian-input gau Gaussian Input format example.gau
	 * example_gau http://www.mdli.com/ chemical/x-gaussian-checkpoint fch,fchk
	 * Gaussian Checkpoint format example.fch http://products.camsoft.com/
	 * chemical/x-gaussian-cube cub Gaussian Cube (Wavefunction) format
	 * example.cub http://www.mdli.com/ chemical/x-gcg8-sequence gcg example.gcg
	 * example_gcg chemical/x-jcamp-dx jdx, dx JCAMP Spectroscopic Data Exchange
	 * Format example.dx example_dx http://www.mdli.com/ chemical/x-mdl-molfile
	 * mol MDL Molfile example.mol example_mol http://www.mdli.com/
	 * chemical/x-mdl-rdfile rd Reaction-data file example.rd example_rd
	 * http://www.mdli.com/ chemical/x-mdl-rxnfile rxn MDL Reaction format
	 * example.rxn example_rxn http://www.mdli.com/ chemical/x-mdl-sdfile sd MDL
	 * Structure-data file example.sd example_sd http://www.mdli.com/
	 * chemical/x-mol2 mol2 Portable representation of a SYBYL molecule
	 * example.mol2 example_mol2
	 * http://www.tripos.com/TechBriefs/mol2_format/mol2.html chemical/x-pdb pdb
	 * Protein DataBank example.pdb example_pdb http://www.mdli.com/
	 * chemical/x-xyz xyz Co-ordinate Animation format example.xyz example_xyz
	 * http://www.mdli.com/
	 * 
	 * 
	 */
	private List<String> extensions = new ArrayList<String>();
	private String mimeType;
	private ObjectType objectType;

	/**
	 * Enumeration of object types that are passed between converters.
	 * 
	 * TODO might need to add byte array.
	 * @author jimdowning
	 * 
	 */
	public static enum ObjectType {
		NULL, DIRECTORY, INDEX, LIST, TEXT, XML, BYTES;
	}

	@SuppressWarnings("unused")
	private MimeType() {
		;
	}

	public MimeType(String mimeType, ObjectType objectType, String... extensions) {
		setArguments(mimeType, objectType, extensions);
	}

	private void setArguments(String mimeType, ObjectType objectType,
			String... extensions) {
		if (mimeType == null) {
			throw new IllegalArgumentException("MIME type may not be null");
		}
		if (objectType == null) {
			throw new IllegalArgumentException("ObjectType may not be null");
		}
		if (extensions == null) {
			throw new IllegalArgumentException("extensions may not be null");
		}
		if (extensions.length == 0) {
			throw new IllegalArgumentException(
					"must supply at least one extension");
		}
		for (String s : extensions) {
			if (s == null) {
				throw new IllegalArgumentException(
						"Exception array should not contain null");
			}
		}
		this.mimeType = mimeType;
		this.objectType = objectType;
		this.extensions = Arrays.asList(extensions);
	}

	public MimeType(String mimeType, ObjectType objectType, String extension) {
		this(mimeType, objectType, new String[] { extension });
	}

	public MimeType(Element typeElement) {
	}

	public List<String> getExtensions() {
		return Collections.unmodifiableList(extensions);
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getDefaultExtension() {
		return !extensions.isEmpty() ? extensions.get(0) : "";
	}

	public int hashCode() {
		int result = 19;
		result = 13 * result + extensions.hashCode();
		result = 13 * result + mimeType.hashCode();
		result = 13 * result + objectType.hashCode();
		return result;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof MimeType)) {
			return false;
		}
		MimeType t = (MimeType) o;
		if (!extensions.equals(t.extensions)) {	
			return false;
		}
		if (!mimeType.equals(t.mimeType)) {
			return false;
		}
		if (objectType != t.objectType) {
			return false;
		}
		return true;
	}

	public String toString() {
		return mimeType;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
	
	/** gets suffix filter oring all extensions.
	 * requires fileFileFilter and non-hidden files
	 * @return filter passing all suffixFilters
	 */
	@SuppressWarnings("deprecation")
	public IOFileFilter getSuffixFileFilter() {
		IOFileFilter fileFilter =  null;
		if (extensions.size() > 0) {
			fileFilter =
		        FileFilterUtils.andFileFilter(
		        		FileFilterUtils.fileFileFilter(),
	                    HiddenFileFilter.VISIBLE);
			IOFileFilter suffixFilter = FileFilterUtils.suffixFileFilter(extensions.get(0));
			for (int i = 1; i < extensions.size(); i++) {
				suffixFilter = FileFilterUtils.andFileFilter(
						suffixFilter,
						FileFilterUtils.suffixFileFilter(extensions.get(i))
						);			
			}
		}
		return fileFilter;
	}

	/** creates unique lookup string for foo2bar conversion.
	 * 
	 * @param foo
	 * @param bar
	 * @return string
	 */
	public static String getFoo2BarString(MimeType foo, MimeType bar) {
		return foo.getMimeType()+"=>"+((bar == null) ? "null" : bar.getMimeType());
	}

	/** gets Type for extension.
	 * 
	 * @param extension
	 * @return null if not found
	 * @throws RuntimeException if more than one match
	 */
	public static MimeType getTypeForExtension(String extension) {
		MimeType type = null;
		Set<MimeType> types = extensionMap.get(extension);
		if (types == null) {
			// none
		} else if (types.size() > 1) {
			throw new RuntimeException("Too many types for extension: "+extension);
		} else {
			type = types.iterator().next();
		}
		return type;
	}

	public static String getTypeForFilename(String filename) {
		String extension = (filename == null) ? null : org.xmlcml.euclid.Util.getSuffix(filename);
		MimeType type = (extension == null) ? null : getTypeForExtension(extension);
		return (type == null) ? null : type.getDefaultExtension();
	}
	
}
