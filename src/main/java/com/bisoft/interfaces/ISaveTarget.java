package com.bisoft.interfaces;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface ISaveTarget{
	ICustomFormat savedFormat(IModelObject next) throws FileNotFoundException, UnsupportedEncodingException;
}
