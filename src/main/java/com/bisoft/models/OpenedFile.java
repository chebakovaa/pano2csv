package com.bisoft.models;

import com.bisoft.exeptions.GetTitleObjectException;
import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IOpenedFile;
import com.bisoft.navi.common.interfaces.ISavedFormat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

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
        Iterator<List<String>> data = object.body().iterator();
        while (data.hasNext()) {
            format.save(stream,(Iterable<String>) data.next());
        }
    }
}
