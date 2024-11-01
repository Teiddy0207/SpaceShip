package GGV;

public  class Giangvien extends Person {
	private String Donvi;
    private int Soct;
	
	public Giangvien() {}
	
	public Giangvien(String MaDD, String Hoten, String GT, String Donvi, int Soct)
	{
		super(MaDD, Hoten,GT);
		this.Donvi = Donvi;
	    this.Soct = Soct;
	}
	
	public String Xetthuong() {
		return this.Soct > 10 ? "Khen thuong" : "binh thuong thoi";
	}

	public String getDonvi() {
		return Donvi;
	}

	public void setDonvi(String donvi) {
		Donvi = donvi;
	}

	public int getSoct() {
		return Soct;
	}

	public void setSoct(int soct) {
		Soct = soct;
	}

}
