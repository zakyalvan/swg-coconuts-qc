package com.swg.web.client.util;

public class StringUtil {
	public static String normalize(String string) {
		String[] nameChunks = string.split("\\s+?");
		
		StringBuilder nameBuilder = new StringBuilder();
		for(int i = 0 ; i < nameChunks.length ; i++) {
			String nameChunk = nameChunks[i].toUpperCase();
			nameChunk.replaceFirst(nameChunk.substring(1), nameChunk.substring(1).toLowerCase());
			nameBuilder.append(nameChunk);
			
			if(i+1 != nameChunks.length)
				nameBuilder.append(" ");
		}
		return nameBuilder.toString();
	}
}
