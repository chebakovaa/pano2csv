package com.bisoft.interfaces;

import java.util.List;

public interface ITableSource {
	List<String> titles();
	
	List<String> row();
	
	boolean isEnd();
}
