package main;

import java.util.*;

public class Rocket extends SpaceShip {
	private int crewCapacity; 
	public Rocket() {
		super();
		this.crewCapacity = 0;
	}
	
	public Rocket(String code, String Name,String type,String country,int yearBuilt,String currentMission, int crewCapacity) {
		super(code,Name,type,country,yearBuilt,currentMission);
		this.crewCapacity = crewCapacity;
	}
public void input()
{
super.input();
Scanner sc = new Scanner(System.in);

boolean check = false;
while(!check) {
	System.out.print("Nhập so luong phi hanh doan: ");
	String input = sc.nextLine();
try {
	
	this.crewCapacity = Integer.parseInt(input);
	if(this.crewCapacity < 0) {
        System.out.println("so phi hanh doan phải là số dương. Vui lòng nhập lại!");

	}
	check = true;
}catch(NumberFormatException e) {
    System.out.println("so phi hanh doan phải là một số hợp lệ. Vui lòng nhập lại!");

}
}
}

public void output()
{
super.output();
System.out.println("So phi hanh doan la: " + crewCapacity);

}

public int getCrewCapacity() {
	return crewCapacity;
}

public void setCrewCapacity(int crewCapacity) {
	this.crewCapacity = crewCapacity;
}


}
