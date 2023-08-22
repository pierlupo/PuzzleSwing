package org.example;

/* Classe exécutable, créee toutes les classes nécésssaires, puis démarre le jeu. */


public class Puzzle {
	public static void main(String[] args)
	{
		Model model = new Model(3);

		ViewMenuBar viewMenuBar = new ViewMenuBar();
		ViewTopBar viewTopBar = new ViewTopBar(model.getScore().getBestScore());

		View view = new View(viewMenuBar, viewTopBar, model.getLvl());
		ViewDisplay viewDisplay = new ViewDisplay(view, model);

		ControlKey controlKey = new ControlKey(model, viewDisplay, viewTopBar);
		ControlButtonRestart controlButtonRestart = new ControlButtonRestart(model, view);
		ControlButtonScore controlButtonScore = new ControlButtonScore(model, viewDisplay);
		ControlMenu controlMenu = new ControlMenu(model, view, viewTopBar);

		viewTopBar.setRestartActionListener(controlButtonRestart);
		viewTopBar.setScoreActionListener(controlButtonScore);

		view.setKeyListener(controlKey);
		viewMenuBar.setLvlItemActionListener(controlMenu);
		viewDisplay.display();
	}
}
