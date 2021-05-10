package com.bisoft.interfaces;

import com.bisoft.helpers.ClearFolderContentExeption;

public interface IFolderContent {
    IClearedFolder clearedFolder() throws ClearFolderContentExeption;
}
