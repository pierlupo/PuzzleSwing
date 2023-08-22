package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlMenu implements ActionListener {
	private Model model;
	private View view;
	private ViewTopBar viewTopBar;

	/**
	 * Constructeur de ControlMenu
	 *
	 * @param model : Un objet Model
	 * @param view: Un objet View
	 *
	 */

	public ControlMenu(Model model, View view, ViewTopBar viewTopBar) {
		this.model = model;
		this.view = view;
		this.viewTopBar = viewTopBar;
	}

	/**
	 * Change de difficulté selon l'option choisie.
	 *
	 * @param actionEvent : Permet de connaître l'option sélectionée
	 */

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		int i;

		for(i = Model.LVL_MIN; i <= Model.LVL_MAX; i++){
			if(actionEvent.getActionCommand().equals(i + "x" + i))
			{
				model.init(i);
				view.initRect(i);
				viewTopBar.setRecord(model.getScore().getBestScore());
				break;
			}
		}
	}
}
