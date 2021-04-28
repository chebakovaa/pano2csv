package com.bisoft.models;

import com.bisoft.helpers.ClearFolderContentExeption;
import com.bisoft.interfaces.IFolderContent;

import java.io.File;

public class FolderContent implements IFolderContent {
	
	private final File folder;
	
	public FolderContent(File folder) {
		this.folder = folder;
	}
	
	@Override
	public void clear() throws ClearFolderContentExeption {
		File[] files = folder.listFiles();
		for(File fl:files){
			fl.delete();
		}
		if(folder.listFiles().length > 0){
			throw new ClearFolderContentExeption("Not all files were deleted!");
		}
	}
	
}