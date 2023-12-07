package top.yukonga.update.logic

import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextAnimation(text: CharSequence?, duration: Long = 300, completion: (() -> Unit)? = null) {
    Log.d("TAG", "${this.text}, $text, ${this.text == text}")
    if (!this.text.contentEquals(text) || !this.isVisible) {
        fadOutAnimation(duration) {
            this.text = text
            fadInAnimation(duration) {
                completion?.invoke()
            }
        }
        this.typeface = Typeface.MONOSPACE
    }
}

// ViewExtensions

fun View.fadOutAnimation(duration: Long = 300, visibility: Int = View.INVISIBLE, completion: (() -> Unit)? = null) {
    animate().alpha(0f).setDuration(duration).withEndAction {
            this.visibility = visibility
            completion?.invoke()
        }
}

fun View.fadInAnimation(duration: Long = 300, completion: (() -> Unit)? = null) {
    alpha = 0f
    visibility = View.VISIBLE
    animate().alpha(1f).setDuration(duration).withEndAction {
            completion?.invoke()
        }
}