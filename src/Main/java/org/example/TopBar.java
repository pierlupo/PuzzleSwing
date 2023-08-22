package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;


public class TopBar {	/*
	 * @time : le temps écoulé
	 * @record : le meilleur score
	 * @restart : un bouton permettant de recommencer
	 * @score : un bouton permettant d'afficher les meilleurs scores
	 */

	private JLabel time;
	private JLabel record;
	private JButton restart;
	private JButton score;

	public TopBar()
	{
		restart = new JButton("Réinitialiser");
		score = new JButton("Meilleurs scores");
		setRecord(0);
		setTime(0);
	}

	public void setRestartActionListener(ActionListener actionListener)
	{
		restart.addActionListener(actionListener);
	}

	public void setScoreActionListener(ActionListener actionListener)
	{
		score.addActionListener(actionListener);
	}

	public void setTime(double time)
	{
		this.time = new JLabel("Temps : " + time);
	}

	public void setRecord(double record)
	{
		this.record = new JLabel("Record : " + record);
	}

	public JLabel getTime()
	{
		return this.time;
	}

	public JLabel getRecord()
	{
		return this.record;
	}

	public JButton getRestart()
	{
		return this.restart;
	}

	public JButton getScore()
	{
		return this.score;
	}
}
