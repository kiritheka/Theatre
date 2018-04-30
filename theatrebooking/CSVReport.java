package theatrebooking;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReport implements RevenueGeneration {
	Logger LOGGER = Logger.getLogger(CSVReport.class.getName());

	public void generateRevenueReport(ArrayList<Show> listOfShow) {
		try {
			FileWriter fileWriter = new FileWriter("/home/linuxuser/Theatre Revenue.csv");
			fileWriter.append("Theatre, show timing, Total amount Sold");
			fileWriter.append("\n");
			if (listOfShow != null) {
				for (Show show : listOfShow) {
					int revenue = 0;
					for (Entry<Seater, Integer> entry : show.seatAndFilledCount.entrySet()) {
						revenue = revenue + (entry.getValue() * entry.getKey().price);
					}
					fileWriter.append(show.theatre.name);
					fileWriter.append(",");
					fileWriter.append(show.name);
					fileWriter.append(",");
					fileWriter.append("Rs.");
					fileWriter.append(String.valueOf(revenue));
					fileWriter.append("\n");
				}
			}
			LOGGER.info("Stored in file successfully !!!");
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error in writing !!!", e);
		}
	}
}