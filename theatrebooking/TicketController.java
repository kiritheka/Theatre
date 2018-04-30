package theatrebooking;

import java.util.ArrayList;
import java.util.Map.Entry;

public class TicketController {

	ArrayList<Ticket> listOfTickets = new ArrayList<Ticket>();
    int id=1;
	
	public void generateTicket(Show showSelected, String seaterSelected) {
		Ticket ticketGenerated = null;
		if (showSelected != null) {
			ticketGenerated = new Ticket(id++, showSelected.theatre.name, seaterSelected, showSelected.name,
					showSelected.movie.name);
			listOfTickets.add(ticketGenerated);
		}
		System.out.format("Your Ticket Number is %d for %s %n", ticketGenerated.id, ticketGenerated.showName);
	}

}
