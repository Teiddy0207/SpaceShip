package main;

import java.util.Scanner;

public class SpaceShip {
	private String code;
	private String Name;
	private String type;
	private String country;
    private int yearBuilt; 
    private String currentMission;
    
    public SpaceShip() {
    	
    }
    public SpaceShip(String code, String Name,String type,String country,int yearBuilt,String currentMission) {
    	this.code= code;
    	this.Name = Name;
    	this.type = type;
    	this.country = country;
    	this.yearBuilt = yearBuilt;
    	this.currentMission = currentMission;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getCurrentMission() {
		return currentMission;
	}

	public void setCurrentMission(String currentMission) {
		this.currentMission = currentMission;
	}
    
	public void input()
	{
		
		Scanner sc =  new Scanner(System.in);
		
		Boolean check = false;
		while(!check) {
		System.out.print("Nhập mã tàu (code): ");
		this.code = sc.nextLine();
		
		if(this.code.isEmpty()) {
			 System.out.println("Mã tàu không được để trống. Vui lòng nhập lại!");	
		}else {
		check = true;
		}
		}
		System.out.print("Nhập tên tàu (name): ");
		this.Name = sc.nextLine();
		
		 System.out.print("Nhập loại tàu (type): ");
		 this.type = sc.nextLine();
		 System.out.print("Nhập quốc gia (country): ");
		 this.country = sc.nextLine();

		 
		 while(true) {
		 System.out.print("Nhập năm sản xuất (yearBuilt): ");
		 String input = sc.nextLine();
		 try {
			 this.yearBuilt = Integer.parseInt(input);
			 if(this.yearBuilt <= 0 ) {
                 System.out.println("Năm sản xuất phải là số dương. Vui lòng nhập lại!");

			 }else {
				 break;
			 }
		 } catch(NumberFormatException e){
             System.out.println("Năm sản xuất phải là một số hợp lệ. Vui lòng nhập lại!");

		 }
		 
		 }
		 
		 
		 System.out.print("Nhập nhiệm vụ hiện tại (currentMission): ");
		 this.currentMission = sc.nextLine();
		 
		
	}
	
	public void output()
	{
		 System.out.println("Thông tin tàu vũ trụ:");
		 System.out.println("Mã tàu: " + code);
	     System.out.println("Tên tàu: " + Name);
	     System.out.println("Loại tàu: " + type);
	     System.out.println("Quốc gia: " + country);
	     System.out.println("Năm sản xuất: " + yearBuilt);
	     System.out.println("Nhiệm vụ hiện tại: " + currentMission);
	}
}
