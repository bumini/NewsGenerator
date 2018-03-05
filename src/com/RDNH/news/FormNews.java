package com.RDNH.news;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.RDNH.bean.LiveMessage;
import com.RDNH.bean.Player;
import com.RDNH.dao.AwayTeamDAO;
import com.RDNH.dao.HomeTeamDAO;
import com.RDNH.dao.LiveMessageDAO;
import com.RDNH.dao.RankDAO;
import com.RDNH.dao.TeamDAO;
import com.RDNH.helper.CreateTable;
import com.RDNH.helper.connectionPool;

public class FormNews {

	/**
	 * �������ű���
	 */
	public void title(LiveMessageDAO goal,TeamDAO team,RankDAO rank,int i) {
//		connectionPool cp = new connectionPool();
//		LiveMessageDAO goal = new LiveMessageDAO(cp);
//		TeamDAO team = new TeamDAO(cp);
//		RankDAO rank = new RankDAO(cp);
		String[] name = new String[2];
		int[] score = new int[2];
		int[] info1 = new int[3];
		int[] info2 = new int[3];
		name = team.getTeam(i);
		score = goal.getScore(i);
		info1 = rank.getRank(name[0]);
		info2 = rank.getRank(name[1]);
		if (info1[0] < info2[0]) {
			String temp;
			int temp1 = 0;

			temp = name[0];
			name[0] = name[1];
			name[1] = temp;

			temp1 = score[0];
			score[0] = score[1];
			score[1] = temp1;

			temp1 = info1[0];
			info1[0] = info2[0];
			info2[0] = temp1;

			temp1 = info1[1];
			info1[1] = info2[1];
			info2[1] = temp1;

			temp1 = info1[2];
			info1[2] = info2[2];
			info2[2] = temp1;
		}
		if (info1[0] != info2[0] && score[0] < score[1]) {
			System.out.println(name[1] + "��������" + name[0]);
		} else if (info1[0] != info2[0] && score[0] == score[1]) {
			System.out.println(name[0] + "��ս" + name[1] + "��" + score[0] + ":" + score[1] + "սƽ");
		} else if (info1[0] != info2[0] && score[0] > score[1]) {
			System.out.println(name[0] + "��" + score[0] + ":" + score[1] + "����" + name[1]);
		} else if (info1[0] == info2[0] && score[0] == score[1]) {
			System.out.println(name[0] + "�����޵�" + name[1] + "�ѷ�ʤ��");
		} else if (info1[0] == info2[0] && score[0] < score[1]) {
			System.out.println(name[1] + "С���汬����" + score[1] + ":" + score[0] + "����" + name[0]);
		} else if (info1[0] == info2[0] && score[0] < score[1]) {
			System.out.println(name[0] + "���޵�" + name[1] + "��" + score[0] + ":" + score[1] + "նɱ");
		}
	}

	/**
	 * ���һ��������ʱ����Ϣ
	 */
	public String getTime(ArrayList<String> text) {
		int i = 0;
		int min = 0;
		int max = 1;
		String time = null;
		for (i = 0; i < text.size(); i++) {
			if (text.get(i).contains("����ʱ��") == true) {
				time = text.get(i);
				break;
			}
		}
		if (time != null) {
			int m = 0;
			for (m = 0; m < time.length(); m++) {
				if (time.charAt(m) == '��') {
					min = m;
				} else if (time.contains("��") == true) {
					if (time.charAt(m) == '��') {
						max = m + 3;
						break;
					}
				} else if (time.contains("��") == true) {
					if (time.charAt(m) == '��') {
						max = m + 1;
						break;
					}
				} else if (time.contains("��") == true) {
					if (time.charAt(m) == '��') {
						max = m + 1;
						break;
					}
				} else {
					if (time.charAt(m) == '��' || time.charAt(m) == '��') {
						max = m + 1;
						break;
					}
				}
			}
			if (m == time.length())
				max = time.length();
			time = time.substring(min, max);
		}
		return time;
	}

	public String[] getInfo(ArrayList<String> text) {
		int i = 0;
		String[] info = new String[2];
		info[0] = null;
		info[1] = null;
		if (text != null) {
			for (i = 0; i < text.size(); i++) {
				if ((text.get(i).contains("Ӣ��") == true)) {
					info[0] = "Ӣ��";
					int min = 0;
					int max = 1;
					for (int j = 0; j < text.get(i).length(); j++) {
						if (text.get(i).charAt(j) == '��') {
							if (j < text.get(i).length() - 1 && text.get(i).charAt(j + 1) == '��') {
								min = j + 1;
							} else if (j < text.get(i).length() - 3 && text.get(i).charAt(j + 3) == '��')
								min = j + 3;
							else
								break;
						} else if (text.get(i).charAt(j) == '��') {
							max = j + 1;
							break;
						}
					}
					if (min != 0 && max != 1) {
						info[1] = text.get(i).substring(min, max);
						break;
					}
				} else if ((text.get(i).contains("����") == true)) {
					info[0] = "����";
					int min = 0;
					int max = 1;
					for (int j = 0; j < text.get(i).length(); j++) {
						if (text.get(i).charAt(j) == '��') {
							if (j < text.get(i).length() - 1 && text.get(i).charAt(j + 1) == '��') {
								min = j + 1;
							} else if (j < text.get(i).length() - 3 && text.get(i).charAt(j + 3) == '��')
								min = j + 3;
							else
								break;
						} else if (text.get(i).charAt(j) == '��') {
							max = j + 1;
							break;
						}
					}
					if (min != 0 && max != 1) {
						info[1] = text.get(i).substring(min, max);
						break;
					}
				} else if ((text.get(i).contains("��") == true && text.get(i).contains("��") == true)) {
					int min = 0;
					int max = 1;
					for (int j = 0; j < text.get(i).length(); j++) {
						if (text.get(i).charAt(j) == '��') {
							min = j;
						} else if (text.get(i).charAt(j) == '��') {
							max = j + 1;
							break;
						}
					}
					if (max - min < 6 && min != 0 && max != 1) {
						info[1] = text.get(i).substring(min, max);
					}
				} else if ((text.get(i).contains("ŷ��") == true)) {
					info[0] = "ŷ��";
					int min = 0;
					int max = 1;
					for (int j = 0; j < text.get(i).length(); j++) {
						if (text.get(i).charAt(j) == '��') {
							min = j + 1;
						} else if (text.get(i).charAt(j) == '��') {
							max = j + 1;
							break;
						}
					}
					if (max - min < 8) {
						info[1] = text.get(i).substring(min, max);
						break;
					}
				}
			}
		}
		return info;
	}

	/**
	 * ���ɵ�һ��
	 */
	public void firstSentence(LiveMessageDAO live,TeamDAO team,int i) {
//		connectionPool cp = new connectionPool();
//		LiveMessageDAO live = new LiveMessageDAO(cp);
//		TeamDAO team = new TeamDAO(cp);
		ArrayList<String> text = new ArrayList<String>();
		String[] name = new String[2];
		text = live.getTextFirst(i);
		name = team.getTeam(i);
		String time = getTime(text);
		String[] info = getInfo(text);
		if (time != null && info[0] != null && info[1] != null)
			System.out.println(time + "," + info[0] + info[1] + name[0] + "������ս" + name[1] + "��");
		else if (time == null && info[0] != null && info[1] != null)
			System.out.println(info[0] + info[1] + name[0] + "������ս" + name[1] + "��");
		else if (time == null && info[0] == null && info[1] != null)
			System.out.println("����" + name[0] + "������ս" + name[1] + "��");
		else if (time == null && info[0] != null && info[1] == null)
			System.out.println(info[0] + "����" + name[0] + "������ս" + name[1] + "��");
		else if (time != null && info[0] != null && info[1] == null)
			System.out.println(time + "," + info[0] + "����" + name[0] + "������ս" + name[1] + "��");
		else if (time != null && info[0] == null)
			System.out.println(time + "," + name[0] + "������ս" + name[1] + "��");
	}

	/**
	 * �õ�һ����ӵ�����Ա
	 * 
	 * @param team
	 * @return
	 */
	public String getGoalkeeper(ArrayList<Player> team) {
		String name = null;
		for (int i = 0; i < team.size(); i++) {
			if (team.get(i).getPlace().equals("�Ž�") == true) {
				name = team.get(i).getName();
				break;
			}
		}
		return name;
	}

	/**
	 * ���һ��������Ŵ������ļ�����Ա
	 * 
	 * @param team
	 * @return
	 */
	public ArrayList<String> getShootPlayer(ArrayList<Player> team) {
		ArrayList<String> player = new ArrayList<String>();
		for (int i = 0; i < team.size() - 1; i++) {
			for (int j = 0; j < team.size() - i - 1; j++) {
				if (team.get(j).getShoot() < team.get(j + 1).getShoot()) {
					Player tempName = new Player();
					tempName.copy(team.get(j));
					team.get(j).copy(team.get(j + 1));
					team.get(j + 1).copy(tempName);
				}
			}
		}
		int i = 0;
		while (i != team.size()) {
			player.add(team.get(i).getName());
			if (i > 3)
				break;
			i++;
		}
		return player;
	}

	/**
	 * ���һ����ӽ���ļ�����Ա
	 * 
	 * @param team
	 * @return
	 */
	public ArrayList<String> getGoalPlayer(ArrayList<Player> team) {
		ArrayList<String> player = new ArrayList<String>();
		for (int i = 0; i < team.size(); i++) {
			if (team.get(i).getGoal() != 0) {
				player.add(team.get(i).getName());
			}
		}
		return player;
	}

	/**
	 * ���ɵڶ��ε����Ÿſ�
	 */
	public void secondSentence(TeamDAO team,LiveMessageDAO goal,AwayTeamDAO awayTeam,HomeTeamDAO homeTeam,int i) {
//		connectionPool cp = new connectionPool();
//		TeamDAO team = new TeamDAO(cp);
//		LiveMessageDAO goal = new LiveMessageDAO(cp);
//		AwayTeamDAO awayTeam = new AwayTeamDAO(cp);
//		HomeTeamDAO homeTeam = new HomeTeamDAO(cp);
		ArrayList<Player> away = new ArrayList<Player>();
		ArrayList<Player> home = new ArrayList<Player>();
		int[] score = new int[2];
		int[] shoot = new int[2];
		String[] name = new String[2];
		shoot = team.getTeamShoots(i);
		name = team.getTeam(i);
		score = goal.getScore(i);
		away = awayTeam.getAwayTeam(i);
		home = homeTeam.getHomeTeam(i);
		ArrayList<String> shootplayer = new ArrayList<String>();
		ArrayList<String> goalplayer = new ArrayList<String>();
		if (score[0] != score[1]) {
			if (score[0] > score[1]) {
				goalplayer = getGoalPlayer(home);
				for (int j = 0; j < goalplayer.size(); j++)
					System.out.print(goalplayer.get(j) + ",");
				System.out.print("��������������ն��");
				System.out.println(name[0] + "������" + score[0] + "-" + score[1] + "սʤ" + name[1] + "��");
			} else {
				goalplayer = getGoalPlayer(away);
				for (int j = 0; j < goalplayer.size(); j++)
					System.out.print(goalplayer.get(j) + ",");
				System.out.print("�ͳ����ӳ�ɫ��Ϊ��ӽ���");
				System.out.println(name[0] + "������" + score[0] + "-" + score[1] + "����" + name[1] + "��");
			}
		} else if (score[0] == score[1]) {
			if (shoot[0] > shoot[1]) {
				String goalkeeper = getGoalkeeper(away);
				System.out.print("ȫ������" + name[0] + "���Ŵ������" + shoot[0] + "��,");
				shootplayer = getShootPlayer(home);
				for (int j = 0; j < shootplayer.size(); j++)
					System.out.print(shootplayer.get(j) + ",");
				System.out.print("���м��ѵĽ�����ᣬ");
				System.out.println("����" + name[1] + "�Ž�" + goalkeeper + "���ֳ�ɫ" + name[0] + "����ֻ�ܽ���" + score[0] + "-"
						+ score[1] + "�Ľ��" + "��");
			} else if (shoot[0] < shoot[1]) {
				String goalkeeper = getGoalkeeper(home);
				System.out.print("ȫ������" + name[1] + "���Ŵ������" + shoot[1] + "��,");
				shootplayer = getShootPlayer(away);
				for (int j = 0; j < shootplayer.size(); j++)
					System.out.print(shootplayer.get(j) + ",");
				System.out.println("���л������,����" + name[0] + "�Ž�" + goalkeeper + "���ֳ�ɫ" + name[1] + "����ֻ�ܽ���" + score[1]
						+ "-" + score[0] + "�Ľ��" + "��");
			} else {
				System.out.print("ȫ������" + name[1] + "���Ŵ������" + shoot[1] + "��,");
				shootplayer = getShootPlayer(away);
				for (int j = 0; j < shootplayer.size(); j++)
					System.out.print(shootplayer.get(j) + ",");
				shootplayer = getShootPlayer(home);
				for (int j = 0; j < shootplayer.size(); j++)
					System.out.print(shootplayer.get(j) + ",");
				System.out.println("���л������,����" + "˫���Ž�" + "���ֳ�ɫ" + name[1] + "��" + "�����Ժ�" + "��");
			}
		}
	}

	/**
	 * �������ʱ��ֱ���ı�
	 */
	public void getShootText(LiveMessageDAO live,int game) {
//		connectionPool cp = new connectionPool();
//		LiveMessageDAO live = new LiveMessageDAO(cp);
		ArrayList<LiveMessage> message = new ArrayList<LiveMessage>();
		message = live.getTextBody(game);
		int i = 0;
		try {
			String filename = "//Volumes//Transcend//Java//RDNH//dictionary.txt";
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			ArrayList<String> line = new ArrayList<String>();
			line.add(reader.readLine());
			while (line.get(i) != null) {
				i++;
				line.add(reader.readLine());
			}
			for (int j = 0; j < message.size(); j++) {
				for (int m = 0; m < line.size() - 1; m++) {
					String temp1 = message.get(j).getText();
					String temp2 = line.get(m);
					if (temp1.contains(temp2) == true) {
						if (message.get(j).getStart() == 1) {
							System.out.println("�ϰ볡����" + message.get(j).getTime() + "����," + message.get(j).getText());
						} else if (message.get(j).getStart() == 2) {
							System.out.println("�°볡����" + message.get(j).getTime() + "����," + message.get(j).getText());
						}
						j++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �������ŵ����岿��
	 */
	public void body(LiveMessageDAO live,int i) {
		getShootText(live,i);
	}

	/**
	 * �������һ�ε���Ա��Ϣ
	 */
	public void lastSentence(AwayTeamDAO awayTeam,HomeTeamDAO homeTeam,TeamDAO team,int i) {
//		connectionPool cp = new connectionPool();
//		AwayTeamDAO awayTeam = new AwayTeamDAO(cp);
//		HomeTeamDAO homeTeam = new HomeTeamDAO(cp);
		String[] name = new String[2];
//		TeamDAO team = new TeamDAO(cp);
		ArrayList<Player> away = new ArrayList<Player>();
		ArrayList<Player> home = new ArrayList<Player>();
		String lastAway = null;
		String lastHome = null;
		lastAway = null;
		lastHome = null;
		name = team.getTeam(i);
		away = awayTeam.getAwayTeam(i);
		home = homeTeam.getHomeTeam(i);
		lastAway = name[0] + "�׷���";
		lastHome = name[1] + "�׷���";
		for (int j = 0; j < away.size(); j++) {
			if (away.get(j).isFirst() == 1) {
				if (away.get(j).getTime() == 90) {
					lastAway = lastAway + away.get(j).getNumber() + "-" + away.get(j).getName() + ";";
				} else {
					for (int k = 0; k < away.size(); k++) {
						if (away.get(k).getTime() + away.get(j).getTime() == 90) {
							lastAway = lastAway + away.get(j).getNumber() + "-" + away.get(j).getName() + "("
									+ away.get(k).getTime() + "'," + away.get(k).getName() + ")" + ";";
							break;
						}
					}
				}
			}
		}
		for (int j = 0; j < home.size(); j++) {
			if (home.get(j).isFirst() == 1) {
				if (home.get(j).getTime() == 90) {
					lastHome = lastHome + home.get(j).getNumber() + "-" + home.get(j).getName() + ";";
				} else {
					for (int k = 0; k < home.size(); k++) {
						if (home.get(k).getTime() + home.get(j).getTime() == 90) {
							lastHome = lastHome + home.get(j).getNumber() + "-" + home.get(j).getName() + "("
									+ home.get(k).getTime() + "'," + home.get(k).getName() + ")" + ";";
							break;
						}
					}
				}
			}
		}
		System.out.println(lastAway);
		System.out.println(lastHome);
		System.out.println();
	}

}
