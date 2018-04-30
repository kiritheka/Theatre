package theatrebooking;

import java.util.HashMap;

public class Theatre {

	String name;
	int capacity;
	HashMap<Seater, Integer> seatAndCapacity;

	Theatre(String name, int capacity, HashMap<Seater, Integer> seatAndCapacity) {
		this.name = name;
		this.capacity = capacity;
		this.seatAndCapacity = seatAndCapacity;
	}
}
