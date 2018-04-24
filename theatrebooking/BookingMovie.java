package theatrebooking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.Scanner;

import theatrebooking.Movie.MoviesAvailable;
import theatrebooking.Theatre.SeatingType;
import theatrebooking.Theatre.ShowAndTiming;

public class BookingMovie {
	public static void main(String args[]) throws IOException {
		TheatreController theatreController = new TheatreController();

		Scanner scan = new Scanner(System.in);
		String userAction = "";
		String bookingAction = "";
		String movieSelection = "";
		String theatreSelection = "";
		String showSelection = "";
		String seaterSelection = "";
		ShowAndTiming showSelected;
		SeatingType seatSelected;
		LinkedHashMap<ShowAndTiming, String> listOfShowForMovie = new LinkedHashMap<ShowAndTiming, String>();
		Logger LOGGER = Logger.getLogger(BookingMovie.class.getName());

		while (!userAction.equals("exit")) {

			System.out.println("Do you wish to book or exit or admin?");
			userAction = scan.nextLine();
			userAction = userAction.toLowerCase();
			if (userAction.equals("book")) {

				System.out.println("Do you wish to book based on Movies or Theatre");
				bookingAction = scan.nextLine();
				bookingAction = bookingAction.toLowerCase();
				if (bookingAction.equals("movies")) {
					LOGGER.info("Available movies are " + Arrays.toString(Movie.MoviesAvailable.values()));
					movieSelection = scan.nextLine();

					for (int i = 0; i < theatreController.getlistOfTheatre().size(); i++) {
						LinkedHashMap<ShowAndTiming, String> showForMovie = theatreController.getlistOfTheatre().get(i)
								.getListOfShowsForMovie(movieSelection);
						if (showForMovie != null) {
							listOfShowForMovie.putAll(showForMovie);
							LOGGER.info("In Theatre " + theatreController.getlistOfTheatre().get(i).theatreName);
							System.out.println(listOfShowForMovie);
							listOfShowForMovie.clear();
						}
					}
				} else if (bookingAction.equals("theatre")) {
					System.out.println("List of Theatres available..");
					for (int i = 0; i < theatreController.getlistOfTheatre().size(); i++) {
						System.out.println(theatreController.getlistOfTheatre().get(i).theatreName);
					}
					System.out.println("Choose a theatre to know more");
					theatreSelection = scan.nextLine();
					theatreSelection = theatreSelection.toLowerCase();
					if (theatreSelection.equals("fun")) {
						System.out.println("Available Movies in fun are "
								+ (theatreController.fun.getListOfMoviesRunninginTheatre()));
						for (Entry<ShowAndTiming, MoviesAvailable> entry : theatreController.fun.showTimingAndMovie
								.entrySet()) {
							System.out.println(
									entry.getKey() + "-" + entry.getKey().getShowTiming() + "-->" + entry.getValue());
						}
						System.out.println("Choose a showName you wish to see");
						showSelection = scan.nextLine();
						showSelected = Theatre.ShowAndTiming.valueOf(showSelection);
						if (showSelected != null) {
							for (Entry<SeatingType, Integer> entry : theatreController.fun.seatingTypeAndPrice
									.entrySet()) {
								System.out.println(entry.getKey() + "-->" + "Rs." + entry.getValue());
							}
							System.out.println("Please select Seater Type");
							seaterSelection = scan.nextLine();
							seatSelected = Theatre.SeatingType.valueOf(seaterSelection);

							LinkedHashMap<ShowAndTiming, Integer> ticket = theatreController.fun
									.bookTicketForShow(showSelected, seatSelected);
							if (ticket != null) {
								System.out.println(
										theatreController.fun.getTotalRevenueForShow(showSelected, seatSelected));
								System.out.println("Your ticket number is " + ticket.get(showSelected) + " in "
										+ theatreController.fun.theatreName + " for " + showSelected + " timing "
										+ showSelected.getShowTiming() + " in " + seatSelected);
							} else {
								LOGGER.warning("Sorry all the seats are filled..Choose another show");
							}
						} else {
							LOGGER.warning("Please..Select a proper show");
						}
					} else if (theatreSelection.equals("city")) {

						System.out.println("Available Movies in city are "
								+ (theatreController.city.getListOfMoviesRunninginTheatre()));
						for (Entry<ShowAndTiming, MoviesAvailable> entry : theatreController.city.showTimingAndMovie
								.entrySet()) {
							System.out.println(
									entry.getKey() + "-" + entry.getKey().getShowTiming() + "-->" + entry.getValue());
						}
						System.out.println("Choose a showname you wish to see");
						showSelection = scan.nextLine();
						showSelected = Theatre.ShowAndTiming.valueOf(showSelection);
						if (showSelected != null) {
							for (Entry<SeatingType, Integer> entry : theatreController.city.seatingTypeAndPrice
									.entrySet()) {
								System.out.println(entry.getKey() + "-->" + "Rs." + entry.getValue());
							}
							System.out.println("Please select Seater Type");
							seaterSelection = scan.nextLine();
							seatSelected = Theatre.SeatingType.valueOf(seaterSelection);
							LinkedHashMap<ShowAndTiming, Integer> ticket = theatreController.city
									.bookTicketForShow(showSelected, seatSelected);
							if (ticket != null) {
								System.out.println(
										theatreController.city.getTotalRevenueForShow(showSelected, seatSelected));
								System.out.println("Your ticket number is " + ticket.get(showSelected) + " in "
										+ theatreController.city.theatreName + " for " + showSelected + " timing "
										+ showSelected.getShowTiming() + " in " + seatSelected);
							} else {
								LOGGER.warning("Sorry all the seats are filled..Choose another show");
							}
						} else {
							LOGGER.warning("Please Select a proper show");
						}
					}
				} else {
					System.out.println("Please enter a proper choice ..");
				}

			} else if (userAction.equals("admin")) {

				System.out.println("view 1.Movies List  or 2.Revenue");
				String adminAction = scan.nextLine();
				adminAction = adminAction.toLowerCase();
				if (adminAction.equals("movieslist")) {
					System.out.println("Available movies are " + Arrays.toString(Movie.MoviesAvailable.values()));
				} else if (adminAction.equals("revenue")) {

					try {
						FileWriter fileWriter = new FileWriter("/home/linuxuser/Theatre Revenue.csv");
						fileWriter.append("Theatre, show timing, Total amount Sold");
						fileWriter.append("\n");
						for (Theatre theatre : theatreController.getlistOfTheatre()) {
							if (theatre.showAndRevenue != null) {
								for (Entry<ShowAndTiming, Integer> entry : theatre.showAndRevenue.entrySet()) {
									fileWriter.append(theatre.theatreName);
									fileWriter.append(",");
									fileWriter.append(entry.getKey().name());
									fileWriter.append(",");
									fileWriter.append("Rs.");
									fileWriter.append(entry.getValue().toString());
									fileWriter.append("\n");
									System.out.println(entry.getKey() + " Rs." + entry.getValue());
								}
							}
						}
						System.out.println("Stored in file successfully !!!");
						fileWriter.flush();
						fileWriter.close();
					} catch (Exception e) {
						System.out.println("Error in writing !!!" + e);

					}
				}
			}
		}
	}
}