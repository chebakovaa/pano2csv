package com.bisoft.interfaces;

import com.bisoft.exeptions.GetTitleObjectException;

import java.util.List;

public interface IModelObject {
	String name();
	List<String> title() throws GetTitleObjectException;
	Iterable<List<String>> body();
}
