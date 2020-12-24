
package SaleManagementSystem;


public class PriceCalculate {
    private double tfprice;
    private int tfquantity;
    
    public PriceCalculate(double tfprice,int quantity) {
        this.tfprice = tfprice;
        this.tfquantity = quantity;
        
    }
    
    public double getTfPrice() {
        return tfprice;
    }
    
    public void setTfprice(double tfprice) {
        this.tfprice = tfprice;
    }
    
    public int getQuantity() {
        return tfquantity;
    } 
    
    public void setTfQuantity(int quantity) {
        this.tfquantity = quantity;
    }
    
    
    public double getCalculatePrice() {
        
        //Calculate price and add inside Price List
        double price = tfprice;
        double quantity =  tfquantity;
        double finalPrice = tfprice * tfquantity ;
        double priceCombine = 0;
        
        //Add Total Price
        double combine = finalPrice;
        priceCombine += combine;
        return priceCombine;
    }    
   
    
}
