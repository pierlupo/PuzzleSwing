package org.example;


import javax.swing.*;


public class MenuBar extends JMenuBar {	/*
	 * @lvlMenu : le menu des niveaux
	 * @imgMenu : le menu d'image
	 * @lvlItem : les JMenuItem de @lvlMenu
	 * @imgItem : les JMenuItem de @imgItem
	 */

	private JMenu lvlMenu;
	private JMenu imgMenu;
	private JMenuItem[] lvlItem;
	private JMenuItem[] imgItem;

	private static final int NB_LVL_ITEM = Model.LVL_MAX - Model.LVL_MIN + 1;
	private static final int NB_IMG_ITEM = 2;

	public MenuBar()
	{
		super();

		int i;
		String lvlLabel; String[] imgLabel = {"Choisir", "Importer"};

		/* Initialisation des variables d'instance */

		this.lvlMenu = new JMenu("Niveau");
		this.imgMenu = new JMenu("Image");

		this.lvlItem = new JMenuItem[this.NB_LVL_ITEM];
		this.imgItem = new JMenuItem[this.NB_IMG_ITEM];

		/* Remplissage des this.lvlItem[] par le niveau */

		for(i = 0; i < this.NB_LVL_ITEM; i++)
		{
			lvlLabel = (i + Model.LVL_MIN) + "";

			this.lvlItem[i] = new JMenuItem(lvlLabel+ "x" + lvlLabel);
			this.lvlMenu.add(this.lvlItem[i]);
		}

		/* Remplissage des this.imgItem[] */

		for(i = 0; i < this.NB_IMG_ITEM; i++)
		{
			this.imgItem[i] = new JMenuItem(imgLabel[i]);
			this.imgMenu.add(this.imgItem[i]);
		}

		/* Ajout des menus à la JMenuBar */

		super.add(this.lvlMenu);
		super.add(this.imgMenu);
	}
}
