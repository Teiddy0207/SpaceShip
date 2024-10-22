package main;

import java.util.Scanner;

public class Satellite extends SpaceShip {
	private String rotate;
	public  Satellite () {
		
	}
	
	public Satellite(String code, String Name,String type,String country,int yearBuilt,String currentMission, String rotate) {
		super(code,Name,type,country,yearBuilt,currentMission);
		this.rotate = rotate;
	}
	
	public void input()
	{
	super.input();
	Scanner sc = new Scanner(System.in);

	System.out.print("Dang quay quanh hanh tinh nao: ");
	this.rotate = sc.nextLine();
	}
	public void output()
	{
	super.output();
	System.out.println("Hanh tinh dang duoc quan sat: : " + rotate);

	}

	public String getRotate() {
		return rotate;
	}

	public void setRotate(String rotate) {
		this.rotate = rotate;
	}
	
	
	}
	
	

