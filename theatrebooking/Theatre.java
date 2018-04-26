package theatrebooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Theatre {

	String theatreName;
	int capacity;
	HashMap<Seater, Integer> seatAndCapacity;
	ArrayList<Shows> show;

	Theatre(String theatreName, int capacity, HashMap<Seater, Integer> seatAndCapacity, ArrayList<Shows> show) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.seatAndCapacity = seatAndCapacity;
		this.show = show;
	}

	LinkedHashMap<Shows, Integer> showAndTicketDetails = new LinkedHashMap<Shows, Integer>();
	LinkedHashMap<Shows, Integer> showAndPrice = new LinkedHashMap<Shows, Integer>();
	HashMap<Seater, Integer> seaterAndCount;

	public LinkedHashMap<Shows, Integer> bookTicket(Shows userShow, Seater userSeater) {

		if (showAndTicketDetails.get(userShow) == null) {
			showAndTicketDetails.put(userShow, 1);
			seaterAndCount.put(userSeater, 1);
			return showAndTicketDetails;
		} else {
			int currentBooking = showAndTicketDetails.get(userShow);
			if (currentBooking < seatAndCapacity.get(userSeater)) {
				currentBooking++;
				showAndTicketDetails.put(userShow, currentBooking);
				if (seaterAndCount.get(userSeater) == null) {
					seaterAndCount.put(userSeater, 1);
				} else {
					seaterAndCount.put(userSeater, seaterAndCount.get(userSeater) + 1);
				}

				return showAndTicketDetails;
			} else {
				System.out.println("No more space in this seater type");
			}
		}
		return null;
	}

	public LinkedHashMap<Shows, Integer> getTotalPriceForShow(Shows userShow) {
		int price = 0;
		for (Entry<Seater, Integer> shows : seaterAndCount.entrySet()) {
			price = price + shows.getKey().price * shows.getValue();
		}
		showAndPrice.put(userShow, price);
		return showAndPrice;
	}
}
