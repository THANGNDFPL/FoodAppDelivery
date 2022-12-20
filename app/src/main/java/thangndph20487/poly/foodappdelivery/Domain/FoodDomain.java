package thangndph20487.poly.foodappdelivery.Domain;

public class FoodDomain {
    private String title;
    private String pic;
    private String desc;
    private double fee;
    private int numberInCart;

    public FoodDomain(String title, String pic, String desc, double fee) {
        this.title = title;
        this.pic = pic;
        this.desc = desc;
        this.fee = fee;
    }

    public FoodDomain(String title, String pic, String desc, double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.desc = desc;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
