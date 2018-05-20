package edmt.dev.androidcustomkeyboard;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.util.List;

public class EDMTKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardViewMine kv;
    private Keyboard keyboard;
    private boolean isQwerty=true;
    private boolean isKZ = true;
    private  boolean isCaps = false;
    private boolean autoCapitalise=false;

    private KeyboardViewMine keyboardViewMine;
    //Press Ctrl+O







    @Override
    public View onCreateInputView() {
       // kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
        kv = (KeyboardViewMine)getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(this,R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        System.out.println("tttttt");
      //  keyboardViewMine = (KeyboardViewMine)getLayoutInflater().inflate(R.layout.keyboard,null);
        return kv;
    }


    /**
     * Helper to send a key down / key up pair to the current editor.
     */
    private void keyDownUp(int keyEventCode) {
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int i, int[] ints) {
        System.out.println("onKey");
        InputConnection ic = getCurrentInputConnection();
        playClick(i);
        switch (i)
        {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                isCaps = !isCaps;
                //keyboardViewMine.onDraw(canvas???);
                keyboard.setShifted(isCaps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                System.out.println("Keyboard.KEYCODE_DONE == "+Keyboard.KEYCODE_DONE);
               // ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
                /** Handle the 'done' action accordingly to the IME Options. */

                EditorInfo curEditor = getCurrentInputEditorInfo();
                switch (curEditor.imeOptions & EditorInfo.IME_MASK_ACTION) {
                    case EditorInfo.IME_ACTION_DONE:
                        keyDownUp(66);
                        break;
                    case EditorInfo.IME_ACTION_GO:
                        getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_GO);
                        break;
                    case EditorInfo.IME_ACTION_NEXT:
                        keyDownUp(66);
                        break;
                    case EditorInfo.IME_ACTION_SEARCH:
                        getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_SEARCH);
                        break;
                    case EditorInfo.IME_ACTION_SEND:
                        keyDownUp(66);
                        break;
                    default:
                        keyDownUp(66);
                        break;
                }
                break;

            case -2:
               if (isQwerty) {
                    keyboard = new Keyboard(this,R.xml.symbols);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty=false;
                    break;
               }
               else{
                   keyboard = new Keyboard(this, R.xml.qwerty);
                   kv.setKeyboard(keyboard);
                   kv.invalidateAllKeys();
                   isQwerty=true;
                   break;
               }

            case -3:
                if (isKZ) {
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty = true;
                    break;
                } else
                {
                    keyboard = new Keyboard(this, R.xml.russianalphabet);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty = true;
                    break;
                }
            case -7:
                if (isKZ) {
                    keyboard = new Keyboard(this,R.xml.russianalphabet);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isKZ=false;
                    break;
                }
                else{
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isKZ=true;
                    break;
                }

            default:
                char code = (char)i;
                if(Character.isLetter(code) && isCaps)
                        code = Character.toUpperCase(code);
                ic.commitText(String.valueOf(code),1);
        }

    }

    private void playClick(int i) {

        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(i)
        {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onText(CharSequence charSequence) {

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

/*
    public void onDraw (Canvas canvas) {
        System.out.println("canvas draw");
        kv.onDraw(canvas);
        doMoreDrawing (canvas);
    }

    public void  doMoreDrawing(Canvas canvas) {

// ...
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(18);
        paint.setColor(Color.WHITE);
//get all your keys and draw whatever you want
        List<Keyboard.Key> keys = kv.getKeyboard().getKeys();
        for(Keyboard.Key key: keys) {
            if(key.label != null) {

                if (key.label.toString().equals("y") || key.label.toString().equals("Y"))
                    canvas.drawText("ý", key.x + (key.width / 2) + 10, key.y + 25, paint);

                else if (key.label.toString().equals("u") || key.label.toString().equals("U"))
                    canvas.drawText("ú", key.x + (key.width / 2) + 10, key.y + 25, paint);

                else if (key.label.toString().equals("o") || key.label.toString().equals("o"))
                    canvas.drawText("ó", key.x + (key.width / 2) + 10, key.y + 25, paint);

                else if (key.label.toString().equals("a") || key.label.toString().equals("A"))
                    canvas.drawText("á", key.x + (key.width / 2) + 10, key.y + 25, paint);

                else
                {}
            }
        }
    }
*/
}
