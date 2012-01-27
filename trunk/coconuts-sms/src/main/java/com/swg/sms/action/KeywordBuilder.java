package com.swg.sms.action;

import java.util.HashSet;
import java.util.Set;

/**
 * Idenya di sini, system secara pinter membangaun {@link Keyword} object.
 * Pintar contohnya, pada saat client mencreate {@link Keyword} dengan nilai
 * "contoh", maka object ini sekaligus membuat alternative pattern dari nilai
 * "contoh" tersebut.
 * 
 * TODO Tambahin kemampuan buat ingnore case.
 * 
 * @author zakyalvan
 */
public abstract class KeywordBuilder {
	protected String value;
	protected Set<String> patterns = new HashSet<String>();
	protected Set<String> alternativePatterns = new HashSet<String>();
	
	public final void setValue(String value) {
		assert(value != null && value.length() != 0) : "Nilai dari parameter value null atau 0 tidak valid";
		this.value = value;
		alternativePatterns = createAlternativePatterns(value);
	}
	/**
	 * Harusnya di sini nilai tambah menggunakan object ini dibanding
	 * inisiasi langsung dari object {@link SimpleKeyword}
	 * 
	 * @param value
	 * @return
	 */
	protected abstract Set<String> createAlternativePatterns(String value);
	
	public final void setPatterns(Set<String> patterns) {
		patterns.addAll(patterns);
	}
	
	public final boolean hasPattern(String value) {
		return patterns.contains(value);
	}
	public final boolean addPattern(String pattern) {
		return patterns.add(pattern);
	}
	public final boolean removePattern(String pattern) {
		return patterns.remove(pattern);
	}
	
	public Keyword getBuiltResult() {
		SimpleKeyword keyword = new SimpleKeyword(value);
		patterns.addAll(alternativePatterns);
		for(String pattern : patterns) {
			keyword.addPattern(pattern);
		}
		return keyword;
	}
}
