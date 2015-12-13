import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

	class OrderItem1 {
		String name;
		BigDecimal Unitprice;
		BigDecimal quantity;
		OrderItem1(){
			
		}
		OrderItem1 (String name, BigDecimal price){
			this.name= name;
			this.Unitprice = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);			
			this.quantity = new BigDecimal("1");
		}
		OrderItem1 (String name, String price, String quantity){
			this.name= name;
			this.Unitprice = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			this.quantity = new BigDecimal(quantity);
		}
		
		public String toString(){
			NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
			return String.format("%-17s", name)+ String.format("%-10s", quantity) + 
					String.format("%7s", nf.format(Unitprice))+String.format("%10s", nf.format(Unitprice.multiply(quantity)));
		}
	
	}