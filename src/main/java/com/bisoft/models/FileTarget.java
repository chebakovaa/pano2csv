package com.bisoft.models;

import com.bisoft.interfaces.IClearedFolder;
import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISaveTarget;
import com.bisoft.interfaces.ISavedFormat;

public class FileTarget implements ISaveTarget {
	public FileTarget(IClearedFolder location, IFileFormat format) {
	}
	
	@Override
	public ISavedFormat savedFormat() {
		return null;
	}
	
	@Override
	public void close() throws Exception {
	
	}
}
