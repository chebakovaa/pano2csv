package com.bisoft.interfaces;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface ISaveTarget{
	IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException;

	public class Fake implements ISaveTarget {

		public Fake(){

		}

		@Override
		public IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException {
			return new IOpenedFile.Fake();
		}
	}
}
