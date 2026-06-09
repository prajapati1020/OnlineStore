package online.store.model;

import java.util.List;

public class CheckoutRequest {
    private String firstName;
    private String LastName;
    private String email;
    private String shippingAddress;
    private List<ProductInfo> products;

    private String creditCard;

    public class ProductInfo{

        private long productInfo;
        private long quantity;

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }



        public long getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(long productInfo) {
            this.productInfo = productInfo;
        }

        public ProductInfo(long productInfo, long quantity) {
            this.productInfo = productInfo;
            this.quantity = quantity;
        }

        public ProductInfo() {

        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public CheckoutRequest(String firstName, String lastName, String email, String shippingAddress, List<ProductInfo> products, String creditCard) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.creditCard = creditCard;
    }

    public CheckoutRequest() {

    }
}
