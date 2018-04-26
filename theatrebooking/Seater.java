package theatrebooking;

public class Seater {

	enum SeaterType {
		silver, gold, diamond, platinum, class1, class2, class3
	}

	SeaterType seaterName;
	int price;

	Seater(SeaterType seaterName, int price) {
		this.seaterName = seaterName;
		this.price = price;

	}
}
