package com.hd.gallery;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Haridev on 21-06-2017.
 */

public class message extends Toast {

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public message(Context context, String s) {
        super(context);
        Toast.makeText(context,s,LENGTH_SHORT);

    }
}
