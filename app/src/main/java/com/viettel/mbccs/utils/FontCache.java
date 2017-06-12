package com.viettel.mbccs.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

import java.util.HashMap;

public class FontCache {
    //
    private static LruCache<String, Typeface> mFontCache = new LruCache<>(12);

    public static Typeface getFont(String fontName, Context context) throws RuntimeException {
        Typeface typeface = mFontCache.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(),
                    "fonts/" + fontName);
            mFontCache.put(fontName, typeface);
        }
        return typeface;
    }
    // new add font
    private static HashMap<String, Typeface> sFontCache = new HashMap<>();

    private static Typeface getTypeface(String fontName, Context context) {
        Typeface typeface = sFontCache.get(fontName);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                return null;
            }
            sFontCache.put(fontName, typeface);
        }
        return typeface;
    }

    public static Typeface selectTypeface(Context context, int textStyle) {
        switch (textStyle) {
            case Typeface.BOLD:
                return FontCache.getTypeface("fonts/averta_bold.otf", context);
            case Typeface.ITALIC:
                return FontCache.getTypeface("fonts/averta_regular_italic.otf", context);
            case Typeface.BOLD_ITALIC:
                return FontCache.getTypeface("fonts/averta_bold_italic.otf", context);
            case Typeface.NORMAL:
            default:
                return FontCache.getTypeface("fonts/averta.otf", context);
        }
    }

}
