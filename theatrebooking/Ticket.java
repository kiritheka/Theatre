package theatrebooking;

public class Ticket {

	int ticketId;
	String theatreName;
	String seaterName;
	int price;
	String showName;
	String showTiming;
	String movieName;

	Ticket(int ticketId, String theatreName, String seaterName, int price, String showName, String showTiming,
			String movieName) {
		this.ticketId = ticketId;
		this.theatreName = theatreName;
		this.seaterName = seaterName;
		this.price = price;
		this.showName = showName;
		this.showTiming = showTiming;
		this.movieName = movieName;

	}

}
