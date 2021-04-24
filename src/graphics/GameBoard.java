package graphics;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import frsf.cidisi.exercise.juegocaperucita.search.CaperucitaAgentPerception;

public class GameBoard {

	private JPanel panel;
	private JFrame frame;
	private JLabel [][] jLmatrix;
	private ImageIcon[] icons;
	private int escenario;
	private int imageCount;
	private String frameName;
	private boolean esAgente;
	private int valorPrevioCeldaLobo= -1;
	MergedIcons merger;


	public void initBoard(String board_name, int[][] matrix, int[] agPosition, int escenarioAmbiente, boolean esAgState){

		merger = new MergedIcons();
		escenario =  escenarioAmbiente;
		esAgente = esAgState;
		jLmatrix = this.getInitJLmatrix();
		frame = new JFrame(board_name);
		frameName = board_name;
		this.initIcons();
		panel = new JPanel(this.getInitGridLayout());

		//asignar a iconsOtherTrees arbusto en 0 y pino amarillo en 1;
		//Setear matrixOtherTrees posiciones arbutos (0) y pinos amarillos (1), -1 para resto.

		//Siempre que valor sea ARBOL, consultar que icono de arbol corresponde segun escenario en esa posicion

		//Cuando se detecte valor Lobo en matriz, asignar icono lobo segun valorPrevioCeldaLobo


		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				jLmatrix[row][col] = new JLabel();
				jLmatrix[row][col].setHorizontalAlignment(JLabel.CENTER);
				jLmatrix[row][col].setIcon(icons[matrix[row][col]+1]);
				jLmatrix[row][col].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				jLmatrix[row][col].setFont(jLmatrix[row][col].getFont().deriveFont(20f));
				panel.add(jLmatrix[row][col]);
			}

		}

		jLmatrix[agPosition[0]][agPosition[1]].setIcon(icons[6]);


		frame.setContentPane(panel);
		frame.setSize(matrix[0].length*57,matrix.length*62);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(this.esAgente)
			frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		this.contentPaneToImage(frame.getContentPane(),board_name);
	}


	private LayoutManager getInitGridLayout() {
		return new GridLayout(9, 14, 0, 0);
	}


	private JLabel[][] getInitJLmatrix() {
		return new JLabel[9][14];
	}

	public int getValorPrevioCeldaLobo() {
		return valorPrevioCeldaLobo;
	}


	public void setValorPrevioCeldaLobo(int valorPrevioCeldaLobo) {
		this.valorPrevioCeldaLobo = valorPrevioCeldaLobo;
	}


	private void initIcons() {
		icons = new ImageIcon[9];
		icons[0] = new ImageIcon(getClass().getResource("resources/images/NO_VISIBLE.jpg"));
		icons[1] = new ImageIcon(getClass().getResource("resources/images/LIBRE.jpg"));
		icons[2] = new ImageIcon(getClass().getResource("resources/images/PINO.jpg"));
		icons[3] = new ImageIcon(getClass().getResource("resources/images/DULCE.jpg"));
		icons[4] = new ImageIcon(getClass().getResource("resources/images/LOBO_TRANS.png"));
		icons[5] = new ImageIcon(getClass().getResource("resources/images/FLORES.jpg"));
		icons[6] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_FRENTE.jpg"));
		icons[7] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_FLORES.jpg"));
		icons[8] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_TRANS.png"));
	}


	private int[][] getInitMatrix(int escenarioAmbiente) {
		return new int[9][14];
	}


	public void repaint(int[][] matrix, int[] position){


		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if(!jLmatrix[row][col].getIcon().equals(icons[matrix[row][col]+1])) {

					if(matrix[row][col] == CaperucitaAgentPerception.LOBO) {
						try {
							BufferedImage newImg = merger.merge(((ImageIcon) jLmatrix[row][col].getIcon()).getImage(), icons[4].getImage());
							jLmatrix[row][col].setIcon(new ImageIcon(newImg));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
						jLmatrix[row][col].setIcon(icons[matrix[row][col]+1]);
				}
			}
		}
		if(matrix[position[0]][position[1]] == CaperucitaAgentPerception.LIBRE)
			jLmatrix[position[0]][position[1]].setIcon(icons[6]);
		else
			if(matrix[position[0]][position[1]] == CaperucitaAgentPerception.FLORES) {
				jLmatrix[position[0]][position[1]].setIcon(icons[7]);
			}
			else {
				try {
					BufferedImage newImg = merger.merge(((ImageIcon) jLmatrix[position[0]][position[1]].getIcon()).getImage(), icons[8].getImage());
					jLmatrix[position[0]][position[1]].setIcon(new ImageIcon(newImg));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		this.contentPaneToImage(frame.getContentPane(),frameName);
	}


	public void contentPaneToImage(Container container, String board_name) {

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Container c = container;
		BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		c.paint(im.getGraphics());
		++imageCount;
		try {
			File outputFile =  new File(getClass().getResource("").toString().substring(6)+"resources/"+board_name+"/shot"+imageCount+".png".replaceFirst("/","\\'"));
			outputFile.getParentFile().mkdirs();
			ImageIO.write(im, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}