package hexagon;

public class HexagonPosition {
	int hexagonId;
	int clockwiseRotation;
	
	public HexagonPosition(int hexagonId, int clockwiseRotation){
		setHexagonId(hexagonId);
		setClockwiseRotation(clockwiseRotation);
	}
	
	public void setHexagonId(int hexagonIdIn){
		hexagonId = hexagonIdIn;
	}
	public int getHexagonId(){
		return hexagonId;
	}
	
	public void setClockwiseRotation(int rotationIn){
		clockwiseRotation = rotationIn;
	}
	public int getClockwiseRotation(){
		return clockwiseRotation;
	}
	
	public String toString(){
		return hexagonId + " " + clockwiseRotation;
	}


}
