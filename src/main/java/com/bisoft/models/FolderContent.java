package com.bisoft.models;

import com.bisoft.exeptions.ClearFolderContentException;
import com.bisoft.interfaces.IClearedFolder;
import com.bisoft.interfaces.IFolderContent;

import java.io.File;

public class FolderContent implements IFolderContent {
	
	private final File folder;
	
	public FolderContent(File folder) {
		this.folder = folder;
	}
	
	private void clear() throws ClearFolderContentException {
		
		File[] files = folder.listFiles();
		for(File fl:files){
			fl.delete();
		}
		if(folder.listFiles().length > 0){
			throw new ClearFolderContentException("Not all files were deleted!");
		}
	}

	@Override
	public IClearedFolder clearedFolder() throws ClearFolderContentException {
		clear();
		return new ClearedFolder(folder);
	}

}
