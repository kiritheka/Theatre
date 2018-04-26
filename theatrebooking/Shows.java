package theatrebooking;

import java.util.LinkedHashMap;

public class Shows {

	String showName;
	String showTiming;
	Movie movie;
	Theatre theatre;

	Shows(String showName, String showTiming, Movie movie, Theatre theatre) {
		this.showName = showName;
		this.showTiming = showTiming;
		this.movie = movie;
		this.theatre = theatre;

	}

	int totalcapacity = 0;
	LinkedHashMap<Seater, Integer> seaterAndTicket = new LinkedHashMap<Seater, Integer>();

	public LinkedHashMap<Seater, Integer> bookTicket(Seater userSeater) {

		if (theatre.seatAndCapacity.containsKey(userSeater)) {
			if (totalcapacity < theatre.capacity) {
				if (seaterAndTicket.get(userSeater) == null) {
					seaterAndTicket.put(userSeater, 1);
					totalcapacity++;

					return seaterAndTicket;
				} else {
					if (seaterAndTicket.get(userSeater) < theatre.seatAndCapacity.get(userSeater)) {
						seaterAndTicket.put(userSeater, seaterAndTicket.get(userSeater) + 1);
						totalcapacity++;

						return seaterAndTicket;
					} else {
						System.out.println("No space available in this type");
					}
				}
			}
		}
		return null;
	}
}
