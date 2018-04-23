package theatrebooking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import theatrebooking.Movie.MoviesAvailable;

public class Theatre {

	public enum SeatingType {
		level1, level2, level3, special
	}

	public enum ShowAndTiming {
		show1("09:00AM"), show2("12.00PM"), show3("05:00PM"), show4("11:00PM");
		private String showTiming;

		ShowAndTiming(String timing) {
			this.showTiming = timing;
		}

		public String getShowTiming() {
			return showTiming;
		}
	}

	String theatreName;
	int capacity;
	HashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovie = new HashMap<ShowAndTiming, MoviesAvailable>();
	HashMap<SeatingType, Integer> seatingTypeAndPrice = new HashMap<SeatingType, Integer>();

	Theatre(String theatreName, int capacity, HashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovie,
			HashMap<SeatingType, Integer> seatingTypeAndPrice) {
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.showTimingAndMovie = showTimingAndMovie;
		this.seatingTypeAndPrice = seatingTypeAndPrice;

	}

	Logger LOGGER = Logger.getLogger(Theatre.class.getName());
	LinkedHashMap<ShowAndTiming, Integer> showAndRevenue = new LinkedHashMap<ShowAndTiming, Integer>();
	LinkedHashMap<ShowAndTiming, Integer> showAndTicket = new LinkedHashMap<ShowAndTiming, Integer>();

	public LinkedHashMap<ShowAndTiming, Integer> getTicket(ShowAndTiming showSelection, SeatingType seaterSelection) {

		if (showAndTicket.get(showSelection) == null) {
			showAndTicket.put(showSelection, 1 /* + seaterSelection.toString() */);
			return showAndTicket;
		} else {
			int currentBooking = showAndTicket.get(showSelection);
			if (currentBooking <= capacity) {
				currentBooking++;
				showAndTicket.put(showSelection, currentBooking);
				return showAndTicket;
			}
		}
		return null;

	}

	public LinkedHashMap<ShowAndTiming, Integer> getTotalPriceForShow(ShowAndTiming showSelection,
			SeatingType seaterSelection) {

		if (showAndRevenue.get(showSelection) == null) {
			showAndRevenue.put(showSelection, seatingTypeAndPrice.get(seaterSelection));
			return showAndRevenue;
		} else {
			int price = showAndRevenue.get(showSelection);
			showAndRevenue.put(showSelection, price + seatingTypeAndPrice.get(seaterSelection));
			return showAndRevenue;
		}
	}
	

}
