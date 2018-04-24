package theatrebooking;

public class Movie {

	public enum MoviesAvailable {
		MOVIE1, MOVIE2, MOVIE3, MOVIE4
	}

	MoviesAvailable movieName;
	double movieRating;
	String movieLanguage;

	public Movie(MoviesAvailable movieName, double movieRating, String movieLanguage) {
		this.movieName = movieName;
		this.movieRating = movieRating;
		this.movieLanguage = movieLanguage;
	}

}
