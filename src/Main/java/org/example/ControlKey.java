package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlKey implements KeyListener {
	private ViewDisplay viewDisplay;
	private ViewTopBar viewTopBar;
	private Model model;

	/**
	 * Constructeur de ControlKey.
	 *
	 * @param model : Une instance d'un objet Model
	 * @param viewDisplay : Une instance d'un objet viewDisplay
	 */

	public ControlKey(Model model, ViewDisplay viewDisplay, ViewTopBar viewTopBar) {
		this.model = model;
		this.viewDisplay = viewDisplay;
		this.viewTopBar = viewTopBar;
	}

	/**
	 * Fonction permettant de déplacer les cases.
	 *
	 * @param keyCode : Le code associé à la touche appuyée
	 *
	 */

	public void move(int keyCode) {
		Point pos = model.getCasePos(0);
		int case_n = pos.x * model.lvl + pos.y;

		if (keyCode == KeyEvent.VK_UP && pos.y < model.lvl - 1)
			model.permute(case_n, case_n + 1);
		else if (keyCode == KeyEvent.VK_DOWN && pos.y > 0)
			model.permute(case_n, case_n - 1);
		else if (keyCode == KeyEvent.VK_LEFT && pos.x < model.lvl - 1)
			model.permute(case_n, (pos.x + 1) * model.lvl + pos.y);
		else if (keyCode == KeyEvent.VK_RIGHT && pos.x > 0)
			model.permute(case_n, (pos.x - 1) * model.lvl + pos.y);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	/**
	 * Fonction appelée lors de l'appui sur une touche du clavier.
	 * Appelle la fonction move si c'est une touche directionelle,
	 * permet également de commencer et d'arrêter la partie si le puzzle est résolu.
	 *
	 * @param keyEvent : La variable contenant les informations sur la touche
	 *
	 */

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();

		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN ||
		keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
		{
			move(keyEvent.getKeyCode());

			if(model.getScore().getScore() == 0)
			{
				model.startScoreTimer();
				viewDisplay.startDisplay();
			}

			if(model.isOrdered())
			{
				model.stopScoreTimer();

				double score = model.getScore().getScore();
				double bestScore = model.getScore().getBestScore();
				double worstScore = model.getScore().getWorstScore();
				int nbScore = model.getScore().getNbScores(model.getScore().getAllScores());

				if(model.getScore().scoreExists(score) == false &&
				(bestScore == 0 || score < bestScore || score < worstScore || nbScore < ModelScore.NB_SCORES_MAX))
						viewDisplay.displaySaveScore();
				else
					viewDisplay.displayNoNewRecord();

				model.init(model.getLvl());
				model.mix();
				viewTopBar.setRecord(model.getScore().getBestScore());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}
}
