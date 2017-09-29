package ferjogames.coralsystemdeck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.lang.ref.WeakReference;

/**
 * Created by Fer on 29/09/2017.
 */

public class Android implements Platform {

    private WeakReference<Context> contextRef;
    private Handler handler;

    public Android(Context context) {
        this.contextRef = new WeakReference<>(context);
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getTextInput(final Input.TextInputListener listener, final String title, final String text, final String hint) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                final Context context = contextRef.get();
                if (context != null) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle(title);
                    final EditText input = new EditText(context);
                    input.setHint(hint);
                    input.setText(text);
                    input.setSingleLine();
                    input.setSelection(text.length());
                    alert.setView(input);
                    alert.setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    listener.input(input.getText().toString());
                                }
                            });
                        }
                    });
                    alert.setNegativeButton(context.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    listener.canceled();
                                }
                            });
                        }
                    });
                    alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface arg0) {
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    listener.canceled();
                                }
                            });
                        }
                    });
                    AlertDialog dialog = alert.show();
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
    }

    private void hideSoftKeyboard(Context context, EditText input) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }
}
