import java.math.BigDecimal;
import java.util.ArrayList;

//import java.io.IOException;
//import java.util.ArrayList;
//
public class Order1{
		ArrayList<OrderItem1> orderitems = new ArrayList<OrderItem1>();
		boolean isDinein;
		static final BigDecimal taxrate = new BigDecimal("0.0825");
		BigDecimal couponbd;
		BigDecimal pointappliedbd;
		
		
		Order1(){
			this.couponbd=new BigDecimal("0.00");
			this.pointappliedbd=new BigDecimal("0.00");
		}
	
		public BigDecimal getSubtotal(){
			BigDecimal subtotal=new BigDecimal("0.00");
			for (OrderItem1 oi: orderitems){
				subtotal =subtotal.add(oi.Unitprice.multiply(oi.quantity));
				
			}
			subtotal = subtotal.subtract(pointappliedbd.divide(new BigDecimal("100")));		
			subtotal =subtotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return subtotal;
		}
		
			
		public BigDecimal getDiscount(){
			BigDecimal discount1 = getSubtotal().multiply(couponbd);			
			discount1 = discount1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return discount1;
		}
		
		public BigDecimal getBeforeTax(){
			BigDecimal beforetax=getSubtotal().subtract(getDiscount());
			beforetax=beforetax.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return beforetax;
		}
		
		public BigDecimal getTax(){
			BigDecimal tax=	getBeforeTax().multiply(taxrate);
			tax = tax.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return tax;
		}
		
		public BigDecimal getTotal(){
			BigDecimal total =getSubtotal().subtract(getDiscount());
			total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return  total.add(getTax());
		}

	}