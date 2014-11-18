package com.emil.clickable.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreenUtils {

	/**
	 * Formating content with regular expressions "[^a-zA-Z\\-\\s']" which means
	 * replace all the elements of the content that is not a letter, - , space
	 * or single quotes.
	 * 
	 * @param content
	 * @param replacement
	 * @return the string formated
	 */
	public static String formatSplit(String content, String replacement) {
		Pattern pattern = Pattern.compile("[^a-zA-Z\\-\\s']");
		Matcher matcher = pattern.matcher(content);
		String formatContent = matcher.replaceAll(replacement);
		return formatContent;
	}

	/**
	 * getting the length before the index(spanIndex) of splits
	 * 
	 * @param splits
	 * @param spanIndex
	 * @return the length of chars before spanIndex of splits
	 */
	public static int getInnerLength(String[] splits, int spanIndex) {
		int evaluationLength = 0;
		for (int i = 0; i < spanIndex; i++) {
			evaluationLength += splits[i].length() + 1;
		}
		return evaluationLength;
	}
}
