package vesta.aapp.customserv.com.vesta.Model;

import android.graphics.drawable.Drawable;

public class CatalogItem {

    private String id;
    private int mImageResourse;
    private String mText1;

    public CatalogItem(int ImageResourse, String text1, String id) {
        mImageResourse = ImageResourse;
        mText1 = text1;
        this.id = id;
    }

    public int getImageResourse() {
        return mImageResourse;
    }

    public String getText1() {
        return mText1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
