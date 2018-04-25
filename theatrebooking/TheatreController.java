package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TheatreController {
	MovieController movieController = new MovieController();

	Seater silver = new Seater("silver", 2, 100);
	Seater gold = new Seater("gold", 20, 200);
	Seater diamond = new Seater("diamond", 10, 400);
	LinkedHashMap<String, Seater> seatingInFun = new LinkedHashMap<String, Seater>() {
		{
			put(silver.seaterName, silver);
			put(gold.seaterName, gold);
			put(diamond.seaterName, diamond);
		}
	};

	Shows morning = new Shows("morning", "9.30AM", movieController.bigHero);
	Shows noon = new Shows("noon", "12.30PM", movieController.aram);
	Shows evening = new Shows("evening", "5.00PM", movieController.happy);
	LinkedHashMap<String, Shows> showsInFun = new LinkedHashMap<String, Shows>() {
		{
			put(morning.showName, morning);
			put(noon.showName, noon);
			put(evening.showName, evening);
		}
	};

	Theatre fun = new Theatre("Fun", 3, seatingInFun, showsInFun);

	Seater class1 = new Seater("class1", 40, 100);
	Seater class2 = new Seater("class2", 50, 300);
	Seater class3 = new Seater("class3", 10, 500);
	LinkedHashMap<String, Seater> seatingInCity = new LinkedHashMap<String, Seater>() {
		{
			put(class1.seaterName, class1);
			put(class2.seaterName, class2);
			put(class3.seaterName, class3);
		}
	};

	Shows night = new Shows("night", "8.00PM", movieController.dangal);
	LinkedHashMap<String, Shows> showsInCity = new LinkedHashMap<String, Shows>() {
		{
			put(morning.showName, morning);
			put(noon.showName, noon);
			put(evening.showName, evening);
			put(night.showName, night);
		}
	};

	Theatre city = new Theatre("City", 100, seatingInCity, showsInCity);

	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}