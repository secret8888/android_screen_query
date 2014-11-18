package com.emil.clickable.single;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.emil.clickable.listener.OnSpanbableClickListener;
import com.emil.clickable.utils.ScreenUtils;

/**
 * Created by yuym on 11/7/14.
 */
public class SingleClickableClient {

	private static SingleClickableClient singleClickableClient = null;

	private SingleClickableClient() {
	}

	public static SingleClickableClient getInstance() {
		if (singleClickableClient == null) {
			singleClickableClient = new SingleClickableClient();
		}

		return singleClickableClient;
	}

	/**
	 * divide and format words which are made to be clickable
	 * 
	 * @param listener
	 * @param builder
	 * @param text
	 * @param type
	 * @param selectBgColor
	 * @param unSelectBgColor
	 * @param selectTextColor
	 * @param unSelectTextColor
	 */
	private void appendSpannableBuilder(OnSpanbableClickListener listener,
			SpannableStringBuilder builder, String text,
			int selectBgColor, int unSelectBgColor, int selectTextColor,
			int unSelectTextColor) {
		String[] titleSplits = text.split(" ");
		for (int i = 0; i < titleSplits.length; i++) {
			SingleClickableSpan clickableSpan = new SingleClickableSpan(
					i, ScreenUtils.formatSplit(text, ""), selectBgColor,
					unSelectBgColor, selectTextColor, unSelectTextColor);
			clickableSpan.setSpanbableClickListener(listener);
			int start = builder.length();
			builder.append(titleSplits[i]);
			builder.setSpan(clickableSpan, start, builder.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			builder.append(" ");
		}
	}

	/**
	 * divide sentences by space and make them clickable with the method
	 * "appendSpannableBuilder()". note: this method is only for english
	 * sentences
	 * 
	 * @param listener
	 * @param spanView
	 * @param text
	 * @param selectBgColor
	 * @param unSelectBgColor
	 * @param selectTextColor
	 * @param unSelectTextColor
	 *            @
	 */
	public void setTextSpan(OnSpanbableClickListener listener,
			TextView spanView, String text, int selectBgColor,
			int unSelectBgColor, int selectTextColor, int unSelectTextColor) {
		if (!TextUtils.isEmpty(text)) {
			spanView.setMovementMethod(LinkMovementMethod.getInstance());
			SpannableStringBuilder builder = new SpannableStringBuilder();
			builder.setSpan(SingleSpanWatcher.class, 0, 0,
					Spanned.SPAN_INCLUSIVE_INCLUSIVE);
			appendSpannableBuilder(listener, builder, text, selectBgColor,
					unSelectBgColor, selectTextColor, unSelectTextColor);
			spanView.setText(builder);
		}
	}

	/**
	 * Format sentences and replace some words by "#" with the regular expressions
	 * "[^a-zA-Z\\-\\s']". Traverse the format sentences and if the char is
	 * "#", show the original, or make the word clickable with "ClickableSpan"
	 * 
	 * @param listener
	 * @param spanView
	 * @param text
	 * @param selectBgColor
	 * @param unSelectBgColor
	 * @param selectTextColor
	 * @param unSelectTextColor
	 */
	public void setTipSpan(OnSpanbableClickListener listener,
			TextView spanView, String text, int selectBgColor,
			int unSelectBgColor, int selectTextColor, int unSelectTextColor) {
		if (!TextUtils.isEmpty(text)) {
			String formatText = ScreenUtils.formatSplit(text, "#");
			spanView.setMovementMethod(LinkMovementMethod.getInstance());
			SpannableStringBuilder builder = new SpannableStringBuilder();
			builder.setSpan(new SingleSpanWatcher(), 0, 0,
					Spanned.SPAN_INCLUSIVE_INCLUSIVE);
			StringBuilder subStringBuilder = null;
			for (int i = 0; i < formatText.length(); i++) {
				String subText = formatText.substring(i, i + 1);
				if (subText.equals("#")) {
					if (subStringBuilder != null) {
						appendSpannableBuilder(listener, builder,
								subStringBuilder.toString(), selectBgColor,
								unSelectBgColor, selectTextColor,
								unSelectTextColor);
						subStringBuilder = null;
					}
					builder.append(text.substring(i, i + 1));
				} else {
					if (subStringBuilder == null) {
						subStringBuilder = new StringBuilder(subText);
					} else {
						subStringBuilder.append(subText);
					}
				}
			}

			spanView.setText(builder);
		}
	}
}
