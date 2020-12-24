package SaleManagementSystem;


public class MinusPrice {
    
    private double price;
    private double priceText;
    
    public MinusPrice(double price, double priceText) {
        this.price = price;
        this.priceText = priceText;
        
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
     public double getMinusTotalPrice() {
        
       double priceForList = price;
       double priceForText = priceText ;
       double finalMinusPrice = priceForText - priceForList;
       
       return finalMinusPrice;
    
}
}
