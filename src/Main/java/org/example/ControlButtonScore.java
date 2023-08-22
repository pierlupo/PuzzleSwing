package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlButtonScore implements ActionListener
{
	private Model model;
	private ViewDisplay viewDisplay;

	/**
	 * Constructeur de ControlButtonScore
	 *
	 * @param model : Un objet Model
	 * @param viewDisplay : Un objet ViewDisplay
	 *
	 */

	public ControlButtonScore(Model model, ViewDisplay viewDisplay) {
		this.model = model;
		this.viewDisplay = viewDisplay;
	}

	/**
	 * Appele la fonction affichant les meilleurs scores.
	 *
	 * @param actionEvent : paramètre nécessaire pour la ré-écriture de la méthode,
	 *                      mais il n'est pas utilisé
	 */

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		viewDisplay.displayBestScores();
	}
}
