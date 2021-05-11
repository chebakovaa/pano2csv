package com.bisoft.interfaces;

import com.bisoft.exeptions.ClearFolderContentException;

public interface IFolderContent {
    IClearedFolder clearedFolder() throws ClearFolderContentException;
}
