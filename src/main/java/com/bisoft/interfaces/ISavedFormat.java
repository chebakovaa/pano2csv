package com.bisoft.interfaces;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public interface ISavedFormat {
	void save(OutputStreamWriter out, Iterable<String> row) throws IOException;
}
