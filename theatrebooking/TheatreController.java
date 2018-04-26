package theatrebooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import theatrebooking.Seater.SeaterType;

public class TheatreController {
	ShowsController showsController = new ShowsController();

	Seater silver = new Seater(SeaterType.silver, 100);
	Seater gold = new Seater(SeaterType.gold, 200);
	Seater diamond = new Seater(SeaterType.diamond,400);
	LinkedHashMap<Seater, Integer> seatAndCapacityInFun = new LinkedHashMap<Seater, Integer>() {{
			put(silver,20);
			put(gold,20);
			put(diamond,10);
		}};

	ArrayList<Shows> showsInFun = new ArrayList<Shows>() {{
			add(showsController.morningfun);
			add(showsController.noon);
			add(showsController.evening);
		}};

	Theatre fun = new Theatre("Fun", 50, seatAndCapacityInFun, showsInFun);

	Seater class1 = new Seater(SeaterType.class1, 100);
	Seater class2 = new Seater(SeaterType.class2, 300);
	Seater class3 = new Seater(SeaterType.class3, 500);
	LinkedHashMap<Seater, Integer> seatAndCapacityInCity = new LinkedHashMap<Seater, Integer>() {{
			put(class1,50);
			put(class2,25);
			put(class3,25);
		}};

	ArrayList<Shows> showsInCity = new ArrayList<Shows>() {{
			add(showsController.morning);
			add(showsController.noon);
			add(showsController.evening);
			add(showsController.night);
		}};

	Theatre city = new Theatre("City", 100, seatAndCapacityInCity, showsInCity);

	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}