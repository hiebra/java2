package com.softalks.ebookipedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.icu.util.ULocale;

class Iso639v1 extends ArrayList<String> {

	private static final long serialVersionUID = 1167006880898269396L;

	Iso639v1() {
		List<String> deprecated = Arrays.asList("bh", "in", "iw", "ji", "jw", "mo", "sh");
		List<String> all = Arrays.asList(ULocale.getISOLanguages());
		for (String language : all) {
			if (language.length() != 2) { // it does not match the getISOLanguages javadoc
				continue; 
			}
			if (!deprecated.contains(language)) {
				add(language);
			}
		}		
	}
	
}
