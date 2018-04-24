package theatrebooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import theatrebooking.Movie.MoviesAvailable;

public class Theatre {

	public enum SeatingType {
		silver, gold, diamond, platinum
	}

	public enum ShowAndTiming {
		morningshow("09:00AM"), noonshow("12.00PM"), eveningshow("05:00PM"), nightshow("11:00PM");
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
	LinkedHashMap<ShowAndTiming, String> listOfShowForMovie = new LinkedHashMap<ShowAndTiming, String>();

	public LinkedHashMap<ShowAndTiming, Integer> bookTicketForShow(ShowAndTiming showSelection, SeatingType seaterSelection) {
		if (showAndTicket.get(showSelection) == null) {
			showAndTicket.put(showSelection, 1);
			return showAndTicket;
		} else {
			int currentBooking = showAndTicket.get(showSelection);
			if (currentBooking < capacity) {
				currentBooking++;
				showAndTicket.put(showSelection, currentBooking);
				return showAndTicket;
			}
		}

		return null;
	}

	public ArrayList<MoviesAvailable> getListOfMoviesRunninginTheatre() {
		ArrayList<MoviesAvailable> ListOfMoviesinTheatre = new ArrayList<MoviesAvailable>();
		for (ShowAndTiming showName : showTimingAndMovie.keySet()) {
			ListOfMoviesinTheatre.add(showTimingAndMovie.get(showName));
		}

		return ListOfMoviesinTheatre;
	}

	public LinkedHashMap<ShowAndTiming, String> getListOfShowsForMovie(String movieSelected) {

		boolean flag = false;
		for (MoviesAvailable iterable_element : MoviesAvailable.values()) {
			if (iterable_element.name().equals(movieSelected.toUpperCase()))
				flag = true;
		}
		if (flag == true) {
			MoviesAvailable movie = Movie.MoviesAvailable.valueOf(movieSelected.toUpperCase());
			for (Entry<ShowAndTiming, MoviesAvailable> entry : showTimingAndMovie.entrySet()) {
				if (entry.getValue().equals(movie))
					listOfShowForMovie.put(entry.getKey(), entry.getKey().showTiming);
			}

			return listOfShowForMovie;
		} else if (flag != true)
			LOGGER.warning("No such movie is available");

		return null;
	}

	public LinkedHashMap<ShowAndTiming, Integer> getTotalRevenueForShow(ShowAndTiming showSelection,
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
