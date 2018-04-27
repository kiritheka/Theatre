package theatrebooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import theatrebooking.Seater.SeaterType;

public class TheatreController {

	Seater silver = new Seater(SeaterType.silver, 100);
	Seater gold = new Seater(SeaterType.gold, 200);
	Seater diamond = new Seater(SeaterType.diamond, 400);
	Seater platinum = new Seater(SeaterType.platinum,500);
	LinkedHashMap<Seater, Integer> seatAndCapacityInFun = new LinkedHashMap<Seater, Integer>() {{
			put(silver, 20);
			put(gold, 10);
			put(diamond, 10);
			put(platinum, 10);
			
		}};
	public Theatre fun = new Theatre("Fun", 50, seatAndCapacityInFun);

	Seater class1 = new Seater(SeaterType.class1, 100);
	Seater class2 = new Seater(SeaterType.class2, 300);
	Seater class3 = new Seater(SeaterType.class3, 500);
	LinkedHashMap<Seater, Integer> seatAndCapacityInCity = new LinkedHashMap<Seater, Integer>() {{
			put(class1, 50);
			put(class2, 25);
			put(class3, 25);
		}};
	public Theatre city = new Theatre("City", 100, seatAndCapacityInCity);

	public ArrayList<Theatre> getlistOfTheatre() {
		ArrayList<Theatre> listOfLevel = new ArrayList<Theatre>();
		listOfLevel.add(fun);
		listOfLevel.add(city);

		return listOfLevel;
	}
}