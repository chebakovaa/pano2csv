package com.bisoft.models;

import com.bisoft.exeptions.GetTitleObjectException;
import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IOpenedFile;
import com.bisoft.interfaces.ISavedFormat;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class OpenedFile implements IOpenedFile {
    private final OutputStreamWriter stream;
    private final ISavedFormat format;

    public OpenedFile(OutputStreamWriter stream, ISavedFormat format) {
        this.stream = stream;
        this.format = format;
    }

    @Override
    public void close() throws Exception {
		if(stream != null) {
			stream.close();
		}
    }

    @Override
    public void save(IModelObject object) throws IOException, GetTitleObjectException {
        format.save(stream, object.title());
        Iterable data = object.body();
        while (data.iterator().hasNext()) {
            format.save(stream,(Iterable<String>) data.iterator().next());
        }
    }
}
