package com.bisoft.interfaces;

import com.bisoft.exeptions.GetObjectNamesException;

import java.util.Iterator;

public interface IModelSource {
	Iterator<IModelObject> objectCollection() throws GetObjectNamesException;
}
