package code.usa.utils;

import java.util.Calendar;

import code.usa.property.Reading.File_Reading;

public class pick_the_date {
	
	
	private String departureDate=null;
	private String returnDate=null;
	
	public String getDepartureDate() {
		return departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	//Picks up current system for Departure DAte and +7 days for Return date Returns in format tue 13 Apr 2019
	public static pick_the_date getDates()
	{
		pick_the_date date=new pick_the_date();
		Calendar cal=Calendar.getInstance();
		
		String[] rawDate=cal.getTime().toString().split(" ");
		date.departureDate=rawDate[0]+" "+rawDate[1]+" "+rawDate[2]+" "+rawDate[5];
		cal.add(Calendar.DATE, Integer.parseInt(File_Reading.get("NoOfdays")));
		rawDate=cal.getTime().toString().split(" ");
		date.returnDate=rawDate[0]+" "+rawDate[1]+" "+rawDate[2]+" "+rawDate[5];
		return date;
	}

	
}
