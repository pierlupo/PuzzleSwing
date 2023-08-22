package org.example;

import java.awt.*;
import java.util.Random;


public class Model {
	protected int lvl;
	protected int nbRect;
	protected int[] order;
	protected ModelScore score;

	public static int LVL_MIN = 2;
	public static int LVL_MAX = 12;

	/**
	 * Constructeur de Model. Appele la méthode init().
	 *
	 * @param lvl : Le niveau de difficulté
	 */

	public Model(int lvl)
	{
		init(lvl);
	}

	/**
	 * Initialise les attributs de la classe.
	 *
	 * @param lvl : Le niveau de difficulté
	 */

	public void init(int lvl)
	{
		if(lvl < LVL_MIN)
			this.lvl = LVL_MIN;
		else if(lvl > LVL_MAX)
			this.lvl = LVL_MAX;
		else
			this.lvl = lvl;

		nbRect = lvl * lvl;
		order = new int[nbRect];
		score = new ModelScore(lvl);

		setOrder();
		mix();
	}

	/* Retourne le niveau de difficultée. */

	public int getLvl()
	{
		return lvl;
	}

	/* Retourne le nombre de rectangles du puzzle. */

	public int getNbRect()
	{
		return nbRect;
	}

	/* Retourne le tableau contenant l'ordre des cases. */

	public int[] getOrder()
	{
		return order;
	}

	/* Retourne l'objet ModelScore score, contenant toutes les informations sur les scores .*/

	public ModelScore getScore()
	{
		return score;
	}

	/* Permet d'ordonner le puzzle. */

	public void setOrder()
	{
		int i;

		for(i = 0; i < nbRect; i++)
			order[i] = i;
	}

	/* Mélange le puzzle. */

	public void mix() {
		int i, caseN, randN, nbPermutation = nbRect * nbRect;
		final int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
		Random rand = new Random();
		Point pos;

		for(i = 0; i < 10; i++) {
			pos = getCasePos(0);
			caseN = pos.x * lvl + pos.y;

			if(pos.x == 0) {
				if(pos.y == 0)
					randN = rand.nextInt(DOWN - RIGHT + 1) + RIGHT;
				else if(pos.y == lvl - 1)
					randN = rand.nextInt(RIGHT - UP + 1) + UP;
				else
					randN = rand.nextInt(DOWN - UP + 1) + UP;
			}
			else if(pos.x == lvl - 1) {
				if(pos.y == 0)
					randN = rand.nextInt(LEFT - DOWN + 1) + DOWN;
				else {
					randN = rand.nextInt(LEFT - UP + 1) + UP;

					if(randN == RIGHT)
						randN = UP;
					else if(pos.y == lvl - 1)
						randN = LEFT;
				}
			}
			else {
				if(pos.y == 0)
					randN = rand.nextInt(LEFT - RIGHT + 1) + RIGHT;
				else {
					randN = rand.nextInt(LEFT - UP + 1) + UP;

					if(pos.y == lvl - 1 && randN == DOWN)
						randN = UP;
				}
			}

			if(randN == LEFT)
				permute(caseN, (pos.x - 1) * lvl + pos.y);
			else if(randN == RIGHT)
				permute(caseN, (pos.x + 1) * lvl + pos.y);
			else if(randN == UP)
				permute(caseN, caseN - 1);
			else
				permute(caseN, caseN + 1);

		}
	}

	/* Vérifie si le puzzle est ordonné. */

	public boolean isOrdered()
	{
		int i;

		for(i = 0; i < nbRect; i++)
		{
			if(order[i] != i)
				return false;
		}

		return true;
	}

	/**
	 * Permute deux cases du puzzle.
	 *
	 * @param index1 : Le numéro de la première case
	 * @param index2 : Le numéro de la seconde case
	 */

	public void permute(int index1, int index2)
	{
		int val = order[index1];

		order[index1] = order[index2];
		order[index2] = val;
	}

	/**
	 * Retourne la position d'une case sous la forme d'un objet Point.
	 *
	 * @param n : La valeur de la case
	 */

	public Point getCasePos(int n) {
		int i, j;

		for(j = 0; j < lvl; j++)
		{
			for(i = 0; i < lvl; i++)
			{
				if(order[i * lvl + j] == n)
					return (new Point(i, j));
			}
		}

		return null;
	}

	/* Démarre le timer de score. */

	public void startScoreTimer()
	{
		score.startTimer();
	}

	/* Arrête le timer de score. */

	public void stopScoreTimer()
	{
		score.stopTimer();
	}
}
