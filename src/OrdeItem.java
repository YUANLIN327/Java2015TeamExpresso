	class OrderItem {
		String name;
		double unitprice;
		int quantity;
		OrderItem(){
			
		}
		OrderItem (String name, Double price){
			this.name= name;
			this.unitprice = price;
			this.quantity = 1;
		}
		
		
		public String toString(){
			
			return String.format("%-17s", name)+ String.format("%-10s", quantity) + 
					String.format("%7s", unitprice)+String.format("%10s", quantity*unitprice);
		}
	}