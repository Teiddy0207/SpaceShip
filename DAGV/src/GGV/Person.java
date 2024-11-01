package GGV;

public abstract class Person {
	private String MaDD;
	private String Hoten;
	private String GT;
	
	public Person()
	{
		
	}
	
	public Person(String MaDD, String Hoten, String GT)
	{
		this.MaDD = MaDD;
		this.Hoten = Hoten;
		this.GT = GT;
		
		
		
	}

	public String getMaDD() {
		return MaDD;
	}

	public void setMaDD(String maDD) {
		MaDD = maDD;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public String getGT() {
		return GT;
	}

	public void setGT(String gT) {
		GT = gT;
	}
	
	public abstract String Xetthuong();
	
}
