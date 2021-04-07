package frsf.cidisi.exercise.juegocaperucita.search;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class FrameTest {
	
	private JPanel panel;
	private JFrame f;
	private JLabel [][] jLmatrix;
	private ImageIcon[] icons;
	private Random generator;

	
	public void initBoard(String games_name, int[][] matrix, int[] position){
	
		generator = new Random();
		jLmatrix = new JLabel[4][4];
		f = new JFrame(games_name);
		icons = new ImageIcon[5];
		panel = new JPanel(new GridLayout(4, 4, 1, 1));

		icons[0] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\LIBRE.jpg");
		icons[1] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\FANTASMAROJO.jpg");
		icons[2] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\FRUTILLA.jpg");
		icons[3] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\CEREZA.jpg");
		icons[4] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\PACMAN.jpg");
		
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				jLmatrix[row][col] = new JLabel();
				jLmatrix[row][col].setHorizontalAlignment(JLabel.CENTER);
				//jLmatrix[row][col].setText("" + matrix[row][col]);
				if(row == 1 && col == 1)
					jLmatrix[1][1].setIcon(icons[4]);
				if(matrix[row][col] == -1)
					jLmatrix[row][col].setIcon(icons[0]);
				else 
					jLmatrix[row][col].setIcon(icons[matrix[row][col] != 2 ? matrix[row][col] : 2+generator.nextInt(2)]);
				jLmatrix[row][col].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				jLmatrix[row][col].setFont(jLmatrix[row][col].getFont().deriveFont(20f));
				panel.add(jLmatrix[row][col]);
			}
			

		}
		f.setContentPane(panel);
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	
	
	public void repaint(int[][] matrix, int[] position){


		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				//jLmatrix[row][col].setText("" + matrix[row][col]);
				if(matrix[row][col] == -1)
					jLmatrix[row][col].setIcon(icons[0]);
				else 
					jLmatrix[row][col].setIcon(icons[matrix[row][col]]);
			}
		}
		jLmatrix[position[0]][position[1]].setIcon(icons[4]);

	}
}