/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.ancustomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ScrollView;

/**
 * Created :  2016-09-28
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class BounceScrollView extends ScrollView {

    private static final int OVERSCROLL_RESISTOR_MAX = 10;
    private Context mContext;

    private int mMaxYOverscrollDistance;
    private int mOverscrollHeight = 100;
    private float mResistor = 0.5f;


    public BounceScrollView(Context context) {
        this(context, null);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // set max overscroll length
        mMaxYOverscrollDistance = (int) dpToPx(mOverscrollHeight);
        // enable overscroll mode
        setOverScrollMode(ScrollView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        // cancel edge effect
        cancelEdgeEffect();
    }

    private void cancelEdgeEffect() {
        // cancel overscroll edge effect by set drawable to transparent
        int glowDrawableId = mContext.getResources().getIdentifier("overscroll_glow", "drawable", "android");
        Drawable androidGlow = mContext.getResources().getDrawable(glowDrawableId);
        androidGlow.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC);
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        // FIXME: 16/9/29 sometime it cannot accurately close back

//        Log.d(getClass().getSimpleName(), "overScrollBy:deltaX="+deltaX
//                +"|deltaY="+deltaY
//                +"|scrollX="+scrollX
//                +"|scrollY="+scrollY
//                +"|scrollRangeX="+scrollRangeX
//                +"|scrollRangeY="+scrollRangeY
//                +"|maxOverScrollX="+maxOverScrollX
//                +"|maxOverScrollY="+maxOverScrollY
//                +"|isTouchEvent="+isTouchEvent);
        // when scrollview is scrolled to top or bottom, then enable the resist
        if (scrollY < 0 || scrollY > scrollRangeY) {
            deltaY /= mResistor * OVERSCROLL_RESISTOR_MAX;
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance, isTouchEvent);
    }

    /**
     * Set overscroll resist effect
     * @param resistor must between 0 and 1
     */
    public void setOverscrollResistor(float resistor) {
        if (resistor <= 0f || resistor > 1f) {
            return;
        }
        mResistor = resistor;
    }

    /**
     * Set overscroll background height
     * @param dp height in dp. 0 means no overscroll, positive number will be overscroll height, should not less than 0
     */
    public void setOverscrollHeight(int dp) {
        if (dp < 0) {
            return;
        }
        mOverscrollHeight = dp;
        mMaxYOverscrollDistance = (int) dpToPx(mOverscrollHeight);
    }
}
