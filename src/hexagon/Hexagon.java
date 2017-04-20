package hexagon;

import java.util.ArrayList;

public class Hexagon {
	int id;

	public String A;
	public String B;
	public String C;
	public String D;
	public String E;
	public String F;

	String colorString;

	public Hexagon(int id, String colors) {

		setID(id);
		setColors(colors);

	}

	private void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setColors(String colorsIn) throws IllegalArgumentException {
		if (colorsIn.length() != 6) {
			throw new IllegalArgumentException("Colors != 6");
		}
		colorString = colorsIn;
		String[] colorSplitter = colorsIn.split("");

		A = colorSplitter[0];
		validateColor(A);
		B = colorSplitter[1];
		validateColor(B);
		C = colorSplitter[2];
		validateColor(C);
		D = colorSplitter[3];
		validateColor(D);
		E = colorSplitter[4];
		validateColor(E);
		F = colorSplitter[5];
		validateColor(F);

	}

	public void validateColor(String color) {
		boolean isValid = color.equals("R") || color.equals("B") || color.equals("Y") || color.equals("G")
				|| color.equals("O") || color.equals("P");

		if (!isValid) {
			throw new IllegalArgumentException("Color is not [R, B, Y, G, O, P]");
		}
	}

	public String getColors() {
		return colorString;
	}

	public String toString() {
		return id + " " + colorString;
	}

	public String getColorBySegmentIndex(int segmentIndex) {
		int segmentIndex1 = segmentIndex % 6;// cyclical
		if (segmentIndex1 < 0) {
			segmentIndex1 += 6;
		}
		switch (segmentIndex1) {

		case 0:
			return this.A;

		case 1:
			return this.B;

		case 2:
			return this.C;

		case 3:
			return this.D;

		case 4:
			return this.E;

		case 5:
			return this.F;

		default:
			throw new IllegalArgumentException("Segment Index Invalid");

		}
	}

	public ArrayList<String> getRotatedColors(HexagonPosition hexagonPosition) throws IllegalArgumentException {
		if (this.id != hexagonPosition.hexagonId) {
			throw new IllegalArgumentException("id does not match");
		}
		ArrayList<String> colors = new ArrayList<>();
		colors.add(getColorBySegmentIndex(0 - hexagonPosition.clockwiseRotation));
		colors.add(getColorBySegmentIndex(1 - hexagonPosition.clockwiseRotation));
		colors.add(getColorBySegmentIndex(2 - hexagonPosition.clockwiseRotation));
		colors.add(getColorBySegmentIndex(3 - hexagonPosition.clockwiseRotation));
		colors.add(getColorBySegmentIndex(4 - hexagonPosition.clockwiseRotation));
		colors.add(getColorBySegmentIndex(5 - hexagonPosition.clockwiseRotation));
		return colors;
	}
	
	public ArrayList<String> getColorList(){
		ArrayList<String> colors = new ArrayList<>();
		colors.add(getColorBySegmentIndex(0));
		colors.add(getColorBySegmentIndex(1));
		colors.add(getColorBySegmentIndex(2));
		colors.add(getColorBySegmentIndex(3));
		colors.add(getColorBySegmentIndex(4));
		colors.add(getColorBySegmentIndex(5 ));
		return colors;
	}
}
