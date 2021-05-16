package com.bisoft.interfaces;

import com.bisoft.exeptions.GetObjectNamesException;

import java.util.Iterator;
import java.util.List;

public interface IModelSource {
	Iterator<IModelObject> objectCollection() throws GetObjectNamesException;


	class Fake implements IModelSource {

		private final List<IModelObject> list;

		public Fake(List<IModelObject> list){
			this.list = list;
		}

		@Override
		public Iterator<IModelObject> objectCollection() throws GetObjectNamesException {
			return (list)
					.listIterator();
		}
	}
}
