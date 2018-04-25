package theatrebooking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Theatre {

	String theatreName;
	int capacity;
	HashMap<String, Seater> seatingType;
	HashMap<String, Shows> show;

	Theatre(String theatreName, int capacity, HashMap<String, Seater> seatingType, HashMap<String, Shows> show) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.seatingType = seatingType;
		this.show = show;
	}

	LinkedHashMap<Shows, Integer> showAndRevenue = new LinkedHashMap<Shows, Integer>();

	public LinkedHashMap<Shows, Integer> getTotalRevenueForShow(Shows userShow, Seater userSeater) {
		if (showAndRevenue.get(userShow) == null) {
			showAndRevenue.put(userShow, userSeater.price);
			return showAndRevenue;
		} else {
			int price = showAndRevenue.get(userShow);
			showAndRevenue.put(userShow, price + userSeater.price);
			return showAndRevenue;
		}
	}
}
