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

	public FileTarget(IClearedFolder location, ISavedFormat format) {
		this.location = location;
		this.format = format;
	}
	
	@Override
	public IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(Path.of(location.toString(), name).toFile()), "UTF8");
		return new OpenedFile(out, format);
	}

}
