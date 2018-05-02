package theatrebooking;

import java.util.LinkedHashMap;

public class Show {

	String name;
	String timing;
	Movie movie;
	Theatre theatre;

	public Show(String name, String timing, Movie movie, Theatre theatre) {
		this.name = name;
		this.timing = timing;
		this.movie = movie;
		this.theatre = theatre;
	}

	int totalSeatsBooked = 0;
	LinkedHashMap<Seater, Integer> seatAndFilledCount = new LinkedHashMap<Seater, Integer>();

	public boolean bookTicket(String seaterSelection, int numberOfTicket) {
		Seater userSeater = null;
		for (Seater seater : theatre.seatAndCapacity.keySet()) {
			if (seater.name.name().equalsIgnoreCase(seaterSelection)) {
				userSeater = seater;
				if (totalSeatsBooked < theatre.capacity)
					if (seatAndFilledCount.get(userSeater) == null) {
						seatAndFilledCount.put(userSeater, numberOfTicket);
						totalSeatsBooked = totalSeatsBooked + numberOfTicket;
						return true;
					} else {
						if (seatAndFilledCount.get(userSeater) < theatre.seatAndCapacity.get(userSeater)) {
							seatAndFilledCount.put(userSeater, seatAndFilledCount.get(userSeater) + numberOfTicket);
							totalSeatsBooked = totalSeatsBooked + numberOfTicket;
							return true;
						} else {
							System.out.format("No space available in %s type %n", seaterSelection);
							return false;
						}
					}
				break;
			}
		}
		return false;
	}
}
