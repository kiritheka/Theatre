package theatrebooking;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Shows {

	String showName;
	String showTiming;
	Movie movie;

	Shows(String showName, String showTiming, Movie movie) {
		this.showName = showName;
		this.showTiming = showTiming;
		this.movie = movie;

	}
	
	public LinkedHashMap<String, Shows> getListOfShowsForMovie(String movieSelected,Theatre theatre) {
		LinkedHashMap<String, Shows> listOfShowForMovie = new LinkedHashMap<String, Shows>();
		if (movieSelected != null) {
			for (Entry<String, Shows> entry : theatre.show.entrySet()) {
				if (entry.getValue().movie.movieName.equalsIgnoreCase(movieSelected))
					listOfShowForMovie.put(entry.getKey(), entry.getValue());
			}
			return listOfShowForMovie;
		} else {
			System.out.println("No such movie is available");
		}
		return null;
	}
	
}
