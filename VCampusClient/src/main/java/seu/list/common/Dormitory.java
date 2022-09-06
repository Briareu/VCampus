//package main.java.seu.list.common;
package seu.list.common;

import java.io.Serializable;
import java.util.*;

public class Dormitory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userID;  //学生姓名
	private String DormitoryID;  //宿舍号
	private int StudentBunkID;  //床位
	private int Water;  //水费
	private int Electricity;  //电费
	private int DormitoryScore;  //卫生评分
	private String DormitoryMaintain;  //维修申请
	private String StudentExchange;  //调换申请

	public Dormitory()
	{
        super();
    }

	public Dormitory(String userID,String DormitoryID, int StudentBunkID,int Water,int Electricity,int DormitoryScore,String DormitoryMaintain,String StudentExchange)
	{
		this.userID=userID;
        this.DormitoryID=DormitoryID;
        this.StudentBunkID=StudentBunkID;
        this.Water=Water;
        this.Electricity=Electricity;
        this.DormitoryScore=DormitoryScore;
        this.DormitoryMaintain=DormitoryMaintain;
        this.StudentExchange=StudentExchange;
    }

	public String getuserID()
	{
        return userID;
    }

    public void setuserID(String userID)
    {
        this.userID = userID;
    }

	public String getDormitoryID()
	{
        return DormitoryID;
    }

    public void setDormitoryID(String DormitoryID)
    {
        this.DormitoryID = DormitoryID;
    }

    public int getStudentBunkID()
	{
        return StudentBunkID;
    }

    public void setStudentBunkID(int StudentBunkID)
    {
        this.StudentBunkID=StudentBunkID;
    }

    public int getWater()
	{
        return Water;
    }

    public void setWater(int Water)
    {
        this.Water=Water;
    }

    public int getElectricity()
	{
        return Electricity;
    }

    public void setElectricity(int Electricity)
    {
        this.Electricity=Electricity;
    }

    public int getDormitoryScore()
	{
        return DormitoryScore;
    }

    public void setDormitoryScore(int DormitoryScore)
    {
        this.DormitoryScore=DormitoryScore;
    }

    public String getDormitoryMaintain()
	{
        return DormitoryMaintain;
    }

    public void setDormitoryMaintain(String DormitoryMaintain)
    {
        this.DormitoryMaintain=DormitoryMaintain;
    }

    public String getStudentExchange()
   	{
           return StudentExchange;
       }

    public void setStudentExchange(String StudentExchange)
    {
    	this.StudentExchange=StudentExchange;
    }

    @Override
    public String toString()
    {
        return "Dormitory{" + "userID="+userID+"DormitoryID=" + DormitoryID + ", StudentBunkID=" + StudentBunkID +",Water="+Water+",Electricity="+Electricity+",DormitoryScore="+DormitoryScore+",DormitoryMaintain="+DormitoryMaintain+",StudentExchange="+StudentExchange+"}";
    }

    public Vector<String> getContent() {
		Vector<String> dormitoryContents = new Vector<String>();
		dormitoryContents.add(userID);
		dormitoryContents.add(DormitoryID);
		dormitoryContents.add(String.valueOf(StudentBunkID));
    	//dormitoryContents.add(StudentBunkID);
		dormitoryContents.add((String.valueOf(Water)));
    	//dormitoryContents.add(Water);
		dormitoryContents.add(String.valueOf(Electricity));
    	//dormitoryContents.add(Electricity);
		dormitoryContents.add(String.valueOf(DormitoryScore));
    	//dormitoryContents.add(DormitoryScore);
		dormitoryContents.add(DormitoryMaintain);
    	//dormitoryContents.add(DormitoryMaintain);
		dormitoryContents.add(StudentExchange);
    	//dormitoryContents.add(StudentExchange);
		return dormitoryContents;
	}

	public void setContent(Vector<String> content) {
		userID = content.get(0);
		DormitoryID = content.get(1);
    	StudentBunkID = Integer.parseInt(content.get(2));
    	Water = Integer.parseInt(content.get(3));
    	Electricity = Integer.parseInt(content.get(4));
    	DormitoryScore = Integer.parseInt(content.get(5));
    	DormitoryMaintain = content.get(6);
    	StudentExchange = content.get(7);
	}
}
