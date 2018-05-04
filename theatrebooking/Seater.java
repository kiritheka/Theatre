package theatrebooking;

public class Seater {

	enum Type {
		silver, gold, diamond, platinum, class1, class2, class3
	}

	Type name;
	int price;

	public Seater(Type name, int price) {
		this.name = name;
		this.price = price;

	}
}
