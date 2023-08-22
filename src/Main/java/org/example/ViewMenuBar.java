package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewMenuBar extends JMenuBar {
	private JMenu lvlMenu;
	private JMenuItem[] lvlItem;

	private static final int NB_LVL_ITEM = Model.LVL_MAX - Model.LVL_MIN + 1;

	/* Constructeur de ViewMenuBar, créee le menu déroulant. */

	public ViewMenuBar() {
		super();

		int i;
		String lvlLabel;

		lvlMenu = new JMenu("Niveau");
		lvlItem = new JMenuItem[NB_LVL_ITEM];

		for(i = 0; i < NB_LVL_ITEM; i++)
		{
			lvlLabel = (i + Model.LVL_MIN) + "";

			lvlItem[i] = new JMenuItem(lvlLabel+ "x" + lvlLabel);
			lvlMenu.add(lvlItem[i]);
		}

		super.add(lvlMenu);
	}

	/* Ajoute un ActionListener à chaque élément de lvlItem. */

	public void setLvlItemActionListener(ActionListener actionListener)
	{
		int i;

		for(i = 0; i < NB_LVL_ITEM; i++)
			lvlItem[i].addActionListener(actionListener);
	}
}
