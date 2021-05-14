package com.bisoft.models;

import com.bisoft.interfaces.IClearedFolder;
import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISaveTarget;
import com.bisoft.interfaces.ISavedFormat;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileTarget implements ISaveTarget {
	private final IClearedFolder location;
	private final IFileFormat format;
	private OutputStreamWriter out;
	
	public FileTarget(IClearedFolder location, IFileFormat format) {
		this.location = location;
		this.format = format;
	}
	
	@Override
	public ISavedFormat savedFormat() {
		String fn = location.
		out = new OutputStreamWriter(new FileOutputStream(file.toString()+".csv"), "UTF8");
		return null;
	}
	
	@Override
	public void close() throws Exception {
		if(out != null) {
			out.close();
		}
	}
}
