package com.bisoft.interfaces;

import java.io.IOException;
import java.util.List;

public interface ISavedFormat extends AutoCloseable {
	void save(Iterable<String> row) throws IOException;
}
