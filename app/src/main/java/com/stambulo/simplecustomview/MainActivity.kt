package com.stambulo.simplecustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


class MyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    var offsetX = 0
    var offsetY = 0
    var layoutWidth = 0
    var layoutHeight = 0

    init {
        isClickable = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            layoutWidth = canvas.width
            layoutHeight = canvas.height
        }
        var shapeDrawable: ShapeDrawable

        var left = offsetX + 20
        var top = layoutHeight - offsetY - 110
        var right = offsetX + 230
        var bottom = layoutHeight - offsetY - 50
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds(left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#FF9944"))
        if (canvas != null) {
            shapeDrawable.draw(canvas)
        }

        left = offsetX + 60
        top = layoutHeight - offsetY - 150
        right = offsetX + 180
        bottom = layoutHeight - offsetY - 100
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds(left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#FF9944"))
        if (canvas != null) {
            shapeDrawable.draw(canvas)
        }

        // 1 Колесо
        canvas?.drawCircle(
            (offsetX + 80).toFloat(),
            (height - offsetY - 50).toFloat(),
            (30).toFloat(),
            Paint(Paint.EMBEDDED_BITMAP_TEXT_FLAG)
        )
        // 2 Колесо
        canvas?.drawCircle(
            (offsetX + 170).toFloat(),
            (height - offsetY - 50).toFloat(),
            (30).toFloat(),
            Paint(Paint.EMBEDDED_BITMAP_TEXT_FLAG)
        )
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        rightMoveTimer.start()
        return true
    }

    private val rightMoveTimer = object: CountDownTimer(3000, 50) {
        override fun onTick(millisUntilFinished: Long) {
            Log.i("--->", "onTick - " + (layoutWidth - offsetX))
            if ((layoutWidth - offsetX)>240){
            offsetX += 20
            invalidate()}}

        override fun onFinish() {upMoveTimer.start()}
    }

    private val upMoveTimer = object: CountDownTimer(5000, 50) {
        override fun onTick(millisUntilFinished: Long) {
            if ((layoutHeight - offsetY)>170){
            offsetY += 20
            invalidate()}}

        override fun onFinish() {}
    }
}
