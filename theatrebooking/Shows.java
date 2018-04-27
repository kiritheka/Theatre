package theatrebooking;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Shows {

	String showName;
	String showTiming;
	Movie movie;
	Theatre theatre;

	public Shows(String showName, String showTiming, Movie movie, Theatre theatre) {
		this.showName = showName;
		this.showTiming = showTiming;
		this.movie = movie;
		this.theatre = theatre;
	}

	int seatsBooked = 0;
	LinkedHashMap<Seater, Integer> seaterAndTicket = new LinkedHashMap<Seater, Integer>();

	public LinkedHashMap<Seater, Integer> bookTicket(String seaterSelection) {
		Seater userSeater = null;
		for (Seater seater : theatre.seatTypeAndCapacity.keySet()) {
			if (seater.seaterName.name().equalsIgnoreCase(seaterSelection)) {
				userSeater = seater;
				if (seatsBooked < theatre.capacity)
					if (seaterAndTicket.get(userSeater) == null) {
						seaterAndTicket.put(userSeater, 1);
						seatsBooked++;
						return seaterAndTicket;
					} else {
						if (seaterAndTicket.get(userSeater) < theatre.seatTypeAndCapacity.get(userSeater)) {
							seaterAndTicket.put(userSeater, seaterAndTicket.get(userSeater) + 1);
							seatsBooked++;
							return seaterAndTicket;
						} else {
							System.out.format("No space available in %s type %n",seaterSelection);
							return null;
						}
					}
				break;
			}
		}
		return null;
	}

	public int getRevenueForShow() {
		int price = 0;
		for (Entry<Seater, Integer> entry : seaterAndTicket.entrySet()) {
			price = price + (entry.getValue() * entry.getKey().price);
		}
		return price;
	}
}
