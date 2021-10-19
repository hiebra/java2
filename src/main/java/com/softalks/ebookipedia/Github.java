package com.softalks.ebookipedia;

import java.util.List;

import com.softalks.github.Repository;

class Github extends Repository {
	
	public final String languageCode;
	public final String article;
	
	Github(String repository, List<String> languageCodes) {
		super(repository);
		int index = owner.indexOf('-');
		if (index != -1) {
			String suffix = owner.substring(index + 1);
			if (languageCodes.contains(suffix)) {
				languageCode = suffix;
				article = owner.substring(0, index);
				return;
			} 
		}
		throw new IllegalArgumentException("repository: " + repository);
	}
	
}