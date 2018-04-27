package theatrebooking;

import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class RevenueGeneration {
	Logger LOGGER = Logger.getLogger(RevenueGeneration.class.getName());

	public void generateRevenueReport(LinkedHashMap<Shows, Integer> showAndRevenue) {
		try {
			if (showAndRevenue != null) {
				FileWriter fileWriter = new FileWriter("/home/linuxuser/Theatre Revenue.csv");
				fileWriter.append("Theatre, show timing, Total amount Sold");
				fileWriter.append("\n");
				for (Entry<Shows, Integer> showRevenue : showAndRevenue.entrySet()) {
					fileWriter.append(showRevenue.getKey().theatre.theatreName);
					fileWriter.append(",");
					fileWriter.append(showRevenue.getKey().showName);
					fileWriter.append(",");
					fileWriter.append("Rs.");
					fileWriter.append(showRevenue.getValue().toString());
					fileWriter.append("\n");
				}

				LOGGER.info("Stored in file successfully !!!");
				fileWriter.flush();
				fileWriter.close();
			}
		} catch (Exception e) {
			LOGGER.warning("Error in writing !!!" + e);
		}
	}
}