package com.emil.clickable.multi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.emil.clickable.constant.Constant;
import com.emil.clickable.utils.Logger;

/**
 * Created by yuym on 10/29/14.
 */
public class CustomizedClickableSpan extends ClickableSpan{

    private int textIndex;
    private String parentText;
    private Handler handler;
    private int textPosition;
    private int type;

    /**
     *
     * @param handler
     * @param textPosition parentText在整个句子中的位置
     * @param textIndex 选中单词的索引位置
     * @param parentText
     * @param type
     */
    public CustomizedClickableSpan(Handler handler, int textPosition, int textIndex, String parentText,int type) {
        this.handler = handler;
        this.parentText = parentText;
        this.textPosition = textPosition;
        this.textIndex = textIndex;
        this.type = type;
    }

    @Override
    public void onClick(View widget) {
        Logger.d(this, "parent: " + parentText);
        Message message = new Message();
        Bundle bundle = new Bundle();
        if(type == Constant.TYPE_TEMPLATE_TITLE){
            Logger.d(this,  "textIndex: " + textIndex + " spanText: "+ parentText.split(" ")[textIndex]);
            message.what = Constant.SPAN_UPDATE_TITLE;
            bundle.putInt(Constant.BUNDLE_SPAN_INDEX, textIndex);
            bundle.putString(Constant.BUNDLE_SPAN_TEXT, parentText);
        }else if (type == Constant.TYPE_TEMPLATE_TIP){
            Logger.d(this, "textIndex: " + textIndex + " spanText: "+ parentText.split(" ")[textIndex]);
            message.what = Constant.SPAN_UPDATE_TIP;
            bundle.putInt(Constant.BUNDLE_TEXT_POSITION, textPosition);
            bundle.putInt(Constant.BUNDLE_SPAN_INDEX, textIndex);
            bundle.putString(Constant.BUNDLE_SPAN_TEXT, parentText);
        } 
        message.setData(bundle);
        handler.sendMessage(message);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
    }
}
