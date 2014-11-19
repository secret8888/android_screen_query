package com.emil.clickable.listener;

public interface OnSpanbableClickListener {
	/**
	 * 
	 * @param textIndex
	 *            the index of word selected in spanText
	 * @param spanTextPosition
	 *            the whole text of span
	 * @param spanText
	 *            the position of "spanText" in the whole sentence
	 */
	public void onSpanbableClick(int textIndex, int spanTextPosition,
			String spanText);
}
