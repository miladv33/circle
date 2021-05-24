package com.example.circletest

import android.view.animation.Animation
import android.view.animation.Transformation


class CircleAngleAnimation(circle: Circle, newAngle: Int) : Animation() {
    private val circle: Circle = circle
    private val oldAngle: Float = circle.angle
    private val newAngle: Float = newAngle.toFloat()
    override fun applyTransformation(
        interpolatedTime: Float,
        transformation: Transformation?
    ) {
        val angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        circle.angle = angle
        circle.requestLayout()
    }

}