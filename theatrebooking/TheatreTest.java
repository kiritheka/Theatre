package theatrebooking;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import theatrebooking.MovieController;
import theatrebooking.Seater;
import theatrebooking.Show;
import theatrebooking.ShowController;
import theatrebooking.TheatreController;

class TheatreTest {
	MovieController movieController = new MovieController();
	TheatreController theatreController = new TheatreController();
	ShowController showController = new ShowController();
	CSVReport csvReport = new CSVReport();
	PDFReport pdfReport = new PDFReport();


	ArrayList<Show> listOfShowForMovie = new ArrayList<Show>();
	String movieName;

	@BeforeEach
	void setUp() throws Exception {
		movieName = "bighero";
	}

	@Test
	void testCheckForMovie() {

		assertEquals(true, movieController.checkForMovie(movieName));

		/* returns null when movie passed is not in list */
		assertEquals(false, movieController.checkForMovie("nomovie"));
		assertEquals(false, movieController.checkForMovie("1234"));
	}

	@Test
	void testGetlistOfShowsForMovie() {

		/* returns empty list when movie don't have any shows or null object is send */
		assertArrayEquals(listOfShowForMovie.toArray(), showController.getlistOfShowsForMovie("nomovie").toArray());
		assertArrayEquals(listOfShowForMovie.toArray(), showController.getlistOfShowsForMovie(null).toArray());

		listOfShowForMovie.add(showController.morningfun);
		listOfShowForMovie.add(showController.eveningcity);
		assertArrayEquals(listOfShowForMovie.toArray(), showController.getlistOfShowsForMovie(movieName).toArray());
	}

	@Test
	void testBookTicket() {

		assertEquals(true, showController.morningfun.bookTicket("gold", 2));

		// capacity of gold seater =20

		/* returns null when ticket is booked against unknown seatType or 
		 * when particular seaterType all seats are booked
		 */
		assertEquals(false, showController.morningfun.bookTicket("empty", 2));
		assertEquals(true, showController.morningfun.bookTicket("gold", 9));
		assertEquals(false, showController.morningfun.bookTicket("gold", 19));

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	void doCheckingException() {
		exception.expect(IOException.class);
		exception.expectMessage("Invalid Location to write");
		csvReport.generateRevenueReport(null);
		pdfReport.generateRevenueReport(null);

	}

}
