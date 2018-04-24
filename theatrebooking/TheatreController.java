package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import theatrebooking.Movie.MoviesAvailable;
import theatrebooking.Theatre.SeatingType;
import theatrebooking.Theatre.ShowAndTiming;

public class TheatreController {

	LinkedHashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovieForfun = new LinkedHashMap<ShowAndTiming, MoviesAvailable>() {{
		put(Theatre.ShowAndTiming.morningshow,Movie.MoviesAvailable.MOVIE1);
		put(Theatre.ShowAndTiming.noonshow,Movie.MoviesAvailable.MOVIE1);
		put(Theatre.ShowAndTiming.eveningshow,Movie.MoviesAvailable.MOVIE2);

	}};
	LinkedHashMap<SeatingType, Integer> seatingTypeAndPriceForfun = new LinkedHashMap<SeatingType, Integer>() {{
		put(Theatre.SeatingType.silver,100);
		put(Theatre.SeatingType.gold,200);
		put(Theatre.SeatingType.diamond,300);

	}};

	Theatre fun = new Theatre("Fun", 3, showTimingAndMovieForfun, seatingTypeAndPriceForfun);


	LinkedHashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovieforcity = new LinkedHashMap<ShowAndTiming, MoviesAvailable>() {{
		put(Theatre.ShowAndTiming.morningshow,Movie.MoviesAvailable.MOVIE1);
		put(Theatre.ShowAndTiming.noonshow,Movie.MoviesAvailable.MOVIE2);
		put(Theatre.ShowAndTiming.eveningshow,Movie.MoviesAvailable.MOVIE3);
		put(Theatre.ShowAndTiming.nightshow,Movie.MoviesAvailable.MOVIE4);


	}};
	LinkedHashMap<SeatingType, Integer> seatingTypeAndPriceforcity = new LinkedHashMap<SeatingType, Integer>() {{
		put(Theatre.SeatingType.silver,100);
		put(Theatre.SeatingType.gold,200);
		put(Theatre.SeatingType.diamond,300);
		put(Theatre.SeatingType.platinum,500);

	}};

	Theatre city = new Theatre("City", 100, showTimingAndMovieforcity, seatingTypeAndPriceforcity);


	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}