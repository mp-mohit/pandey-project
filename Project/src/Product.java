public class Product {


//    ID|NAME|COLOUR|GENDER_RECOMMENDATION|SIZE|PRICE|RATING|AVAILABILITY
//    ADC14536258MP|Adidas Men Purple Slim Fit Aeroready|Purple|M|M|1199.00|3.8|Y
    private Long id;
    private String productId;
    private String name;
    private String color;
    private String gender;
    private String size;
    private double price;
    private int rating;
    private boolean availability;
    private String genderRecommendation;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getGenderRecommendation() {
        return genderRecommendation;
    }

    public void setGenderRecommendation(String genderRecommendation) {
        this.genderRecommendation = genderRecommendation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Product(Long id, String productId, String name, String color, String gender, String size, double price, int rating, boolean availability, String genderRecommendation) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.color = color;
        this.gender = gender;
        this.size = size;
        this.price = price;
        this.rating = rating;
        this.availability = availability;
        this.genderRecommendation = genderRecommendation;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", gender='" + gender + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", availability=" + availability +
                ", genderRecommendation='" + genderRecommendation + '\'' +
                '}';
    }
}