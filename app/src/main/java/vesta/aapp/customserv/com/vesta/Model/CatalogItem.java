package vesta.aapp.customserv.com.vesta.Model;

import android.graphics.drawable.Drawable;

public class CatalogItem {

    private int mImageResourse;
    private String mText1;

    public CatalogItem(int ImageResourse, String text1) {
        mImageResourse = ImageResourse;
        mText1 = text1;
    }

    public int getImageResourse() {
        return mImageResourse;
    }

    public String getText1() {
        return mText1;
    }

}
