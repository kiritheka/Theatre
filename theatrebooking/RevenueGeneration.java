package theatrebooking;

import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class RevenueGeneration {

	TheatreController theatreController = new TheatreController();
	Logger LOGGER = Logger.getLogger(RevenueGeneration.class.getName());

	
	/*LinkedHashMap<Shows, Integer> showAndPrice = new LinkedHashMap<Shows, Integer>();
	public LinkedHashMap<Shows, Integer> getTotalPriceForShow(Theatre theatre) {
		
		for (Shows show : theatre.show) {
			for (Entry<Shows, LinkedHashMap<Seater, Integer>> entry : theatre.showAndSeaterCount.entrySet()) {
				for (Entry<Seater, Integer> seatCount : entry.getValue().entrySet()) {
					int price=seatCount.getKey().price*seatCount.getValue();;
					showAndPrice.put(show,price+showAndPrice.get(show)); 
				}
			}	
		}
		return showAndPrice;
	}*/
	
	
	
	public void generateRevenueReport() {
		try {
			    FileWriter fileWriter = new FileWriter("/home/linuxuser/Theatre Revenue.csv");
				fileWriter.append("Theatre, show timing, Total amount Sold");
				fileWriter.append("\n");
				for (Theatre theatre : theatreController.getlistOfTheatre()) {
					if (theatre.showAndPrice != null) {
					System.out.println(theatre.theatreName);
					for (Entry<Shows, Integer> entry : theatre.showAndPrice.entrySet()) {
						System.out.println("INN");
						fileWriter.append(theatre.theatreName);
						fileWriter.append(",");
						fileWriter.append(entry.getKey().showName);
						fileWriter.append(",");
						fileWriter.append("Rs.");
						fileWriter.append(entry.getValue().toString());
						fileWriter.append("\n");
						System.out.println(entry.getKey().showName + " Rs." + entry.getValue().intValue());
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