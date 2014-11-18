package com.emil.clickable.multi;

import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.emil.clickable.constant.Constant;
import com.emil.clickable.utils.Logger;
import com.emil.clickable.utils.ScreenUtils;

/**
 * Created by yuym on 11/7/14.
 */
public class ScreenSpanableUtils {

    /**
     * 拆分text，并进行点击处理
     * @param handler
     * @param builder
     * @param textPosition text在整个句子中的位置
     * @param text
     * @param type
     */
    private static void appendSpannableBuilder(Handler handler, SpannableStringBuilder builder, int textPosition, String text,
                                        int type){
        String[] titleSplits = text.split(" ");
        for (int i = 0; i < titleSplits.length; i++) {
            CustomizedClickableSpan clickableSpan = new CustomizedClickableSpan(handler, textPosition, i, text,
                    type);
            int start = builder.length();
            builder.append(titleSplits[i]);
            builder.setSpan(clickableSpan, start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(i != titleSplits.length - 1){
                builder.append(" ");
            }
        }
        if(text.endsWith(" ")){
            builder.append(" ");
        }
    }

    /**
     * 更新所选中区域的前景和背景色
     * @param builder
     * @param start
     * @param end
     * @param selectBgColor
     * @param selectTextColor
     */
    public static void updateSelectSpan(SpannableStringBuilder builder, int start, int end, int selectBgColor, int selectTextColor){
        if(start == end){
            return;
        }
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(selectBgColor);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(selectTextColor);
        builder.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(foregroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 更新所选中区域的前景和背景色
     * @param builder
     * @param start
     * @param end
     * @param selectTextColor
     */
    public static void updateTextColorSpan(SpannableStringBuilder builder, int start, int end, int selectTextColor){
        if(start == end){
            return;
        }
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(selectTextColor);
        builder.setSpan(foregroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 更新所选中区域的背景色
     * @param builder
     * @param start
     * @param end
     * @param selectBgColor
     */
    public static void updateBgColorSpan(SpannableStringBuilder builder, int start, int end, int selectBgColor){
        if(start == end){
            return;
        }
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(selectBgColor);
        builder.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 针对不存在汉字内容的text，可以根据空格将text进行拆分，最后利用ClickableSpan处理成可点击的内容
     * @param handler
     * @param spanView
     * @param text
     * @param selectBgColor
     * @param selectTextColor
     */
    public static void setTextSpan(Handler handler, TextView spanView, String text, int selectStart, int selectEnd,
                            int selectBgColor, int selectTextColor) {
        if (!TextUtils.isEmpty(text)) {
            spanView.setMovementMethod(LinkMovementMethod.getInstance());
            SpannableStringBuilder builder = new SpannableStringBuilder();
            appendSpannableBuilder(handler, builder, 0, text, Constant.TYPE_TEMPLATE_TITLE);
            updateSelectSpan(builder, selectStart, selectEnd, selectBgColor, selectTextColor);
            spanView.setText(builder);
        }
    }

    public static void appendTextSpan(Handler handler, TextView spanView, String text, int selectStart, int selectEnd,
                                   int selectBgColor, int selectTextColor) {
        if (!TextUtils.isEmpty(text)) {
            spanView.setMovementMethod(LinkMovementMethod.getInstance());
            SpannableStringBuilder builder = new SpannableStringBuilder();
            appendSpannableBuilder(handler, builder, 0, text, Constant.TYPE_TEMPLATE_TITLE);
            updateSelectSpan(builder, selectStart, selectEnd, selectBgColor, selectTextColor);
            spanView.append(builder);
        }
    }

    /**
     * （1）首先将内容进行格式化，利用正则表达式将非字母的内容替换成统一字符“#”
     * （2）遍历格式化后的内容，如果是“#”，则直接显示原始的内容
     * （3）否则利用ClickableSpan进行分段可点击显示。
     * @param handler
     * @param spanView
     * @param text
     * @param selectStart
     * @param selectEnd
     * @param selectBgColor
     * @param selectTextColor
     */
    public static void setTipSpan(Handler handler, TextView spanView, String text, int selectStart, int selectEnd,
                           int selectBgColor, int selectTextColor, int type) {
        if (!TextUtils.isEmpty(text)) {
            String formatText = ScreenUtils.formatSplit(text, "#");
            spanView.setMovementMethod(LinkMovementMethod.getInstance());
            SpannableStringBuilder builder = new SpannableStringBuilder();
            StringBuilder subStringBuilder = null;
            for(int i = 0; i < formatText.length(); i ++){
                String subText = formatText.substring(i, i+1);
                if(subText.equals("#")){
                    if(subStringBuilder != null){
                        appendSpannableBuilder(handler, builder, i - subStringBuilder.length(), subStringBuilder.toString(), type);
                        subStringBuilder = null;
                    }
                    builder.append(text.substring(i, i+1));
                }else{
                    if(subStringBuilder == null){
                        subStringBuilder = new StringBuilder(subText);
                    }else{
                        subStringBuilder.append(subText);
                    }
                }
            }
            Logger.d("ScreenSpannable ",builder.toString());
            updateSelectSpan(builder, selectStart, selectEnd, selectBgColor, selectTextColor);
            spanView.setText(builder);
        }
    }
}
