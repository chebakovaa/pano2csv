package com.bisoft.interfaces;

import com.bisoft.exeptions.GetTitleObjectException;

import java.util.Arrays;
import java.util.List;

public interface IModelObject {
	String name();
	List<String> title() throws GetTitleObjectException;
	Iterable<List<String>> body();

	public class Fake implements IModelObject {

		private final String name;
		private final List<String> title;
		private final List<List<String>> columns;

		public Fake(String name, List<String> title, List<List<String>> columns) {
			this.name = name;
			this.title = title;
			this.columns = columns;
		}

		@Override
		public String name() {
			return name;
		}

		@Override
		public List<String> title() throws GetTitleObjectException {
			return title;
		}

		@Override
		public Iterable<List<String>> body() {
			return columns;
		}
	}
}
