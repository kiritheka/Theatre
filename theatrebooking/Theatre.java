package theatrebooking;

import java.util.ArrayList;

public class Theatre {

	String theatreName;
	int capacity;
	ArrayList<Seater> seatingType;
	ArrayList<Shows> show;

	Theatre(String theatreName, int capacity, ArrayList<Seater> seatingType, ArrayList<Shows> show) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.seatingType = seatingType;
		this.show = show;
	}
}
