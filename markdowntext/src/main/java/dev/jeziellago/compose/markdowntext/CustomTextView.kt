package dev.jeziellago.compose.markdowntext

import android.content.Context
import android.text.Spannable
import android.text.style.ClickableSpan
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView(context: Context?) : AppCompatTextView(context!!) {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_DOWN) {
            val link = getClickableSpans(event)

            if (link.size != 0) {
                if (event.action == MotionEvent.ACTION_UP) {
                    link[0].onClick(this)
                }
                return true
            }
        }
        return false
    }

    private fun getClickableSpans(event: MotionEvent): Array<ClickableSpan> {
        var x = event.x.toInt()
        var y = event.y.toInt()

        x -= totalPaddingLeft
        y -= totalPaddingTop

        x += scrollX
        y += scrollY

        val layout = layout
        val line = layout.getLineForVertical(y)
        val off = layout.getOffsetForHorizontal(line, x.toFloat())

        val spannable = text as Spannable
        return spannable.getSpans(off, off, ClickableSpan::class.java)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}