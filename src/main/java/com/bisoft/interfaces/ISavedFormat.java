package com.bisoft.interfaces;

import java.io.IOException;
import java.util.List;

public interface ISavedFormat {
	void saveNext(List<String> row) throws IOException;
}
