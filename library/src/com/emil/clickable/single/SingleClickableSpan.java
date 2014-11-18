package com.emil.clickable.single;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.emil.clickable.listener.OnSpanbableClickListener;

/**
 * 
 * Created by yuym on 10/29/14.
 */
public class SingleClickableSpan extends ClickableSpan {

	/*
	 * the index of word selected in spanText 
	 */
	private int textIndex;
	
	/*
	 * the whole text of span
	 */
	private String spanText;
	
	/*
	 * the background color of word selected
	 */
	private int selectBgColor;
	
	/*
	 * the background color of word unselected
	 */
	private int unSelectBgColor;
	
	/*
	 * text color of word selected
	 */
	private int selectTextColor;
	
	/*
	 * text color of word unselected
	 */
	private int unSelectTextColor;
	
	/*
	 * whether the word is selected
	 */
	private boolean selected;

	/*
	 * the callback of the method "onClick"
	 */
	private OnSpanbableClickListener spanbableClickListener = null;
	
	public SingleClickableSpan(int textIndex,
			String spanText, int selectBgColor,
			int unSelectBgColor, int selectTextColor, int unSelectTextColor) {
		this.spanText = spanText;
		this.textIndex = textIndex;
		this.selectBgColor = selectBgColor;
		this.unSelectBgColor = unSelectBgColor;
		this.selectTextColor = selectTextColor;
		this.unSelectTextColor = unSelectTextColor;
	}

	/**
	 * set callback listener
	 * @param spanbableClickListener
	 */
	public void setSpanbableClickListener(OnSpanbableClickListener spanbableClickListener){
		this.spanbableClickListener = spanbableClickListener;
	}
	
	@Override
	public void onClick(View widget) {
		if(spanbableClickListener != null){
			spanbableClickListener.onSpanbableClick(textIndex, spanText);
		}
	}

	/**
	 * update text color and bg color
	 */
	@Override
	public void updateDrawState(TextPaint ds) {
		ds.bgColor = selected ? selectBgColor : unSelectBgColor;
		ds.setColor(selected ? selectTextColor : unSelectTextColor);
		ds.setUnderlineText(false);
	}

	/**
	 * set whether word is selected
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
