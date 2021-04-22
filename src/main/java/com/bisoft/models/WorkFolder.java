package com.bisoft.models;

import java.io.File;

public class WorkFolder {
	
	private final File folder;
	
	public WorkFolder(File folder) {
		this.folder = folder;
	}
	
	public void prepare() throws Exception {
		File[] files = folder.listFiles();
		for(File fl:files){
			if(!fl.delete()) {throw new Exception("Not all file were deleted!");};
		}
	}
	
}
