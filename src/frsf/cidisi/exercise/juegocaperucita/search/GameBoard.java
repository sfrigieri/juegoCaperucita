package frsf.cidisi.exercise.juegocaperucita.search;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class GameBoard {
	
	private JPanel panelAgente;
	private JFrame frameAgente;
	private JPanel panelAmbiente;
	private JFrame frameAmbiente;
	private JLabel [][] jLmatrixAgente;
	private JLabel [][] jLmatrixAmbiente;
	private int [][] matrixOtherTrees;
	private ImageIcon[] icons;
	private ImageIcon[] iconsOtherTrees;
	private Random generator;
	private int escenario;
	private int imageCount;
	//Inicializarlo en la clase
	public static int valorPrevioCeldaLobo= -1;
	
	public void initBoard(String games_name, int[][] matrix, int[] position, int escenarioAmbiente){
	
		generator = new Random();
		escenario =  escenarioAmbiente;
		jLmatrixAgente = new JLabel[9][14];
		matrixOtherTrees = this.getInitMatrix(escenarioAmbiente);
		frameAgente = new JFrame(games_name);
		icons = new ImageIcon[5];
		iconsOtherTrees = new ImageIcon[2];
		panelAgente = new JPanel(new GridLayout(9, 14, 0, 0));
	
		//asignar a iconsOtherTrees arbusto en 0 y pino amarillo en 1;
		//Setear matrixOtherTrees posiciones arbutos (0) y pinos amarillos (1), -1 para resto.
		
		//Siempre que valor sea ARBOL, consultar que icono de arbol corresponde segun escenario en esa posicion
		
		//Cuando se detecte valor Lobo en matriz, asignar icono lobo segun valorPrevioCeldaLobo
		//Si posicion lobo >columna 7. usar imagen izquierda, sino derecha. 
		//(crear listas con icons lobo derechos o izquierdos.)
		
		icons[0] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\LIBRE.jpg");
		icons[1] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\FANTASMAROJO.jpg");
		icons[2] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\FRUTILLA.jpg");
		icons[3] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\CEREZA.jpg");
		icons[4] = new ImageIcon("C:\\Users\\Santi\\Documents\\GitHub\\examples\\src\\frsf\\cidisi\\faia\\examples\\search\\pacman\\icons\\PACMAN.jpg");
		
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				jLmatrixAgente[row][col] = new JLabel();
				jLmatrixAgente[row][col].setHorizontalAlignment(JLabel.CENTER);
				//jLmatrix[row][col].setText("" + matrix[row][col]);
				if(row == 1 && col == 1)
					jLmatrixAgente[1][1].setIcon(icons[4]);
				if(matrix[row][col] == -1)
					jLmatrixAgente[row][col].setIcon(icons[0]);
				else 
					jLmatrixAgente[row][col].setIcon(icons[matrix[row][col] != 2 ? matrix[row][col] : 2+generator.nextInt(2)]);
				jLmatrixAgente[row][col].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				jLmatrixAgente[row][col].setFont(jLmatrixAgente[row][col].getFont().deriveFont(20f));
				panelAgente.add(jLmatrixAgente[row][col]);
			}
			

		}
		frameAgente.setContentPane(panelAgente);
		frameAgente.setSize(400, 400);
		frameAgente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAgente.setVisible(true);

		this.contentPaneToImage(frameAgente.getContentPane());
	}
	
	
	private int[][] getInitMatrix(int escenarioAmbiente) {
		return new int[9][14];
	}


	public void repaint(int[][] matrix, int[] position){


		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				//jLmatrix[row][col].setText("" + matrix[row][col]);
				if(matrix[row][col] == -1)
					jLmatrixAgente[row][col].setIcon(icons[0]);
				else 
					jLmatrixAgente[row][col].setIcon(icons[matrix[row][col]]);
			}
		}
		jLmatrixAgente[position[0]][position[1]].setIcon(icons[4]);

		this.contentPaneToImage(frameAgente.getContentPane());
	}

	
	public void contentPaneToImage(Container container) {
		
		 try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		Container c = container;
		BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		c.paint(im.getGraphics());
		++imageCount;
		try {
			ImageIO.write(im, "PNG", new File("C:\\Users\\Santi\\Desktop\\images\\shot"+imageCount+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}