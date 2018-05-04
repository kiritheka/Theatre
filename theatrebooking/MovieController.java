package theatrebooking;

import java.util.ArrayList;

public class MovieController {

	Movie aram = new Movie("Aram", 4, "Tamil");
	Movie bigHero = new Movie("BigHero", 4.5, "English");
	Movie dangal = new Movie("Dangal", 4, "Hindi");
	Movie happy = new Movie("Happy", 3.4, "English");

	public ArrayList<Movie> getlistOfMovies() {
		ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
		listOfMovies.add(aram);
		listOfMovies.add(bigHero);
		listOfMovies.add(dangal);
		listOfMovies.add(happy);

		return listOfMovies;
	}

	public boolean checkForMovie(String movieSelected) {
		for (Movie movie : getlistOfMovies()) {
			if (movie.name.equalsIgnoreCase(movieSelected)) {
				return true;
			}
		}
		return false;
	}
}
