package theatrebooking;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import theatrebooking.MovieController;
import theatrebooking.Seater;
import theatrebooking.Shows;
import theatrebooking.ShowsController;
import theatrebooking.TheatreController;

class TheatreTest {
	MovieController movieController = new MovieController();
	TheatreController theatreController = new TheatreController();
	ShowsController showsController = new ShowsController();
	RevenueGeneration revenueGeneration = new RevenueGeneration();

	ArrayList<Shows> listOfShowForMovie = new ArrayList<Shows>();
	LinkedHashMap<Seater, Integer> seaterAndTicket = new LinkedHashMap<Seater, Integer>();
	LinkedHashMap<Shows, Integer> showAndRevenue = new LinkedHashMap<Shows, Integer>();
	String movieName;

	@BeforeEach
	void setUp() throws Exception {
		movieName = "bighero";
		seaterAndTicket.put(theatreController.gold, 1);
	}

	@Test
	void testCheckForMovie() {

		assertEquals("bighero", movieController.checkForMovie(movieName));

		/* returns null when movie passed is not in list */
		assertEquals(null, movieController.checkForMovie("nomovie"));
		assertEquals(null, movieController.checkForMovie("1234"));
	}

	@Test
	void testGetlistOfShowsForMovie() {

		/* returns empty list when movie don't have any shows or null object is send */
		assertArrayEquals(listOfShowForMovie.toArray(), showsController.getlistOfShowsForMovie("nomovie").toArray());
		assertArrayEquals(listOfShowForMovie.toArray(), showsController.getlistOfShowsForMovie(null).toArray());

		listOfShowForMovie.add(showsController.morningfun);
		listOfShowForMovie.add(showsController.eveningcity);
		assertArrayEquals(listOfShowForMovie.toArray(), showsController.getlistOfShowsForMovie(movieName).toArray());
	}

	@Test
	void testBookTicket() {

		assertEquals(seaterAndTicket, showsController.morningfun.bookTicket("gold"));

		//capacity of gold seater =1 
	
		/* returns null when ticket is booked against unknown seatType or when
		 * particular seaterType all seats are booked
		 */
		assertEquals(null, showsController.morningfun.bookTicket("empty"));
		assertEquals(null, showsController.morningfun.bookTicket("gold"));

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	void doCheckingException() {
		exception.expect(FileNotFoundException.class);
		exception.expectMessage("Invalid Location to write");
		revenueGeneration.generateRevenueReport(null);
	}
}
