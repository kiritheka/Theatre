package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
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

		ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
		ArrayList<Ticket> listOfTickets = new ArrayList<Ticket>();
		LinkedHashMap<Shows, Integer> userticket = new LinkedHashMap<Shows, Integer>();
		LinkedHashMap<Seater, Integer> seaterAndTicket = new LinkedHashMap<Seater, Integer>();
		LinkedHashMap<Shows, Integer> showAndRevenue = new LinkedHashMap<Shows, Integer>();

		while (!userAction.equalsIgnoreCase("exit")) {
			System.out.println("Do you wish to IssueTicket or AboutRevenue or exit?");
			userAction = scan.nextLine();

			if (userAction.equalsIgnoreCase("issueticket")) {
				System.out.println("Do you wish to book based on Movies or Theatre");
				bookingAction = scan.nextLine();

				if (bookingAction.equalsIgnoreCase("movies")) {
					System.out.println("Available movies are ");
					for (Movie movie : movieController.getlistOfMovies()) {
						System.out.format("%s --- %s %n", movie.movieName, movie.movieLanguage);
					}
					System.out.println("Select a movie name to know theatre and shows..");
					movieSelected = scan.nextLine();

					String movieCheck = movieController.checkForMovie(movieSelected);
					if (movieCheck != null) {
						ArrayList<Shows> showForMovie = showsController.getlistOfShowsForMovie(movieCheck);
						if (!showForMovie.isEmpty()) {
							listOfShowForMovie.addAll(showForMovie);
							for (Shows shows : listOfShowForMovie) {
								System.out.format("%s --- %s --- %s %n", shows.theatre.theatreName, shows.showName,
										shows.showTiming);
							}
							listOfShowForMovie.clear();
						}
					}
				} else if (bookingAction.equalsIgnoreCase("theatre")) {
					System.out.println("List of Theatres available..");
					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						System.out.format("%s %n", theatre.theatreName);
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
					if (selectedTheatre != null) {
						System.out.format("Available shows in  %s  are %n", selectedTheatre.theatreName);
						for (Shows entry : showsController.getlistOfShows()) {
							if (entry.theatre.theatreName.equalsIgnoreCase(theatreSelection)) {
								System.out.format("%s --- %s --- %s %n", entry.showName, entry.showTiming,
										entry.movie.movieName);
							}
						}
						System.out.println("Choose a showName you wish to see");
						showSelection = scan.nextLine();
						for (Shows shows : showsController.getlistOfShows()) {
							if (shows.showName.equalsIgnoreCase(showSelection)) {
								userShow = shows;
								break;
							}
						}

						System.out.println("Available seaterTypes in " + userShow.theatre.theatreName + " are ");
						for (Seater entry : userShow.theatre.seatTypeAndCapacity.keySet()) {
							System.out.format("%s ---Rs. %d %n", entry.seaterName, entry.price);
						}
						System.out.println("Choose a seaterType you wish ");
						seaterSelection = scan.nextLine();

						seaterAndTicket = userShow.bookTicket(seaterSelection);
						if (seaterAndTicket != null)
							userticket.put(userShow, userShow.seatsBooked);
						/* checking for ticket successfully booked or not */
						Ticket ticketGenerated = null;
						if (userticket != null) {
							for (Entry<Shows, Integer> ticket : userticket.entrySet()) {
								for (Entry<Seater, Integer> seater : ticket.getKey().theatre.seatTypeAndCapacity
										.entrySet()) {
									ticketGenerated = new Ticket(ticket.getValue(), ticket.getKey().theatre.theatreName,
											seater.getKey().seaterName, seater.getKey().price, ticket.getKey().showName,
											ticket.getKey().showTiming, ticket.getKey().movie.movieName);
									listOfTickets.add(ticketGenerated);
								}
							}
							System.out.format("Your Ticket Number is %d for %s %n", ticketGenerated.ticketId,
									ticketGenerated.showName);
						}
					} else {
						System.out.println("No such theatre available..");
					}
				}
			} else if (userAction.equalsIgnoreCase("AboutRevenue")) {
				for (Shows show : showsController.getlistOfShows()) {
					showAndRevenue.put(show, show.getRevenueForShow());
				}
				revenue.generateRevenueReport(showAndRevenue);
			}
		}
	}
}
