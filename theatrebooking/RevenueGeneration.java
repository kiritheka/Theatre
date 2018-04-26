package theatrebooking;

import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class RevenueGeneration {

	TheatreController theatreController = new TheatreController();
	Logger LOGGER = Logger.getLogger(RevenueGeneration.class.getName());

	LinkedHashMap<Shows, Integer> showAndRevenue = new LinkedHashMap<Shows, Integer>();

	public LinkedHashMap<Shows, Integer> getTotalRevenueForShow(Shows userShow, Seater userSeater) {
		if (showAndRevenue.get(userShow) == null) {
			showAndRevenue.put(userShow, userSeater.price);
			return showAndRevenue;
		} else {
			int price = showAndRevenue.get(userShow);
			showAndRevenue.put(userShow, price + userSeater.price);
			return showAndRevenue;
		}
	}

	public void generateRevenueReport() {
		try {
			FileWriter fileWriter = new FileWriter("/home/linuxuser/TheatreRevenue.csv");
			fileWriter.append("Theatre, show timing, Total amount Sold");
			fileWriter.append("\n");
			for (Theatre theatre : theatreController.getlistOfTheatre()) {
				System.out.println(theatre.theatreName + "rev" + showAndRevenue);
				if (showAndRevenue != null) {
					System.out.println("l");
					for (Entry<Shows, Integer> entry : showAndRevenue.entrySet()) {
						System.out.println(theatre.theatreName);
						fileWriter.append(theatre.theatreName);
						fileWriter.append(",");
						fileWriter.append(entry.getKey().showName);
						fileWriter.append(",");
						fileWriter.append("Rs.");
						fileWriter.append(entry.getValue().toString());
						fileWriter.append("\n");
						System.out.println(entry.getKey() + " Rs." + entry.getValue());
					}
				}
			}
			LOGGER.info("Stored in file successfully !!!");
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			LOGGER.warning("Error in writing !!!" + e);

		}

	}
}