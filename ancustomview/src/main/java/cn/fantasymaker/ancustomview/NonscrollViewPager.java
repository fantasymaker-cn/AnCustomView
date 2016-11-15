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

package cn.fantasymaker.ancustomview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created :  2016-11-15
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class NonscrollViewPager extends ViewPager {

    private boolean mCanScroll = false;

    public NonscrollViewPager(Context context) {
        super(context);
    }

    public NonscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mCanScroll && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mCanScroll && super.onInterceptTouchEvent(event);
    }

    /**
     * Set if viewpager can be scrolled by touch movement.
     * Note that this only affects touch movement. You can still
     * call setCurrentItem() to scroll page.
     *
     * @param canScroll true if can scroll; false otherwise
     */
    public void setCanScroll(boolean canScroll) {
        mCanScroll = canScroll;
    }

    /**
     * Return if this viewpager can be scrolled by touch movement
     *
     * @return true if can scroll; false otherwise
     */
    public boolean isCanScroll() {
        return mCanScroll;
    }
}
