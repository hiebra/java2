package com.softalks.ebookipedia;

import static com.softalks.ebookipedia.Output.print;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ibm.icu.util.ULocale;
import com.softalks.github.Repository;

/**
 * @see updated.Summary
 */
public class SummaryUpdateResponse implements Runnable {

	private final Iso639v1 languageCodes = new Iso639v1();
	@SuppressWarnings("unused")
	private final Element body;
	@SuppressWarnings("unused")
	private final Repository repository;
	private final String language;
	private final String article;
	private final Head head;
	
	public SummaryUpdateResponse(String githubRepositoryName) {
		Element root = Input.summary().getDocumentElement();
		if (root.getTagName().equals("html")) {
			head = new Head(root);
			NodeList nodes = root.getElementsByTagName("body");
			if (nodes.getLength() != 1) {
				throw new IllegalStateException("The HTML document does not contain a <body>");
			}
			body = (Element) nodes.item(0);
		} else if (root.getTagName().equals("body")) {
			head = null;
			body = root;
		} else {
			throw new IllegalStateException("The root element is not supported: <" + root.getTagName() + '>');
		}
		boolean noTitle = head == null || head.title == null;
		String langAttribute = root.getAttribute("lang"); 
		boolean noLang = "".equals(langAttribute);
		if (noLang && noTitle) { 
			Github githubRepository = new Github(githubRepositoryName, languageCodes);
			language = githubRepository.languageCode;
			article = githubRepository.article;
			repository = githubRepository;
		} else if (noTitle || noLang) {
			throw new IllegalStateException("'lang' is specified but <title> is not (or vice versa)");
		} else if (languageCodes.contains(langAttribute)) {
			language = langAttribute;
			article = head.title;
			repository = new Repository(githubRepositoryName);
		} else {
			throw new IllegalStateException("The value specificed as attribute 'lang' at the root element is not valid: " + langAttribute);
		}
	}
	
	@Override
	public void run() {
		ULocale.setDefault(new ULocale(language));
		print("url", "https://" + language + ".wikipedia.org/wiki/" + article.replace(' ', '_'));
		print("title", article);
		try (OutputStream output = new FileOutputStream("docs/summary.html")) {
			output.write(String.valueOf(System.currentTimeMillis()).getBytes());
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

}