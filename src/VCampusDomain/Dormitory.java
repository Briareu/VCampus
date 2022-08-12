package VCampusDomain;

import java.io.Serializable;

public class Dormitory implements Serializable{
	
	private String DormitoryID;  //宿舍号
	private int StudentBunkID;  //床位
	private int Water;  //水费
	private int Electricity;  //电费
	private int DormitoryScore;  //卫生评分
	private boolean DormitoryMaintain;  //维修申请
	private boolean StudentExchange;  //调换申请
	
	public Dormitory() 
	{
        super();
    }
	
	public Dormitory(String DormitoryID, int StudentBunkID,int Water,int Electricity,int DormitoryScore,boolean DormitoryMaintain,boolean StudentExchange) 
	{
        this.DormitoryID=DormitoryID;
        this.StudentBunkID=StudentBunkID;
        this.Water=Water;
        this.Electricity=Electricity;
        this.DormitoryScore=DormitoryScore;
        this.DormitoryMaintain=DormitoryMaintain;
        this.StudentExchange=StudentExchange;
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
        this.StudentBunkID=StudentBunkIDD;
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
    
    public boolean getDormitoryMaintain() 
	{
        return DormitoryMaintain;
    }

    public void setDormitoryMaintain(boolean DormitoryMaintain) 
    {
        this.DormitoryMaintain=DormitoryMaintain;
    }

    public boolean getStudentExchange() 
   	{
           return StudentExchange;
       }

    public void setStudentExchange(boolean StudentExchange) 
    {
    	this.StudentExchange=StudentExchange;
    }
    
    @Override
    public String toString() 
    {
        return "Dormitory{" + "DormitoryID=" + DormitoryID + ", StudentBunkID=" + StudentBunkID +",Water="+Water+",Electricity="+Electricity+",DormitoryScore="+DormitoryScore+",DormitoryMaintain="+DormitoryMaintain+",StudentExchange="+StudentExchange};
    }
}
