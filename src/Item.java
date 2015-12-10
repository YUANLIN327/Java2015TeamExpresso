	class Item {
		private String name;
		private Double price;
		String Category;
		
		Item(String name,String category,double price){
			this.name= name;
			this.price = price;
			this.Category=category;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}