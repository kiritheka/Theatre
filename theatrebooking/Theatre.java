package theatrebooking;

import java.util.HashMap;

public class Theatre {

	String theatreName;
	int capacity;
	HashMap<Seater, Integer> seatTypeAndCapacity;

	Theatre(String theatreName, int capacity, HashMap<Seater, Integer> seatTypeAndCapacity) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.seatTypeAndCapacity = seatTypeAndCapacity;
	}
}
