package com.example.circletest
import android.view.animation.Interpolator;
import java.lang.Math.abs

class ReverseInterpolator:Interpolator  {
    override fun getInterpolation(input: Float): Float {
        return kotlin.math.abs(input - 1f);
    }
}