package compunell.org.util;

import java.io.File;
import java.net.URI;
import java.util.Optional;

public class Util {

	public static boolean isPathValid(String path) {
		try {
			File file = new File(path);
			 return file.exists();
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static Optional<String> getFileExtension(String path) {
		
		try {
			File file = new File(path);
			if(file.exists()) {
				if(path.contains("."))
					return Optional.ofNullable(path.substring(path.lastIndexOf(".")+1));
					else
						return Optional.empty();
			}
			else
				return Optional.empty();
		} catch(Exception ex) {
			return Optional.empty();
		}
	}
	
	public static boolean isNumeric(String num) {
		if(null == num)
			return false;
		char[] charArray = num.trim().toCharArray();
		for(int i = 0; i< num.length();i++) {
			if(!(Character.isDigit(charArray[i])))
				return false;
		}
		return true;
	}
	public static boolean isAlpha(String alpha) {
		if(null == alpha)
			return false;
		char[] charArray = alpha.trim().toCharArray();
		for(int i = 0; i< alpha.length();i++) {
			if(!(Character.isLetter(charArray[i])))
				return false;
		}
		return true;
	}
	
	public static boolean isAlphaNumericWithSpace(String alphaNum) {
		if(null == alphaNum)
			return false;
		char[] charArray = alphaNum.trim().toCharArray();
		for(int i = 0; i< alphaNum.length();i++) {
			if(charArray[i] != ' ') {
			if(!(Character.isDigit(charArray[i]) || Character.isLetter(charArray[i])))
				return false;
			}
			else
				continue;
		}
		return true;
	}
}
