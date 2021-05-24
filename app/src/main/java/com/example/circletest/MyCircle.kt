package com.example.circletest

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat


class MyCircle(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private val mBounds: Rect = Rect()
    val mPaint = Paint()
    val mPaint2 = Paint()
    var mPaintText = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private val OFFSET = 120
    private var mOffset = OFFSET
    private val MULTIPLIER = 100
    private var mColorBackground = 0
    private var mColorRectangle = 0
    private var mColorAccent = 0
    var vWidth = width
    var vHeight = height
    var halfWidth = vWidth / 2
    var halfHeight = vHeight / 2
    private var zoomed = false
    lateinit var zoomAnimation: ValueAnimator
    private var mCustomImage: Drawable? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawIt(canvas)
    }

    init {
        mColorBackground = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
        mColorRectangle = ResourcesCompat.getColor(resources, R.color.colorRectangle, null)
        mColorAccent = ResourcesCompat.getColor(resources, R.color.purple_200, null)
        mPaint.color = mColorBackground
        mPaint2.color = mColorRectangle
        mPaintText.color = ResourcesCompat.getColor(
            resources,
            R.color.design_default_color_primary_dark, null
        )
        mPaintText.textSize = 70F
        mCustomImage = ContextCompat.getDrawable(context,R.drawable.ic_banner_default)


    }

    fun addText(mCanvas: Canvas?) {
        val text = "done"
        mPaintText.getTextBounds(text, 0, text.length, mBounds)
        mPaintText.isUnderlineText = false
        val x = halfWidth - mBounds.centerX()
        val y = halfWidth / 2.3.toFloat()
        mCanvas?.drawText(text, x.toFloat(), y.toFloat(), mPaintText)
    }

    private fun addCircle(mCanvas: Canvas?) {
        mCanvas!!.drawCircle(
            halfWidth.toFloat(),
            halfHeight.toFloat(),
            halfWidth / 2.0.toFloat(),
            mPaint
        )
    }

    private fun addBorder(mCanvas: Canvas?) {
        mCanvas?.drawCircle(
            halfWidth.toFloat(),
            halfHeight.toFloat(),
            halfWidth / 2.15.toFloat(),
            mPaint2
        )
    }

    private fun drawIt(mCanvas: Canvas?) {
        vWidth = width
        vHeight = height
        halfWidth = vWidth / 2
        halfHeight = vHeight / 2
        addCircle(mCanvas)
        addBorder(mCanvas)
        addText(mCanvas)
        invalidate()
    }

    fun changeColor() {
        mPaint2.color = mColorAccent
        invalidate()
    }

    fun zoomIn(
        blackView: View
    ) {
        if (zoomed) return
        isEnabled = false
        zoomAnimation = ValueAnimator.ofFloat(blackView.scaleX, blackView.scaleX + 0.5f)
        zoomAnimation.addUpdateListener { valueAnimator ->
            val animatedValue = (valueAnimator.animatedValue) as Float
            blackView.scaleX = animatedValue
            blackView.scaleY = animatedValue
        }
        zoomAnimation.duration = 1000
        zoomAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                zoomed = !zoomed
                isEnabled = true
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
        zoomAnimation.start()
    }

    fun zoomOut() {
        if (!::zoomAnimation.isInitialized) return
        if (zoomed) {
            isEnabled = false
            zoomAnimation.interpolator = ReverseInterpolator()
            zoomAnimation.start()
        }

    }
}