package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewTopBar {
	private JLabel time;
	private JLabel record;
	private JButton restart;
	private JButton score;

	/**
	 * Constructeur de ViewTopBar, créer les boutons et initialise les labels.
	 *
	 * @param bestScore : Le meilleur score.
	 */

	public ViewTopBar(double bestScore){
		restart = new JButton("Réinitialiser");
		score = new JButton("Meilleurs scores");
		setRecord(bestScore);
		setTime(0);
	}

	/* Ajoute un AtionListener au bouton restart */

	public void setRestartActionListener(ActionListener actionListener)
	{
		restart.addActionListener(actionListener);
	}

	/* Ajoute un ActionListener au bouton score. */

	public void setScoreActionListener(ActionListener actionListener)
	{
		score.addActionListener(actionListener);
	}

	/**
	 * Créee un nouvel objet JLabel pour la variable time, l'initialisant également.
	 *
	 * @param time : Le temps de jeu
	 */

	public void setTime(double time)
	{
		this.time = new JLabel("Temps : " + time);
	}

	/**
	 * Créee un nouvel objet JLabel pour la variable record, l'initialisant également.
	 *
	 * @param record: Le meilleur temps de jeu
	 */

	public void setRecord(double record)
	{
		if(record == 0)
			this.record = new JLabel("Record : ?");
		else
			this.record = new JLabel("Record : " + record);
	}

	/* Retourne time. */

	public JLabel getTime()
	{
		return this.time;
	}

	/* Retourne record. */

	public JLabel getRecord()
	{
		return this.record;
	}

	/* Retourne restart. */

	public JButton getRestart()
	{
		return this.restart;
	}

	/* Retourne score. */

	public JButton getScore()
	{
		return this.score;
	}
}
