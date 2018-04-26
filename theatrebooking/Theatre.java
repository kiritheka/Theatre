package theatrebooking;

import java.util.HashMap;

public class Theatre {

	String theatreName;
	int capacity;
	HashMap<Seater, Integer> seatAndCapacity;

	Theatre(String theatreName, int capacity, HashMap<Seater, Integer> seatAndCapacity) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.seatAndCapacity = seatAndCapacity;
	}
}
