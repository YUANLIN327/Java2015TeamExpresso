import java.util.ArrayList;

//import java.io.IOException;
//import java.util.ArrayList;
//
public class Order{
		ArrayList<OrderItem> orderitems = new ArrayList<OrderItem>();
		boolean isDinein;
		static final double taxRate=0.0825;
		double coupon =0.00;
		double giftvalue;
		

	
		public double getSubtotal(){
			double subtotal=0.0;
			for (OrderItem oi: orderitems){
				subtotal += oi.quantity*oi.unitprice;
			}
			return subtotal;			
		}
		
		public double getDiscount(){
			
			return Math.round(getSubtotal()*coupon*100.00)/100.00;
		}
		
		public double getTax(){
			return Math.round(taxRate*getSubtotal()*100.00)/100.0;
		}
		
		public double getTotal(){
			return  Math.round((getSubtotal()+getTax()-getDiscount())*100.0)/100.00;
		}

	}