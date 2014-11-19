android screen query
===================

android screen query with Clickablespan and Spanwatcher. when you click one word on View, you will get the word index and the whole sencence. after that,you can query the word or do anothing that you want.

####1、Single Word

![ScreenShot](https://raw.githubusercontent.com/secret8888/android_screen_query/master/screenshots/single.png)

In this mode, you can use the method below:
``` xml
SingleClickableClient.getInstance().setTipSpan(this, titleView,
				getString(R.string.title_sample), Color.BLUE, Color.WHITE,
				Color.WHITE, Color.BLACK);

description:
        /**
	 * Format sentences and replace some words by "#" with the regular
	 * expressions "[^a-zA-Z\\-\\s']". Traverse the format sentences and if the
	 * char is "#", show the original, or make the word clickable with
	 * "ClickableSpan"
	 * 
	 * @param listener
	 *            callback method
	 * @param spanView
	 *            the view show text
	 * @param text
	 *            the text that need to be clickable
	 * @param selectBgColor
	 *            the background color of area selected
	 * @param unSelectBgColor
	 *            the background color of area unselected
	 * @param selectTextColor
	 *            the text color of area selected
	 * @param unSelectTextColor
	 *            the text color of area unselected
	 */
	public void setTipSpan(OnSpanbableClickListener listener,
			TextView spanView, String text, int selectBgColor,
			int unSelectBgColor, int selectTextColor, int unSelectTextColor)

```
The result just like above.

==============================================================================================================

####2、Multi Words

![ScreenShot](https://raw.githubusercontent.com/secret8888/android_screen_query/master/screenshots/multi.png)

In this mode, you can use the method below:
``` xml
MultiClickableClient.setTipSpan(tipListener, tipView,
				getString(R.string.tip_sample), 0, 0,
				Color.BLACK, Color.WHITE, Color.WHITE);

description:
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
			int selectTextColor, int defualtBgColor)

```
The result just like above.



