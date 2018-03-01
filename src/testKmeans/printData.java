package testKmeans;

public class printData {
	private int index;
	private double career;
	private double time;

	public printData(int index, double career, double time){
		super();
		this.index=index;
		this.career = career;
		this.time = time;
	}

	public double getCareer() {
		return career;
	}
	
	public double getTime() {
		return time;
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "["+index+" "+"Career=" + career + ", time=" + time +"]";
	}
}
