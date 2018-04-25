package theatrebooking;

import java.util.LinkedHashMap;

public class Seater {

	String seaterName;
	int capacity;
	int price;

	Seater(String seaterName, int capacity, int price) {
		this.seaterName = seaterName;
		this.capacity = capacity;
		this.price = price;

	}
	
	public LinkedHashMap<Shows, Integer> bookTicketForShow(Shows userShow) {
		LinkedHashMap<Shows, Integer> showAndTicketDetails = new LinkedHashMap<Shows, Integer>();
		
		if (showAndTicketDetails.get(userShow) == null) {
			showAndTicketDetails.put(userShow, 1);
			return showAndTicketDetails;
		} else {
			int currentBooking = showAndTicketDetails.get(userShow);
			if (currentBooking < capacity) {
				currentBooking++;
				showAndTicketDetails.put(userShow, currentBooking);
				return showAndTicketDetails;
			} else {
				System.out.println("there is no space available in this SeaterType");
			}
		}
		return null;
	}
}
