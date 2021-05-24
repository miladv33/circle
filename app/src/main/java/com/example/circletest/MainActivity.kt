package com.example.circletest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circle = findViewById<View>(R.id.circle) as Circle
        val animation = CircleAngleAnimation(circle, 360)
        animation.duration = 5000
        circle.startAnimation(animation)

        val myCircle = findViewById<View>(R.id.myCircle) as MyCircle
        myCircle.setOnClickListener {
            myCircle.changeColor()
            myCircle.zoomIn(myCircle)
            myCircle.zoomOut()
        }
    }

}