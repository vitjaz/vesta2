package vesta.aapp.customserv.com.vesta.Model;

public class Products {

   private String name;
   private String product_id;
   private String imageRes;

    public Products() {

    }

    public Products(String name, String product_id, String imageRes) {
        this.name = name;
        this.product_id = product_id;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }
}
