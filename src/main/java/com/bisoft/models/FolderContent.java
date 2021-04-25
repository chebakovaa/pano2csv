package com.bisoft.models;

import java.io.File;

public class FolderContent {
	
	private final File folder;
	
	public FolderContent(File folder) {
		this.folder = folder;
	}
	
	public int prepare() throws Exception {
		File[] files = folder.listFiles();
		for(File fl:files){
			if(!fl.delete()) {throw new Exception("Not all files were deleted!");};
		}
		return folder.listFiles().length;
	}
	
}
