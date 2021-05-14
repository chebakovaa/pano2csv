package com.bisoft.interfaces;

import com.bisoft.exeptions.GetTitleObjectException;

import java.util.List;

public interface IModelObject {
	List<String> title() throws GetTitleObjectException;
	Iterable<List<String>> body();
}
