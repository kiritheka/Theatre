package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.Scanner;

public class BookingMovie {
	public static void main(String args[]) {
		TheatreController theatreController = new TheatreController();
		MovieController movieController = new MovieController();
		ShowsController showsController = new ShowsController();
		RevenueGeneration revenue = new RevenueGeneration();
		Scanner scan = new Scanner(System.in);
		String userAction = "";
		String bookingAction = "";
		String movieSelected = "";
		String theatreSelection = "";
		String showSelection = "";
		String seaterSelection = "";
		Shows userShow = null;
		Seater userSeater = null;

		Logger LOGGER = Logger.getLogger(BookingMovie.class.getName());
		ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
		while (!userAction.equalsIgnoreCase("exit")) {
			System.out.println("Do you wish to IssueTicket or AboutRevenue or exit?");
			userAction = scan.nextLine();

			if (userAction.equalsIgnoreCase("issueticket")) {
				System.out.println("Do you wish to book based on Movies or Theatre");
				bookingAction = scan.nextLine();

				if (bookingAction.equalsIgnoreCase("movies")) {
					System.out.println("Available movies are ");
					for (Movie movie : movieController.getlistOfMovies()) {
						System.out.println(movie.movieName + " " + movie.movieLanguage);
					}
					System.out.println("Select a movie name to know theatre and shows..");
					movieSelected = scan.nextLine();

					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						String movieCheck = movieController.checkForMovie(movieSelected);
						for (Shows shows : showsController.getlistOfShows()) {
							ArrayList<Shows> showForMovie = showsController.getlistOfShowsForMovie(movieCheck);
							if (showForMovie != null) {
								listOfShowForMovie.addAll(showForMovie);
								System.out.println("In Theatre " + theatre.theatreName);
								System.out.println(listOfShowForMovie);
								listOfShowForMovie.clear();
							}
						}

					}
				} else if (bookingAction.equalsIgnoreCase("theatre")) {
					System.out.println("List of Theatres available..");
					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						System.out.println(theatre.theatreName);
					}
					System.out.println("Choose a theatre to know more");
					theatreSelection = scan.nextLine();
					Theatre selectedTheatre = null;

					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						if (theatre.theatreName.equalsIgnoreCase(theatreSelection)) {
							selectedTheatre = theatre;
							break;
						}
					}
					/*
					 * System.out.println("Available Movies in " + selectedTheatre.theatreName +
					 * " are "); for (Movie movie :
					 * selectedTheatre.getListOfMoviesRunninginTheatre()) {
					 * System.out.println(movie.movieName + " -- " + movie.movieLanguage); }
					 */

					System.out.println("Available shows in " + selectedTheatre.theatreName + " are ");
					for (Shows entry : selectedTheatre.show) {
						System.out.println(entry.showName + " -- " + entry.showTiming + " -- " + entry.movie.movieName);
					}
					System.out.println("Choose a showName you wish to see");
					showSelection = scan.nextLine();

					for (Shows shows : selectedTheatre.show) {
						if (shows.showName.equalsIgnoreCase(showSelection)) {
							userShow = shows;
							break;
						}
					}

					System.out.println("Available seaterTypes in " + selectedTheatre.theatreName + " are ");
					for (Seater entry : selectedTheatre.seatingType) {
						System.out.println(entry.seaterName + " -- Rs." + entry.price);
					}
					System.out.println("Choose a seaterType you wish ");
					seaterSelection = scan.nextLine();

					for (Seater seater : selectedTheatre.seatingType) {
						if (seater.seaterName.equalsIgnoreCase(seaterSelection)) {
							userSeater=seater;
							break;
						}
					}

					LinkedHashMap<Shows, Integer> ticket = userSeater.bookTicketForShow(userShow);
					/* checking for ticket successfully booked or not */
					if (ticket != null) {
						Ticket userTicket = new Ticket(ticket.get(userShow), selectedTheatre.theatreName,
								userSeater.seaterName, userSeater.price, userShow.showName, userShow.showTiming,
								userShow.movie.movieName);
						System.out.println(
								"Your Ticket Number is " + userTicket.ticketId + " for " + userTicket.showName);
						revenue.getTotalRevenueForShow(userShow, userSeater);
					} else {
						LOGGER.warning("Your booking failed due to insufficient seats");
					}
				}
			} else if (userAction.equalsIgnoreCase("AboutRevenue")) {
				revenue.generateRevenueReport();
				System.out.println("sucessfully generated report");
			}
		}
	}
}