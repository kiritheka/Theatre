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
		Seater userSeater = null;

		ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
		ArrayList<Ticket> listOfTickets = new ArrayList<Ticket>();
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
						System.out.println(movie.movieName + " " + movie.movieLanguage);
					}
					System.out.println("Select a movie name to know theatre and shows..");
					movieSelected = scan.nextLine();

					String movieCheck = movieController.checkForMovie(movieSelected);
					ArrayList<Shows> showForMovie = showsController.getlistOfShowsForMovie(movieCheck);
					if (showForMovie != null) {
						listOfShowForMovie.addAll(showForMovie);
						for (Shows shows : listOfShowForMovie) {
							System.out.println(
									shows.theatre.theatreName + "---" + shows.showName + "--" + shows.showTiming);
						}
						listOfShowForMovie.clear();
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

					System.out.println("Available shows in " + selectedTheatre.theatreName + " are ");
					for (Shows entry : showsController.getlistOfShows()) {
						if (entry.theatre.theatreName.equalsIgnoreCase(theatreSelection)) {
							System.out.println(
									entry.showName + " -- " + entry.showTiming + " -- " + entry.movie.movieName);
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
					for (Seater entry : userShow.theatre.seatAndCapacity.keySet()) {
						System.out.println(entry.seaterName + " -- Rs." + entry.price);
					}
					System.out.println("Choose a seaterType you wish ");
					seaterSelection = scan.nextLine();

					for (Seater seater : userShow.theatre.seatAndCapacity.keySet()) {
						if (seater.seaterName.name().equalsIgnoreCase(seaterSelection)) {
							userSeater = seater;
							break;
						}
					}
					userShow.bookTicket(userSeater);
					LinkedHashMap<Shows, Integer> userticket = new LinkedHashMap<Shows, Integer>();
					userticket.put(userShow, userShow.totalcapacity);
					/* checking for ticket successfully booked or not */
					Ticket ticketGenerated = null;
					if (userticket != null) {
						for (Entry<Shows, Integer> ticket : userticket.entrySet()) {
							for (Entry<Seater, Integer> seater : ticket.getKey().theatre.seatAndCapacity.entrySet()) {
								ticketGenerated = new Ticket(ticket.getValue(), ticket.getKey().theatre.theatreName,
										seater.getKey().seaterName, seater.getKey().price, ticket.getKey().showName,
										ticket.getKey().showTiming, ticket.getKey().movie.movieName);
								listOfTickets.add(ticketGenerated);
							}
						}
						System.out.println("Your Ticket Number is " + ticketGenerated.ticketId + " for "
								+ ticketGenerated.showName);
					}

				}

			} else if (userAction.equalsIgnoreCase("AboutRevenue")) {
				showAndRevenue.putAll(showsController.getRevenueForShow());
				revenue.generateRevenueReport(showAndRevenue);
				System.out.println("sucessfully generated report");
			}
		}
	}
}
