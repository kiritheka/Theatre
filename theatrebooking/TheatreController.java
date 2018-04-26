package theatrebooking;

import java.util.ArrayList;

public class TheatreController {
	ShowsController showsController = new ShowsController();

	Seater silver = new Seater("silver", 2, 100);
	Seater gold = new Seater("gold", 20, 200);
	Seater diamond = new Seater("diamond", 10, 400);
	ArrayList<Seater> seatingInFun = new ArrayList<Seater>() {{
			add(silver);
			add(gold);
			add(diamond);
		}};

	ArrayList<Shows> showsInFun = new ArrayList<Shows>() {{
			add(showsController.morningfun);
			add(showsController.noon);
			add(showsController.evening);
		}};

	Theatre fun = new Theatre("Fun", 3, seatingInFun, showsInFun);

	Seater class1 = new Seater("class1", 40, 100);
	Seater class2 = new Seater("class2", 50, 300);
	Seater class3 = new Seater("class3", 10, 500);
	ArrayList<Seater> seatingInCity = new ArrayList<Seater>() {{
			add(class1);
			add(class2);
			add(class3);
		}};

	ArrayList<Shows> showsInCity = new ArrayList<Shows>() {{
			add(showsController.morning);
			add(showsController.noon);
			add(showsController.evening);
			add(showsController.night);
		}};

	Theatre city = new Theatre("City", 100, seatingInCity, showsInCity);

	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}