package theatrebooking;

import java.util.ArrayList;

public class ShowsController {

	MovieController movieController = new MovieController();

	Shows morning = new Shows("morning", "9.30AM", movieController.bigHero);
	Shows morningfun = new Shows("morning", "9.00AM", movieController.happy);
	Shows noon = new Shows("noon", "12.30PM", movieController.aram);
	Shows evening = new Shows("evening", "5.00PM", movieController.happy);
	Shows night = new Shows("night", "8.00PM", movieController.dangal);

	public ArrayList<Shows> getlistOfShows() {
		ArrayList<Shows> listOfShows = new ArrayList<Shows>();
		listOfShows.add(morning);
		listOfShows.add(morningfun);
		listOfShows.add(noon);
		listOfShows.add(evening);
		listOfShows.add(night);
		
		return listOfShows;
	}

	public ArrayList<Shows> getlistOfShowsForMovie(String movieSelected) {
		ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
		for (Shows show : getlistOfShows()) {
			if (movieSelected != null) {
				if (show.movie.movieName.equalsIgnoreCase(movieSelected)) {
					listOfShowForMovie.add(show);
				}
			} else {
				System.out.println("No such movie is available");
			}
		}
		return listOfShowForMovie;
		
	}
}
