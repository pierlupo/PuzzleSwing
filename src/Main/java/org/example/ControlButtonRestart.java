package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlButtonRestart implements ActionListener {
	Model model;
	View view;

	/**
	 * Constructeur de ControlButtonRestart.
	 *
	 * @param model : Un objet Model
	 * @param view : Un objet View
	 *
	 */

	public ControlButtonRestart(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Réinitialise le jeu.
	 *
	 * @param actionEvent : paramètre nécessaire pour la ré-écriture de la méthode,
	 *                      mais il n'est pas utilisé
	 */

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		model.init(model.getLvl());
		model.mix();
	}
}
