package com.example.circletest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class Circle(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint
    private val rect: RectF
    var angle: Float
    val START_ANGLE_POINT = 180f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint)
    }

    init {
        val strokeWidth = 20f
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        //Circle color
        paint.color = Color.RED

        //size 200x200 example
        rect = RectF(strokeWidth, strokeWidth, (200 + strokeWidth), (200 + strokeWidth)
        )

        //Initial Angle (optional, it can be zero)
        angle = 0f
    }
}