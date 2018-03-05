package com.RDNH.todatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.RDNH.dao.AwayTeamDAO;
import com.RDNH.dao.HomeTeamDAO;
import com.RDNH.dao.LiveMessageDAO;
import com.RDNH.dao.RankDAO;
import com.RDNH.dao.TeamDAO;
import com.RDNH.helper.CreateTable;
import com.RDNH.helper.connectionPool;
import com.RDNH.news.FormNews;
import com.RDNH.news.Rank;

public class GetData {
	public static void main(String[] args) throws Exception { 
//		toDatabase();
		news();
    }
	/**
	 * �������Ϣд�����ݿ���
	 * @throws Exception 
	 */
	public static void toDatabase() throws Exception{
		connectionPool cp=new connectionPool();
		CreateTable table=new CreateTable(cp);
		table.InitalTable();
		ExecutorService executor=Executors.newFixedThreadPool(5);//���ö��߳��������Ч��
		executor.execute(new getAway());
		executor.execute(new getHome());
		executor.execute(new getLive());
		executor.execute(new getTeam());
		executor.execute(new getRank());
		executor.shutdown();
//		getAway();
//		getHome();
//		getLive();
//		getTeam();
//		getRank();
	}
	public static void news(){
		FormNews deal=new FormNews();
		connectionPool cp = new connectionPool();
		LiveMessageDAO goal = new LiveMessageDAO(cp);
		TeamDAO team = new TeamDAO(cp);
		RankDAO rank = new RankDAO(cp);
		LiveMessageDAO live = new LiveMessageDAO(cp);
		AwayTeamDAO awayTeam = new AwayTeamDAO(cp);
		HomeTeamDAO homeTeam = new HomeTeamDAO(cp);
		for(int i=1;i<31;i++){
			System.out.println(i);
//			deal.title(goal,team,rank,i);
//			deal.firstSentence(live,team,i);
			deal.secondSentence(team,goal,awayTeam,homeTeam,i);
//			deal.body(live,i);
//			deal.lastSentence(awayTeam,homeTeam,team,i); 
			System.out.println();
		}       
	}
}
	/**
	 * ��ȡ�ͳ���Ϣ���������ݿ�
	 */
	class getAway implements Runnable{
		public void run(){
			try {
	        	String filename="//Volumes//Transcend//�ļ�//SampleData//";
	        	connectionPool cp=new connectionPool();
	        	AwayTeamDAO awayTeam=new AwayTeamDAO(cp);
	        	for(int i=1;i<31;i++){
		        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename+i+".away.csv"), "UTF-8");
		        	BufferedReader reader = new BufferedReader(isr);
		            reader.readLine();//��һ����Ϣ��Ϊ������Ϣ������,�����Ҫ��ע�͵� 
		            String line = null;        
		            while((line=reader.readLine())!=null){ 
		                String item[] = line.split(",");//CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з� 
		                
		            	awayTeam.insertAwayTeam(i, item);
		            }  
	        	}
	        	cp.closeConnectionPool();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
		}
	}
	/**
	 * ��ȡ������Ϣ���������ݿ�
	 */
	 class getHome implements Runnable{
		 public void run(){
			try {
	        	String filename="//Volumes//Transcend//�ļ�//SampleData//";
	        	connectionPool cp=new connectionPool();
	        	HomeTeamDAO homeTeam=new HomeTeamDAO(cp);
	        	for(int i=1;i<31;i++){
		        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename+i+".home.csv"), "UTF-8");
		        	BufferedReader reader = new BufferedReader(isr);
		            reader.readLine();//��һ����Ϣ��Ϊ������Ϣ������,�����Ҫ��ע�͵� 
		            String line = null;        
		            while((line=reader.readLine())!=null){ 
		                String item[] = line.split(",");//CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з� 
		                
		                homeTeam.insertHomeTeam(i,item);
		            }  
	        	}
	        	cp.closeConnectionPool();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
	 }
	/**
	 * ��ȡֱ����Ϣ�����ݿ�
	 */
	class getLive implements Runnable{
		public void run(){
			try {
	        	String filename="//Volumes//Transcend//�ļ�//SampleData//";
	        	connectionPool cp=new connectionPool();
	        	LiveMessageDAO liveMessage=new LiveMessageDAO(cp);
	        	for(int i=1;i<31;i++){
		        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename+i+".live.csv"), "UTF-8");
		        	BufferedReader reader = new BufferedReader(isr);
		            String line = null; 
		            while((line=reader.readLine())!=null){ 
		                String item[] = line.split(",");//CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з� 	                
		                liveMessage.insertLiveMessage(i,item);
		            }  
	        	}
	        	cp.closeConnectionPool();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
	}
	/**
	 * ��ȡ��ս˫����Ϣ�����ݿ�
	 */
	class getTeam implements Runnable{
		public void run(){
			try {
	        	String filename="//Volumes//Transcend//�ļ�//SampleData//";
	        	connectionPool cp=new connectionPool();
	        	String[] temp1=new String[18];
	        	String[] temp2=new String[18];
	        	TeamDAO team=new TeamDAO(cp);
	        	int j=0;
	        	for(int i=1;i<31;i++){
	        		j=0;
		        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename+i+".tec.csv"), "UTF-8");
		        	BufferedReader reader = new BufferedReader(isr);
		            String line = null; 
		            while((line=reader.readLine())!=null){ 
		                String item[] = line.split(",");//CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з� 	                
		                temp1[j]=item[0];
		                temp2[j]=item[2];
		                j++;
		            } 
		            team.insertTeamTable1(i, temp1);
		            team.insertTeamTable2(i, temp2);
	        	}
	        	cp.closeConnectionPool();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
	}
	/**
	 * ����������������ݿ�
	 * @throws Exception 
	 */
	class getRank implements Runnable{
		public void run(){
			Rank rank=new Rank();
			try {
				rank.getRank();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

