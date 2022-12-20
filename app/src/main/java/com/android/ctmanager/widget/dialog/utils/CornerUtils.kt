package com.android.ctmanager.widget.dialog.utils

import android.R
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable

 object CornerUtils {
    fun cornerDrawable(bgColor: Int, cornerradius: Float): Drawable? {
        val bg = GradientDrawable()
        bg.cornerRadius = cornerradius
        bg.setColor(bgColor)
        return bg
    }

    fun cornerDrawable(bgColor: Int, cornerradius: FloatArray?): Drawable? {
        val bg = GradientDrawable()
        bg.cornerRadii = cornerradius
        bg.setColor(bgColor)
        return bg
    }

    fun cornerDrawable(
        bgColor: Int,
        cornerradius: FloatArray?,
        borderwidth: Int,
        bordercolor: Int
    ): Drawable? {
        val bg = GradientDrawable()
        bg.cornerRadii = cornerradius
        bg.setStroke(borderwidth, bordercolor)
        bg.setColor(bgColor)
        return bg
    }

    /**
     * set btn selector with corner drawable for special position
     */
    fun btnSelector(
        radius: Float,
        normalColor: Int,
        pressColor: Int,
        postion: Int
    ): StateListDrawable? {
        val bg = StateListDrawable()
        var normal: Drawable? = null
        var pressed: Drawable? = null
        if (postion == 0) { // left btn
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, radius, radius)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, radius, radius)
            )
        } else if (postion == 1) { // right btn
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, 0f, 0f)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, 0f, 0f)
            )
        } else if (postion == -1) { // only one btn
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
        } else if (postion == -2) { // for material dialog
            normal = cornerDrawable(normalColor, radius)
            pressed = cornerDrawable(pressColor, radius)
        }
        bg.addState(intArrayOf(-R.attr.state_pressed), normal)
        bg.addState(intArrayOf(R.attr.state_pressed), pressed)
        return bg
    }

    /**
     * set ListView item selector with corner drawable for the last position
     * (ListView的item点击效果,只处理最后一项圆角处理)
     */
    fun listItemSelector(
        radius: Float,
        normalColor: Int,
        pressColor: Int,
        isLastPostion: Boolean
    ): StateListDrawable? {
        val bg = StateListDrawable()
        var normal: Drawable? = null
        var pressed: Drawable? = null
        if (!isLastPostion) {
            normal = ColorDrawable(normalColor)
            pressed = ColorDrawable(pressColor)
        } else {
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
        }
        bg.addState(intArrayOf(-R.attr.state_pressed), normal)
        bg.addState(intArrayOf(R.attr.state_pressed), pressed)
        return bg
    }

    /**
     * set ListView item selector with corner drawable for the first and the last position
     * (ListView的item点击效果,第一项和最后一项圆角处理)
     */
    fun listItemSelector(
        radius: Float, normalColor: Int, pressColor: Int, itemTotalSize: Int,
        itemPosition: Int
    ): StateListDrawable? {
        val bg = StateListDrawable()
        var normal: Drawable? = null
        var pressed: Drawable? = null
        if (itemPosition == 0 && itemPosition == itemTotalSize - 1) { // 只有一项
            normal = cornerDrawable(
                normalColor, floatArrayOf(
                    radius, radius, radius, radius, radius, radius, radius,
                    radius
                )
            )
            pressed = cornerDrawable(
                pressColor, floatArrayOf(
                    radius, radius, radius, radius, radius, radius, radius,
                    radius
                )
            )
        } else if (itemPosition == 0) {
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
            )
        } else if (itemPosition == itemTotalSize - 1) {
            normal = cornerDrawable(
                normalColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
            pressed = cornerDrawable(
                pressColor,
                floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
            )
        } else {
            normal = ColorDrawable(normalColor)
            pressed = ColorDrawable(pressColor)
        }
        bg.addState(intArrayOf(-R.attr.state_pressed), normal)
        bg.addState(intArrayOf(R.attr.state_pressed), pressed)
        return bg
    }

    /*fun cornerDrawable(parseColor: Int, dp2px: Int): Drawable? {

    }
*/
}