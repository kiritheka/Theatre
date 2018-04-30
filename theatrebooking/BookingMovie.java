package theatrebooking;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingMovie {
	public static void main(String args[]) {
		TheatreController theatreController = new TheatreController();
		MovieController movieController = new MovieController();
		ShowController showController = new ShowController();
		TicketController ticketController = new TicketController();
		CSVReport csvReport = new CSVReport();
		PDFReport pdfReport = new PDFReport();
		Scanner scan = new Scanner(System.in);
		String userAction = "";
		String bookingAction = "";
		String movieSelected = "";
		String theatreSelection = "";
		String showSelection = "";
		String seaterSelected = "";
		Show showSelected = null;

		ArrayList<Show> listOfShowsForMovie = new ArrayList<Show>();
		boolean confirmation;

		while (!userAction.equalsIgnoreCase("exit")) {
			System.out.println("Do you wish to IssueTicket or AboutRevenue or exit?");
			userAction = scan.nextLine();

			if (userAction.equalsIgnoreCase("issueticket")) {
				System.out.println("Do you wish to book based on Movies or Theatre");
				bookingAction = scan.nextLine();

				if (bookingAction.equalsIgnoreCase("movies")) {
					System.out.println("Available movies are ");
					for (Movie movie : movieController.getlistOfMovies()) {
						System.out.format("%s --- %s %n", movie.name, movie.language);
					}
					System.out.println("Select a movie name to know theatre and shows..");
					movieSelected = scan.nextLine();

					boolean movieCheck = movieController.checkForMovie(movieSelected);
					if (movieCheck != false) {
						ArrayList<Show> showForMovie = showController.getlistOfShowsForMovie(movieSelected);
						if (!showForMovie.isEmpty()) {
							listOfShowsForMovie.addAll(showForMovie);
							for (Show show : listOfShowsForMovie) {
								System.out.format("%s --- %s --- %s %n", show.theatre.name, show.name, show.timing);
							}
							listOfShowsForMovie.clear();
						}
					}
				} else if (bookingAction.equalsIgnoreCase("theatre")) {
					System.out.println("List of Theatres available..");
					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						System.out.format("%s %n", theatre.name);
					}
					System.out.println("Choose a theatre to know more");
					theatreSelection = scan.nextLine();
					Theatre selectedTheatre = null;
					for (Theatre theatre : theatreController.getlistOfTheatre()) {
						if (theatre.name.equalsIgnoreCase(theatreSelection)) {
							selectedTheatre = theatre;
							break;
						}
					}
					if (selectedTheatre != null) {
						System.out.format("Available shows in  %s  are %n", selectedTheatre.name);
						for (Show show : showController.getlistOfShows()) {
							if (show.theatre.name.equalsIgnoreCase(theatreSelection)) {
								System.out.format("%s --- %s --- %s %n", show.name, show.timing, show.movie.name);
							}
						}
						System.out.println("Choose a showName you wish to see");
						showSelection = scan.nextLine();
						for (Show show : showController.getlistOfShows()) {
							if (show.name.equalsIgnoreCase(showSelection)) {
								showSelected = show;
								break;
							}
						}

						System.out.println("Available seaterTypes in " + showSelected.theatre.name + " are ");
						for (Seater entry : showSelected.theatre.seatAndCapacity.keySet()) {
							System.out.format("%s ---Rs. %d %n", entry.name, entry.price);
						}
						System.out.println("Choose a seaterType you wish ");
						seaterSelected = scan.nextLine();

						System.out.println("Choose a Number of tickets to book ");
						int numberOfTicket = scan.nextInt();

						/* checking for ticket successfully booked or not */
						confirmation = showSelected.bookTicket(seaterSelected, numberOfTicket);
						if (confirmation != false) {
							ticketController.generateTicket(showSelected, seaterSelected);
						} else {
							System.out.println("Sorry..No space available.. ");
						}
					} else {
						System.out.println("No such theatre present..");
					}
				}
			} else if (userAction.equalsIgnoreCase("AboutRevenue")) {
				ArrayList<Show> listOfShow = showController.getlistOfShows();
				csvReport.generateRevenueReport(listOfShow);
				pdfReport.generateRevenueReport(listOfShow);

			}
		}
	}
}
