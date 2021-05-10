package com.bisoft.models;

import com.bisoft.interfaces.IClearedFolder;

import java.io.File;

public class ClearedFolder implements IClearedFolder {
    private final File folder;

    public ClearedFolder(File folder) {
        this.folder = folder;
    }
}
