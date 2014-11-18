package com.emil.clickable.single;

import android.text.Selection;
import android.text.SpanWatcher;
import android.text.Spannable;

/**
 * SingleSpanWatcher, screen click watcher based on SpanWatcher
 * 
 * @author yuym on 10/29/14.
 */
public class SingleSpanWatcher implements SpanWatcher {
	private SingleClickableSpan selectedSpan = null;

	/**
	 * select word which is watched
	 * @param text
	 * @param what
	 * @param start
	 * @param end
	 */
	private void changeColor(Spannable text, Object what, int start, int end) {
		if (what == Selection.SELECTION_END) {
			SingleClickableSpan[] spans = text.getSpans(start, end,
					SingleClickableSpan.class);
			if (spans != null) {
				// tv.setHighlightColor(spans[0].getUnSelectColor());
				if (selectedSpan != null) {
					selectedSpan.setSelected(false);
				}
				selectedSpan = spans[0];
				selectedSpan.setSelected(true);
			}
		}
	}

	@Override
	public void onSpanAdded(Spannable text, Object what, int start, int end) {
		changeColor(text, what, start, end);
	}

	@Override
	public void onSpanChanged(Spannable text, Object what, int ostart,
			int oend, int nstart, int nend) {
		changeColor(text, what, nstart, nend);
	}

	@Override
	public void onSpanRemoved(Spannable text, Object what, int start, int end) {
	}
}
