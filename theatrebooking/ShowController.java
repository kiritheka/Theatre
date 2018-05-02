package theatrebooking;

import java.util.ArrayList;

public class ShowController {

	MovieController movieController = new MovieController();
	TheatreController theatreController = new TheatreController();

	Show morningfun = new Show("morning", "9.30AM", movieController.bigHero, theatreController.fun);
	Show morningcity = new Show("morning", "9.00AM", movieController.happy, theatreController.city);
	Show noonfun = new Show("noon", "12.30PM", movieController.happy, theatreController.fun);
	Show nooncity = new Show("noon", "12.00PM", movieController.aram, theatreController.city);
	Show eveningfun = new Show("evening", "5.00PM", movieController.dangal, theatreController.fun);
	Show eveningcity = new Show("evening", "5.00PM", movieController.bigHero, theatreController.city);
	Show night = new Show("night", "8.00PM", movieController.dangal, theatreController.city);

	public ArrayList<Show> getlistOfShows() {
		ArrayList<Show> listOfShows = new ArrayList<Show>();
		listOfShows.add(morningfun);
		listOfShows.add(morningcity);
		listOfShows.add(noonfun);
		listOfShows.add(nooncity);
		listOfShows.add(eveningfun);
		listOfShows.add(eveningcity);
		listOfShows.add(night);

		return listOfShows;
	}

	public ArrayList<Show> getlistOfShowsForMovie(String movieSelected) {
		ArrayList<Show> listOfShowsForMovie = new ArrayList<Show>();
		for (Show show : getlistOfShows()) {
			if (movieSelected != null) {
				if (show.movie.name.equalsIgnoreCase(movieSelected))
					listOfShowsForMovie.add(show);
			} else {
				System.out.format("%s movie is not available now %n", movieSelected);
			}
		}
		return listOfShowsForMovie;
	}
}
