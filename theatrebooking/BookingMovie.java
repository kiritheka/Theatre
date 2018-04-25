package theatrebooking;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.Scanner;

public class BookingMovie {
	public static void main(String args[]) {
		TheatreController theatreController = new TheatreController();
		MovieController movieController = new MovieController();
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
		LinkedHashMap<String, Shows> listOfShowForMovie = new LinkedHashMap<String, Shows>();
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
						String movieCheck=movieController.checkForMovie(movieSelected);
						for (Shows shows : theatre.show.values()) {
						LinkedHashMap<String, Shows> showForMovie=shows.getListOfShowsForMovie(movieCheck,theatre);
						if (showForMovie != null) {
							listOfShowForMovie.putAll(showForMovie);
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
				/*	System.out.println("Available Movies in " + selectedTheatre.theatreName + " are ");
					for (Movie movie : selectedTheatre.getListOfMoviesRunninginTheatre()) {
						System.out.println(movie.movieName + " -- " + movie.movieLanguage);
					}*/

					System.out.println("Available shows in " + selectedTheatre.theatreName + " are ");
					for (Entry<String, Shows> entry : selectedTheatre.show.entrySet()) {
						System.out.println(entry.getValue().showName + " -- " + entry.getValue().showTiming + " -- "
								+ entry.getValue().movie.movieName);
					}
					System.out.println("Choose a showName you wish to see");
					showSelection = scan.nextLine();

					for (Entry<String, Shows> element : selectedTheatre.show.entrySet()) {
						if (element.getValue().showName.equalsIgnoreCase(showSelection)) {
							userShow = element.getValue();
							break;
						}
					}

					System.out.println("Available seaterTypes in " + selectedTheatre.theatreName + " are ");
					for (Entry<String, Seater> entry : selectedTheatre.seatingType.entrySet()) {
						System.out.println(entry.getValue().seaterName + " -- Rs." + entry.getValue().price);
					}
					System.out.println("Choose a seaterType you wish ");
					seaterSelection = scan.nextLine();

					for (Entry<String, Seater> element : selectedTheatre.seatingType.entrySet()) {
						if (element.getValue().seaterName.equalsIgnoreCase(seaterSelection)) {
							userSeater = element.getValue();
							break;
						}
					}

					LinkedHashMap<Shows, Integer> ticket = userSeater.bookTicketForShow(userShow);
					/*checking for ticket successfully booked or not*/
					if (ticket != null) {
						Ticket userTicket = new Ticket(ticket.get(userShow), selectedTheatre.theatreName,
								userSeater.seaterName, userSeater.price, userShow.showName, userShow.showTiming,
								userShow.movie.movieName);
						System.out.println("Your Ticket Number is " + userTicket.ticketId + " for " + userTicket.showName);
						selectedTheatre.getTotalRevenueForShow(userShow, userSeater);
					}else {
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