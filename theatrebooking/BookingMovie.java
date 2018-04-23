package theatrebooking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;

import theatrebooking.Movie.MoviesAvailable;
import theatrebooking.Theatre.SeatingType;
import theatrebooking.Theatre.ShowAndTiming;

public class BookingMovie {
	public static void main(String args[]) throws IOException {
		TheatreController theatreController = new TheatreController();
       // BufferedWriter bw = new BufferedWriter(new FileWriter("book"));

		Scanner scan = new Scanner(System.in);
		String userAction = "";
		String theatreSelection = "";
		String showSelection = "";
		String seaterSelection = "";
		ShowAndTiming showSelected;
		SeatingType seatSelected;
		
		while (!userAction.equals("exit")) {

			System.out.println("Do you wish to book or exit or admin?");
			userAction = scan.nextLine();

			if (userAction.equals("book")) {
				System.out.println("List of Theatres available..");
				for (int i = 0; i < theatreController.getlistOfTheatre().size(); i++) {
					System.out.println(theatreController.getlistOfTheatre().get(i).theatreName);
				}
				System.out.println("Choose a theatre to know more");
				theatreSelection = scan.nextLine();

				if (theatreSelection.equals("fun")) {
					for (Entry<ShowAndTiming, MoviesAvailable> entry : theatreController.fun.showTimingAndMovie
							.entrySet()) {
						System.out.println(
								entry.getKey() + "-" + entry.getKey().getShowTiming() + "-->" + entry.getValue());
					}
					System.out.println("Choose a showName you wish to see");
					showSelection = scan.nextLine();
					showSelected = Theatre.ShowAndTiming.valueOf(showSelection);
					if (showSelected != null) {
						for (Entry<SeatingType, Integer> entry : theatreController.fun.seatingTypeAndPrice.entrySet()) {
							System.out.println(entry.getKey() + "-->" + "Rs." + entry.getValue());
						}
						System.out.println("Please select Seater Type");
						seaterSelection = scan.nextLine();
						seatSelected = Theatre.SeatingType.valueOf(seaterSelection);
						System.out.println(theatreController.fun.getTotalPriceForShow(showSelected, seatSelected));
						System.out.println("Your ticket number is"
								+ theatreController.fun.getTicket(showSelected, seatSelected).get(showSelected)+" in "+theatreController.fun.theatreName+" for "+showSelected+" timing "+showSelected.getShowTiming()+" in "+seatSelected);
					} else {
						System.out.println("Please..Select a proper show");
					}
				}
			} else if (userAction.equals("admin")) {
				String locationToWrite=scan.nextLine();
				BufferedWriter bw = new BufferedWriter(new FileWriter(locationToWrite));
				bw.write("Theatre, show timing, Total amount Sold");
				bw.newLine();
				for (Theatre theatre : theatreController.getlistOfTheatre()) {
					System.out.println(theatre.theatreName);
					for (Entry<ShowAndTiming, Integer> entry : theatre.showAndRevenue.entrySet()) {
						bw.write(theatre.theatreName+" "+entry.getKey() + ", Rs." + entry.getValue());
						bw.newLine();
						System.out.println(entry.getKey() + " Rs." + entry.getValue());
					}
				}
				bw.close();
			}
		}
	}
}