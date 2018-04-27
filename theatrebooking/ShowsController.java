package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShowsController {

	MovieController movieController = new MovieController();
	TheatreController theatreController = new TheatreController();
	LinkedHashMap<Shows, Integer> showAndTicketDetails = new LinkedHashMap<Shows, Integer>();

	Shows morningfun = new Shows("morning", "9.30AM", movieController.bigHero, theatreController.fun);
	Shows morningcity = new Shows("morning", "9.00AM", movieController.happy, theatreController.city);
	Shows noonfun = new Shows("noon", "12.30PM", movieController.happy, theatreController.fun);
	Shows nooncity = new Shows("noon", "12.00PM", movieController.aram, theatreController.city);
	Shows eveningfun = new Shows("evening", "5.00PM", movieController.dangal, theatreController.fun);
	Shows eveningcity = new Shows("evening", "5.00PM", movieController.bigHero, theatreController.city);
	Shows night = new Shows("night", "8.00PM", movieController.dangal, theatreController.city);

	public ArrayList<Shows> getlistOfShows() {
		ArrayList<Shows> listOfShows = new ArrayList<Shows>();
		listOfShows.add(morningfun);
		listOfShows.add(morningcity);
		listOfShows.add(noonfun);
		listOfShows.add(nooncity);
		listOfShows.add(eveningfun);
		listOfShows.add(eveningcity);
		listOfShows.add(night);

		return listOfShows;
	}

	public ArrayList<Shows> getlistOfShowsForMovie(String movieSelected) {
		ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
		for (Shows show : getlistOfShows()) {
			if (movieSelected != null) {
				if (show.movie.movieName.equalsIgnoreCase(movieSelected))
					listOfShowForMovie.add(show);
			} else {
				System.out.format("%s movie is not available now %n",movieSelected);
			}
		}
		return listOfShowForMovie;
	}
}
