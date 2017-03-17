/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lixplor.fastcustomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ScrollView;

/**
 * ScrollView with bounce effect
 *
 * Created :  2016-09-28
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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
