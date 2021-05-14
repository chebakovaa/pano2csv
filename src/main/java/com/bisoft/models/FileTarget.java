package com.bisoft.models;

import com.bisoft.interfaces.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public class FileTarget implements ISaveTarget {
	private final IClearedFolder location;
	private final ISavedFormat format;
	private OutputStreamWriter out;
	
	public FileTarget(IClearedFolder location, ISavedFormat format) {
		this.location = location;
		this.format = format;
	}
	
	@Override
	public ICustomFormat savedFormat(IModelObject object) throws FileNotFoundException, UnsupportedEncodingException {
		out = new OutputStreamWriter(new FileOutputStream(Path.of(location.toString(), object.name()).toFile()), "UTF8");
		return null;
	}
	
//	@Override
//	public void close() throws Exception {
//		if(out != null) {
//			out.close();
//		}
//	}
}
