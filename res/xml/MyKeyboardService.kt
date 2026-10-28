import android.inputmethodservice.InputMethodService
import android.inputmethodservice.KeyboardView
import android.view.View

class MyKeyboardService : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as KeyboardView
        val keyboard = Keyboard(this, R.xml.keys_layout)
        keyboardView.keyboard = keyboard
        keyboardView.setOnKeyboardActionListener(this)
        return keyboardView
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection ?: return
        when (primaryCode) {
            -5 -> { // دوگمەی سڕینەوە (Delete)
                inputConnection.deleteSurroundingText(1, 0)
            }
            else -> { // ناردنی پیتەکان
                val char = primaryCode.toChar()
                inputConnection.commitText(char.toString(), 1)
            }
        }
    }

    override fun onPress(primaryCode: Int) {}
    override fun onRelease(primaryCode: Int) {}
    override fun onText(text: CharSequence?) {}
    override fun swipeLeft() {}
    override fun swipeRight() {}
    override fun swipeDown() {}
    override fun swipeUp() {}
}
