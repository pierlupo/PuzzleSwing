package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelScore
{
	private double score;
	private double bestScore;
	private double worstScore;
	private String scoreFile;
	private Timer timer;

	public static int NB_SCORES_MAX = 10;

	/**
	 * Constructeur de ModelScore.
	 *
	 * @param lvl : Le niveau de difficultée
	 */

	public ModelScore(int lvl)
	{
		scoreFile = ".scores/" + lvl + "x" + lvl;

		setBestScore();
		setWorstScore();
	}

	/*
	 * Retourne un objet BufferedReader.
	 *
	 * @param file : Le nom du fichier
	 */

	private BufferedReader getBufferedReader(String file)
	{
		try
		{
			return new BufferedReader(new FileReader(new File(file)));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Retourne un objet BufferedWriter.
	 *
	 * @param file : Le nom du fichier
	 */

	private BufferedWriter getBufferedWriter(String file)
	{
		try
		{
			return new BufferedWriter(new FileWriter(file, true));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/* Vérifie l'existence d'un fichier.
	 *
	 * @param file : Le nom du fichier
	 */

	private boolean fileExists(String file)
	{
		return new File(file).exists();
	}

	/*
	 * Créer un nouveau fichier.
	 *
	 * @param file : Le nom du fichier
	 */

	private void createFile(String file)
	{
		try
		{
			new BufferedWriter(new FileWriter(new File(file)));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/* Créee le dossier contenant les scores. */

	private void createScoreDir()
	{
		if(!fileExists(".scores/"))
			new File(".scores/").mkdir();
	}

	/* Créee un fichier contenant des scores. */

	private void createScoreFile()
	{
		if(!fileExists(scoreFile))
			createFile(scoreFile);
	}

	/*
	 * Retourne le nombre de scores sauvegardés.
	 *
	 * @param score : Un tableau de taille NB_SCORES_MAX
	 *                contenant tout les scores
	 */

	public int getNbScores(String[] scores)
	{
		if(scores == null)
			return 0;

		int i;

		for(i = 0; i < NB_SCORES_MAX && scores[i] != null; i++);

		return i;
	}

	/* Obtient le meilleur score depuis le fichier de sauvegarde. */

	public void setBestScore()
	{
		if(!fileExists(scoreFile))
			bestScore = 0;
		else
		{
			BufferedReader reader = getBufferedReader(scoreFile);

			try
			{
				String bestScore = reader.readLine();
				Pattern pattern = Pattern.compile(" (.*$)");
				Matcher matcher = pattern.matcher(bestScore);

				matcher.find();

				this.bestScore = Double.parseDouble(matcher.group(1));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/* Obtient le plus mauvais score. */

	public void setWorstScore()
	{
		if(!fileExists(scoreFile))
			worstScore = 0;
		else
		{
			BufferedReader reader = getBufferedReader(scoreFile);

			try
			{
				String worstScore;

				while((worstScore = reader.readLine()) != null)
				{
					Pattern pattern = Pattern.compile(" (.*$)");
					Matcher matcher = pattern.matcher(worstScore);

					matcher.find();

					this.worstScore = Double.parseDouble(matcher.group(1));
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/* Retourne bestScore. */

	public double getBestScore()
	{
		return bestScore;
	}

	/*
	 * Affection une nouvelle valeur à score
	 *
	 * @param score : Le nouveau score.
	 */

	private void setScore(double score)
	{
		this.score = score;
	}

	public double getScore()
	{
		return score;
	}

	public double getWorstScore()
	{
		return worstScore;
	}

	/* Retourne les NB_SCORES_MAX scores. */

	public String[] getAllScores()
	{
		if(!fileExists(scoreFile))
			return null;

		BufferedReader reader = getBufferedReader(scoreFile);

		try
		{
			String line;
			String[] scores = new String[NB_SCORES_MAX];
			int i;

			for(i = 0; i <= NB_SCORES_MAX && (line = reader.readLine()) != null; i++)
				scores[i] = line;

			return scores;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Vérifie si un score existe déjà.
	 *
	 * @param score : Le score à comparer
	 */

	public boolean scoreExists(double score)
	{
		String[] scores = getAllScores();

		if(scores != null)
		{
			int i, nbScores = getNbScores(scores);

			for(i = 0; i < nbScores; i++)
			{
				Pattern pattern = Pattern.compile(" (.*$)");
				Matcher matcher = pattern.matcher(scores[i]);

				matcher.find();

				if(Double.parseDouble(matcher.group(1)) == score) return true;
			}
		}

		return false;
	}

	/*
	 * Sauvegarde un score s'il est suffisament élevé.
	 *
	 * @param pseudo : Le pseudo du joueur
	 * @param score : Le score du joueur
	 */

	public void save(String pseudo)
	{
		createScoreDir();
		createScoreFile();

		/*
		 * insertLine est initialisé à -1 pour indiquer que l'on n'insère pas
		 * le nouveau score dans le milieu du fichier.
		 */

		String[] scores = getAllScores();
		int i, insertLine = -1, nbScores = getNbScores(scores);

		for(i = 0; i < nbScores && insertLine == -1; i++)
		{
			Pattern pattern = Pattern.compile(" (.*$)");
			Matcher matcher = pattern.matcher(scores[i]);

			matcher.find();

			double curScore = Double.parseDouble(matcher.group(1));

			if(curScore == score)
				insertLine = -2;
			else if(curScore > score)
				insertLine = i;
		}

		if(i - 1 == insertLine && i != 0)
		{
			createFile(scoreFile);

			try
			{
				BufferedWriter writer = getBufferedWriter(scoreFile);

				for(i = 0; i <= nbScores && i < NB_SCORES_MAX; i++)
				{
					if(i == insertLine)
						writer.write(pseudo + " " + score);
					else if(i > insertLine)
						writer.write(scores[i - 1]);
					else
						writer.write(scores[i]);

					writer.newLine();
				}

				writer.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else if(nbScores < NB_SCORES_MAX && score != worstScore)
		{
			try
			{
				BufferedWriter writer = getBufferedWriter(scoreFile);

				writer.write(pseudo + " " + score);
				writer.newLine();
				writer.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/* Démarre le timer permettant de calculer le score. */

	public void startTimer()
	{
		timer = new Timer(100, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				double score = (double)((int)((getScore() + 0.1) * 10.0 + .5)) / 10.0;

				setScore(score);
			}
		});

		timer.start();
	}

	/* Arrête le timer. */

	public void stopTimer()
	{
		timer.stop();
	}
}
