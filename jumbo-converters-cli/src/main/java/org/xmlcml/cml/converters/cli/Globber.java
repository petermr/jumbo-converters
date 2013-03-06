package org.xmlcml.cml.converters.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Globber extends SimpleFileVisitor<Path> {
	/**

	 * code that finds files that match the specified glob pattern.
	 * For more information on what constitutes a glob pattern, see
	 * http://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
	 *
	 * The file or directories that match the pattern are printed to
	 * standard out.  The number of matches is also printed.
	 *
	 */

	public static final String GLOB = "glob:"; 
	public static final String REGEX = "regex:"; 
	
    private final PathMatcher matcher;
	private List<File> fileList = new ArrayList<File>();
	private String syntax = "glob:";
	private Path startDir = new File(".").toPath();
	private File currentDir;

    public Globber(String pattern, String startDirName) {
    	this(pattern);
        this.startDir = new File(startDirName).toPath();
    }

    public Globber(String pattern) {
        matcher = FileSystems.getDefault().getPathMatcher(syntax + pattern);
    }

	public List<File> getFileList() {
		return fileList;
	}
	
    // Invoke the pattern matching method on each file.
    @Override
    public FileVisitResult visitFile(Path file,	BasicFileAttributes attrs) {
        find(file);
        return FileVisitResult.CONTINUE;
    }

	private void find(Path path) {
		Path name = path.getFileName();
        if (name != null && matcher.matches(name)) {
        	File fullFile = new File(currentDir, name.toString());
        	fileList.add(fullFile);
        }
	}

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    	currentDir = dir.toFile();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
    	throw new RuntimeException("Globber failed", exc);
    }

    public List<File> walkTree() {
    	try {
    		Files.walkFileTree(startDir, this);
    	} catch (Exception e) {
    		throw new RuntimeException("walk tree fails: ", e);
    	}
        return fileList;
    }

}