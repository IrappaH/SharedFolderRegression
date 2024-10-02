package cocoro.enums;

public enum Year {
	Y2022("2022"),
	Y2021("2021"),
	Y2020("2020"),
	Y2019("2019");
	
	String number;
	
	Year(String number){
		this.number = number;
	}
	
	public String getValue() {
		return this.number;
	}
}
