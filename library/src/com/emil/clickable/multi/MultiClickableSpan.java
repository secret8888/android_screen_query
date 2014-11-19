package com.emil.clickable.multi;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.emil.clickable.listener.OnSpanbableClickListener;

/**
 * Created by yuym on 10/29/14.
 */
public class MultiClickableSpan extends ClickableSpan{

	/*
	 * the index of word selected in spanText 
	 */
	private int textIndex;
	
	/*
	 * the whole text of span
	 */
	private String spanText;
	
	/*
	 * the position of "spanText" in the whole sentence
	 */
    private int textPosition;
    
    /*
     * the default background color of text
     */
    private int defualtBgColor;
    
    /*
	 * callback of the method "onClick"
	 */
	private OnSpanbableClickListener spanbableClickListener = null;

    public MultiClickableSpan(int textPosition, int textIndex, String spanText, int defualtBgColor) {
        this.spanText = spanText;
        this.textPosition = textPosition;
        this.textIndex = textIndex;
        this.defualtBgColor = defualtBgColor;
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
			spanbableClickListener.onSpanbableClick(textIndex, textPosition, spanText);
		}
    }

    @Override
    public void updateDrawState(TextPaint ds) {
    	ds.bgColor = defualtBgColor;
        ds.setUnderlineText(false);
    }
}
