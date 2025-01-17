package cocoro.enums;

public enum Month {
	JANUARY("01"),
	FEBRUARY("02"),
	MARCH("03"),
	APRIL("04"),
	MAY("05"),
	JUNE("06"),
	JULY("07"),
	AUGUST("08"),
	SEPTEMBER("09"),
	OCTOBER("10"),
	NOVEMBER("11"),
	DECEMBER("12");
	
	String number;
	
	Month(String number){
		this.number = number;
	}
	
	public String getValue() {
		return this.number;
	}

}
