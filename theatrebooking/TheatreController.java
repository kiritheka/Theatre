package theatrebooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import theatrebooking.Movie.MoviesAvailable;
import theatrebooking.Theatre.SeatingType;
import theatrebooking.Theatre.ShowAndTiming;

public class TheatreController {

	
	LinkedHashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovieForfun = new LinkedHashMap<ShowAndTiming, MoviesAvailable>() {{
				put(Theatre.ShowAndTiming.show1,Movie.MoviesAvailable.Movie1);
				put(Theatre.ShowAndTiming.show2,Movie.MoviesAvailable.Movie1);
				put(Theatre.ShowAndTiming.show3,Movie.MoviesAvailable.Movie2);

	}};
	LinkedHashMap<SeatingType, Integer> seatingTypeAndPriceForfun = new LinkedHashMap<SeatingType, Integer>() {{
		put(Theatre.SeatingType.level1,300);
		put(Theatre.SeatingType.level2,200);
		put(Theatre.SeatingType.level3,100);

	}};
	
	Theatre fun = new Theatre("Fun", 50, showTimingAndMovieForfun, seatingTypeAndPriceForfun);
	

	HashMap<ShowAndTiming, MoviesAvailable> showTimingAndMovieforcity = new HashMap<ShowAndTiming, MoviesAvailable>() {{
				put(Theatre.ShowAndTiming.show1,Movie.MoviesAvailable.Movie1);
				put(Theatre.ShowAndTiming.show2,Movie.MoviesAvailable.Movie2);
				put(Theatre.ShowAndTiming.show3,Movie.MoviesAvailable.Movie3);
				put(Theatre.ShowAndTiming.show4,Movie.MoviesAvailable.Movie4);


	}};
	HashMap<SeatingType, Integer> seatingTypeAndPriceforcity = new HashMap<SeatingType, Integer>() {{
		put(Theatre.SeatingType.level1,300);
		put(Theatre.SeatingType.level2,200);
		put(Theatre.SeatingType.level3,100);
		put(Theatre.SeatingType.special,500);

	}};
	
	Theatre city = new Theatre("City", 100, showTimingAndMovieforcity, seatingTypeAndPriceforcity);
	
	
	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}