package eg.edu.nu.bioinf.ui;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InputFiles {
	public static final String TRIM_SUFFIX = "_trim";
	
	private File[] forwardFiles;
	private File[] reverseFiles;
	private ArrayList<File> trimmedFiles;
	
	private boolean singleEndReads = true;

	public File[] getAllFiles() {
		if(singleEndReads) return forwardFiles;
		else return concatenate(forwardFiles, reverseFiles);
	}

	public File[] getForwardFiles() {
		return forwardFiles;
	}

	public void setForwardFiles(File[] forwardFiles) {
		this.forwardFiles = forwardFiles;
	}

	public File[] getReverseFiles() {
		return reverseFiles;
	}

	public void setReverseFiles(File[] reverseFiles) {
		this.reverseFiles = reverseFiles;
	}

	public boolean isSingleEndReads() {
		return singleEndReads;
	}

	public void setSingleEndReads(boolean singleEndReads) {
		this.singleEndReads = singleEndReads;
	}
	
	
	public <T> T[] concatenate(T[] a, T[] b) {
	    int aLen = a.length;
	    int bLen = b.length;

	    @SuppressWarnings("unchecked")
	    T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);

	    return c;
	}

	public ArrayList<File> getTrimmedFiles() {
		return trimmedFiles;
	}

	public void setTrimmedFiles(ArrayList<File> trimmedFiles) {
		this.trimmedFiles = trimmedFiles;
	}

}
