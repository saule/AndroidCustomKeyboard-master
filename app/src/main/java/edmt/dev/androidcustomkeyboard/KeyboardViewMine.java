package edmt.dev.androidcustomkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class KeyboardViewMine extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    int keyXAxis = 25;
    int keyYAxis = 30;

    public KeyboardViewMine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean onLongPress(Keyboard.Key popupKey) {
        boolean keyPress = false;
        if (popupKey.codes[0] == 121) {
            getOnKeyboardActionListener().onKey('ý', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 111) {
            getOnKeyboardActionListener().onKey('ó', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 117) {
            getOnKeyboardActionListener().onKey('ú', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 97) {
            getOnKeyboardActionListener().onKey('á', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 1077) {
            getOnKeyboardActionListener().onKey('ё', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 1100) {
            getOnKeyboardActionListener().onKey('ъ', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 46) {
            getOnKeyboardActionListener().onKey(',', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
        if (popupKey.codes[0] == 63) {
            getOnKeyboardActionListener().onKey('!', null);
            //Toast.makeText(this.getContext(),"code replaced",Toast.LENGTH_LONG).show();
            keyPress=true;
        }
      //  Toast.makeText(this.getContext(),"code do not replaced",Toast.LENGTH_LONG).show();
        return keyPress;
        // return super.onLongPress(popupKey);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("KeyboardViewMine", "onDraw");
        System.out.println("onDraw kbdMine");
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(35);
        paint.setColor(Color.WHITE);
        //get all your keys and draw whatever you want
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for(Keyboard.Key key: keys) {
            if(key.label != null) {

                if (key.label.toString().equals("y"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                  canvas.drawText("ý", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("Y"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("Ý", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                        //XAxix = 25, YAxis = 50
                else if (key.label.toString().equals("U"))
                    canvas.drawText("Ú", key.x + (key.width / 2) + 10, key.y + 25, paint);
                else  if (key.label.toString().equals("u"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("ú", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);

                else if (key.label.toString().equals("o") )
                    canvas.drawText("ó", key.x + (key.width / 2) + 10, key.y + 25, paint);
                else if (key.label.toString().equals("O") )
                    canvas.drawText("Ó", key.x + (key.width / 2) + 10, key.y + 25, paint);

                else if (key.label.toString().equals("a") )
                    canvas.drawText("á", key.x + (key.width / 2) + 10, key.y + 25, paint);
                else if (key.label.toString().equals("A") )
                    canvas.drawText("Á", key.x + (key.width / 2) + 10, key.y + 25, paint);
               // else if (key.label.toString().equals("."))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                 //   canvas.drawText(",", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("?"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("!", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("е"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("ё", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("Е"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("Ё", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("ь"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("ъ", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else if (key.label.toString().equals("Ь"))
                    //canvas.drawText(String.valueOf(1), key.x + (key.width / 2) + 10, key.y + 25, paint);
                    canvas.drawText("Ъ", key.x + (key.width - keyXAxis), key.y + keyYAxis, paint);
                else
                {}
            }
        }
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
