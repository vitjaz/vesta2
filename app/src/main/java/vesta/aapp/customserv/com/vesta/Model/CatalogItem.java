package vesta.aapp.customserv.com.vesta.Model;

public class CatalogItem {

    private int mImageResource;
    private String mText1;

    public CatalogItem(int imageResource, String text1) {
        mImageResource = imageResource;
        mText1 = text1;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

}
