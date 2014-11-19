package com.emil.clickable.multi;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.emil.clickable.listener.OnSpanbableClickListener;
import com.emil.clickable.utils.Logger;
import com.emil.clickable.utils.ScreenUtils;

/**
 * Created by yuym on 11/7/14.
 */
public class MultiClickableClient {

	/**
	 * divide and format words which are made to be clickable
	 * 
	 * @param builder
	 * @param textPosition
	 * @param text
	 */
	private static void appendSpannableBuilder(
			OnSpanbableClickListener spanbableClickListener,
			SpannableStringBuilder builder, int textPosition, String text,
			int defualtBgColor) {
		String[] titleSplits = text.split(" ");
		for (int i = 0; i < titleSplits.length; i++) {
			MultiClickableSpan clickableSpan = new MultiClickableSpan(
					textPosition, i, text, defualtBgColor);
			clickableSpan.setSpanbableClickListener(spanbableClickListener);
			int start = builder.length();
			builder.append(titleSplits[i]);
			builder.setSpan(clickableSpan, start, builder.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			if (i != titleSplits.length - 1) {
				builder.append(" ");
			}
		}
		if (text.endsWith(" ")) {
			builder.append(" ");
		}
	}

	/**
	 * update background color and foreground color of area selected
	 * 
	 * @param builder
	 * @param start
	 * @param end
	 * @param selectBgColor
	 * @param selectTextColor
	 */
	public static void updateSelectSpan(SpannableStringBuilder builder,
			int start, int end, int selectBgColor, int selectTextColor) {
		if (start == end) {
			return;
		}
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
				selectBgColor);
		ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
				selectTextColor);
		builder.setSpan(backgroundColorSpan, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder.setSpan(foregroundColorSpan, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	/**
	 * update foreground color of area selected
	 * 
	 * @param builder
	 * @param start
	 * @param end
	 * @param selectTextColor
	 */
	public static void updateTextColorSpan(SpannableStringBuilder builder,
			int start, int end, int selectTextColor) {
		if (start == end) {
			return;
		}
		ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
				selectTextColor);
		builder.setSpan(foregroundColorSpan, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	/**
	 * update background color of area selected
	 * 
	 * @param builder
	 * @param start
	 * @param end
	 * @param selectBgColor
	 */
	public static void updateBgColorSpan(SpannableStringBuilder builder,
			int start, int end, int selectBgColor) {
		if (start == end) {
			return;
		}
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
				selectBgColor);
		builder.setSpan(backgroundColorSpan, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	/**
	 * divide sentences by space and make them clickable with the method
	 * "appendSpannableBuilder()". note: this method is only for english
	 * sentences
	 * 
	 * @param spanbableClickListener
	 * @param text
	 * @param selectStart
	 * @param selectEnd
	 * @param selectBgColor
	 * @param selectTextColor
	 * @param defualtBgColor
	 * @return
	 */
	private SpannableStringBuilder getInitedBuilder(
			OnSpanbableClickListener spanbableClickListener, String text,
			int selectStart, int selectEnd, int selectBgColor,
			int selectTextColor, int defualtBgColor) {
		if (!TextUtils.isEmpty(text)) {
			SpannableStringBuilder builder = new SpannableStringBuilder();
			appendSpannableBuilder(spanbableClickListener, builder, 0, text,
					defualtBgColor);
			updateSelectSpan(builder, selectStart, selectEnd, selectBgColor,
					selectTextColor);

			return builder;
		}

		return null;
	}

	/**
	 * set format builder
	 * 
	 * @param spanbableClickListener
	 * @param spanView
	 * @param text
	 * @param selectStart
	 * @param selectEnd
	 * @param selectBgColor
	 * @param selectTextColor
	 * @param defualtBgColor
	 */
	public void setTextSpan(OnSpanbableClickListener spanbableClickListener,
			TextView spanView, String text, int selectStart, int selectEnd,
			int selectBgColor, int selectTextColor, int defualtBgColor) {
		spanView.setMovementMethod(LinkMovementMethod.getInstance());
		SpannableStringBuilder builder = getInitedBuilder(
				spanbableClickListener, text, selectStart, selectEnd,
				selectBgColor, selectTextColor, defualtBgColor);
		spanView.setText(builder);
	}

	/**
	 * append format builder
	 * 
	 * @param spanbableClickListener
	 * @param spanView
	 * @param text
	 * @param selectStart
	 * @param selectEnd
	 * @param selectBgColor
	 * @param selectTextColor
	 * @param defualtBgColor
	 */
	public void appendTextSpan(OnSpanbableClickListener spanbableClickListener,
			TextView spanView, String text, int selectStart, int selectEnd,
			int selectBgColor, int selectTextColor, int defualtBgColor) {
		spanView.setMovementMethod(LinkMovementMethod.getInstance());
		SpannableStringBuilder builder = getInitedBuilder(
				spanbableClickListener, text, selectStart, selectEnd,
				selectBgColor, selectTextColor, defualtBgColor);
		spanView.append(builder);
	}

	/**
	 * Format sentences and replace some words by "#" with the regular
	 * expressions "[^a-zA-Z\\-\\s']". Traverse the format sentences and if the
	 * char is "#", show the original, or make the word clickable with
	 * "ClickableSpan"
	 * 
	 * @param spanbableClickListener
	 * @param spanView
	 * @param text
	 * @param selectStart
	 *            first position of area selected
	 * @param selectEnd
	 *            last position of area selected
	 * @param selectBgColor
	 * @param selectTextColor
	 * @param defualtBgColor
	 *            background color of text
	 */
	public static void setTipSpan(
			OnSpanbableClickListener spanbableClickListener, TextView spanView,
			String text, int selectStart, int selectEnd, int selectBgColor,
			int selectTextColor, int defualtBgColor) {
		if (!TextUtils.isEmpty(text)) {
			String formatText = ScreenUtils.formatSplit(text, "#");
			spanView.setMovementMethod(LinkMovementMethod.getInstance());
			SpannableStringBuilder builder = new SpannableStringBuilder();
			StringBuilder subStringBuilder = null;
			for (int i = 0; i < formatText.length(); i++) {
				String subText = formatText.substring(i, i + 1);
				if (subText.equals("#")) {
					if (subStringBuilder != null) {
						appendSpannableBuilder(spanbableClickListener, builder,
								i - subStringBuilder.length(),
								subStringBuilder.toString(), defualtBgColor);
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
			Logger.d("ScreenSpannable ", builder.toString());
			updateSelectSpan(builder, selectStart, selectEnd, selectBgColor,
					selectTextColor);
			spanView.setText(builder);
		}
	}
}
