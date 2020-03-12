package com.Amazon;

public class GivingData {

	
	public String currentClassName(String Classname) {
		//Getting the Class and replacing 'o' with '.'
		String cellContent1 = Classname.replace("TC", "");
		String ClassName = cellContent1.replace("o", ".");
		return ClassName;
	}
	
}
