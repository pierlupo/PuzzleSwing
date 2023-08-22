package org.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ViewDisplay extends JPanel {
	private View view;
	private Model model;
	Timer timer;

	/**
	 * Constructeur de ViewDisplay
	 *
	 * @param view : Un objet View
	 * @param model : Un objet Model
	 */

	public ViewDisplay(View view, Model model)
	{
		super();

		this.view = view;
		this.model = model;
	}

	/* Dessine l'image sur l'écran, sauf la case ayant pour valeur 0. */

	protected void paintComponent(Graphics g) {
		int i, caseN, nbRect = model.getNbRect();
		int order[] = model.getOrder();
		BufferedImage img = view.getImg();
		Rectangle[] rect = view.getRect();

		super.paintComponent(g);

		for(i = 0; i < nbRect; i++)
		{
			caseN = order[i];

			if(caseN != 0)
			{
				g.drawImage(img, rect[i].x, rect[i].y, rect[i].width, rect[i].height,
				rect[caseN].x, rect[caseN].y, rect[caseN].width, rect[caseN].height, this);
			}
		}
	}

	/* Affiche toute l'interface. */

	public void display() {
		JPanel globalPan = new JPanel(new BorderLayout());
		JPanel topBarPan = new JPanel(new GridLayout(1, 4));

		view.topBar.setTime(model.getScore().getScore());

		topBarPan.add(view.topBar.getRecord());
		topBarPan.add(view.topBar.getTime());
		topBarPan.add(view.topBar.getRestart());
		topBarPan.add(view.topBar.getScore());

		globalPan.add(topBarPan, BorderLayout.NORTH);
		globalPan.add(this);

		view.setContentPane(globalPan);
		view.setVisible(true);
	}

	/* Commence le timer permettant d'afficher la fenêtre. */

	public void startDisplay() {
		timer = new Timer(60, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				display();
			}
		});
		timer.start();
	}

	/* Créee une JOptionPane affichant les meilleurs scores s'il y en a. */

	public void displayBestScores(){
		JOptionPane scorePan = new JOptionPane();
		String[] scores = model.score.getAllScores();

		if(scores != null){
			int i, nbScores = model.score.getNbScores(scores);
			String text = "Records :\n";

			for (i = 0; i < nbScores; i++)
				text = text + (i + 1) + " - " + scores[i] + "\n";

			scorePan.showMessageDialog(view, text, "Records", JOptionPane.INFORMATION_MESSAGE);
		}
		else
			scorePan.showMessageDialog(view, "Pas de scores", "Records", JOptionPane.INFORMATION_MESSAGE);

		scorePan.createDialog(view, "Records");
	}

	/* Affiche la boite de dialogue permettant d'entrer son score. */

	public void displaySaveScore(){
		JOptionPane saveScorePan = new JOptionPane();
		String pseudo;

		pseudo = saveScorePan.showInputDialog(view, "Votre pseudo : ");
		saveScorePan.createDialog(view, "Score");

		if(pseudo != null && pseudo.isEmpty() == false)
			model.score.save(pseudo);
	}

	/* Affiche le score dans une fenêtre. */

	public void displayNoNewRecord() {
		JOptionPane scorePan = new JOptionPane();

		scorePan.showMessageDialog(view, "Score : " + model.getScore().getScore() + "\nPas de nouveau record.", "Records", JOptionPane.INFORMATION_MESSAGE);
		scorePan.createDialog(view, "Records");
	}
}
