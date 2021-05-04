package com.bisoft.interfaces;

import java.io.IOException;
import java.util.List;

public interface ISavedFormat {
	void save(Iterable<String> row) throws IOException;
}
