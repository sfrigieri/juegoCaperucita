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
import frsf.cidisi.exercise.juegocaperucita.search.CaperucitaState;

public class GameBoard {

	private JPanel panel;
	private JFrame frame;
	private JLabel [][] jLmatrix;
	private JLabel[][] jLleftColumn;
	private JLabel[][] jLrightColumn;
	private ImageIcon[] icons;
	private int imageCount;
	private String frameName;
	MergedIcons merger;


	public void initBoard(String board_name, CaperucitaState agState){

		int[] agPosition = agState.getPosicion();
		int[][] matrix = agState.getMapaBosque();

		this.initIcons();
		merger = new MergedIcons();

		frame = new JFrame(board_name);
		frameName = board_name;

		panel = new JPanel(this.getGridLayoutAgente());
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		jLmatrix = this.getInitJLmatrix();
		this.initLeftColumn(agState);
		this.initRightColumn(agState);

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {

				if(col == 0) 
					panel.add(jLleftColumn[row][0]);

				jLmatrix[row][col] = new JLabel();
				jLmatrix[row][col].setHorizontalAlignment(JLabel.CENTER);
				jLmatrix[row][col].setIcon(icons[matrix[row][col]+1]);
				jLmatrix[row][col].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				//jLmatrix[row][col].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				panel.add(jLmatrix[row][col]);

				if(col == (matrix[0].length-1))
					panel.add(jLrightColumn[row][0]);

			}

		}

		jLmatrix[agPosition[0]][agPosition[1]].setIcon(icons[6]);


		frame.setContentPane(panel);
		//frame.setSize(matrix[0].length*57,matrix.length*62);
		frame.setSize(frame.getPreferredSize());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		this.contentPaneToImage(frame.getContentPane(),board_name);
	}



	private void initLeftColumn(CaperucitaState agState) {

		jLleftColumn =  new JLabel[9][1];

		for (int row = 0; row < jLleftColumn.length; row++) {
			for (int col = 0; col < jLleftColumn[0].length; col++) {
				jLleftColumn[row][col] = new JLabel();
				jLleftColumn[row][col].setHorizontalAlignment(JLabel.CENTER);
				jLleftColumn[row][col].setIcon(icons[0]);
				jLleftColumn[row][col].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			}
		}

		jLleftColumn[0][0].setIcon(icons[9]);
		jLleftColumn[1][0].setIcon(icons[10]);
		jLleftColumn[2][0].setIcon(icons[15]);

	}



	private void initRightColumn(CaperucitaState agState) {

		jLrightColumn =  new JLabel[9][1];

		for (int row = 0; row < jLrightColumn.length; row++) {
			for (int col = 0; col < jLrightColumn[0].length; col++) {
				jLrightColumn[row][col] = new JLabel();
				jLrightColumn[row][col].setHorizontalAlignment(JLabel.CENTER);
				jLrightColumn[row][col].setIcon(icons[0]);
				jLrightColumn[row][col].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			}
		}

		jLrightColumn[7][0].setIcon(icons[16]);
		jLrightColumn[8][0].setIcon(icons[17]);

	}



	public void initBoard(String board_name, int[][] matrix, int[] agPosition){

		merger = new MergedIcons();
		jLmatrix = this.getInitJLmatrix();
		frame = new JFrame(board_name);
		frameName = board_name;
		this.initIcons();
		panel = new JPanel(this.getGridLayoutAmbiente());
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				jLmatrix[row][col] = new JLabel();
				jLmatrix[row][col].setHorizontalAlignment(JLabel.CENTER);
				jLmatrix[row][col].setIcon(icons[matrix[row][col]+1]);
				jLmatrix[row][col].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				panel.add(jLmatrix[row][col]);
			}

		}

		jLmatrix[agPosition[0]][agPosition[1]].setIcon(icons[6]);


		frame.setContentPane(panel);
		frame.setSize(frame.getPreferredSize());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		this.contentPaneToImage(frame.getContentPane(),board_name);
	}


	private LayoutManager getGridLayoutAgente() {
		return new GridLayout(9, 16, 0, 0);
	}

	private LayoutManager getGridLayoutAmbiente() {
		return new GridLayout(9, 14, 0, 0);
	}

	private JLabel[][] getInitJLmatrix() {
		return new JLabel[9][14];
	}



	private void initIcons() {
		icons = new ImageIcon[21];
		icons[0] = new ImageIcon(getClass().getResource("resources/images/NO_VISIBLE.jpg"));
		icons[1] = new ImageIcon(getClass().getResource("resources/images/LIBRE.jpg"));
		icons[2] = new ImageIcon(getClass().getResource("resources/images/PINO.jpg"));
		icons[3] = new ImageIcon(getClass().getResource("resources/images/DULCE.jpg"));
		icons[4] = new ImageIcon(getClass().getResource("resources/images/LOBO_TRANS.png"));
		icons[5] = new ImageIcon(getClass().getResource("resources/images/FLORES.jpg"));
		icons[6] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_FRENTE.jpg"));
		icons[7] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_FLORES.jpg"));
		icons[8] = new ImageIcon(getClass().getResource("resources/images/CAPERUCITA_TRANS.png"));
		icons[9] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA0_0.jpg"));
		icons[10] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA1_0o1.jpg"));
		icons[11] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA0_1.jpg"));
		icons[12] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA0_2o3.jpg"));
		icons[13] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA1_2.jpg"));
		icons[14] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA1_3.jpg"));
		icons[15] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_VIDAS_FILA2.jpg"));
		icons[16] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_DULCES_FILA7.jpg"));
		icons[17] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_DULCES_FILA8_0.jpg"));
		icons[18] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_DULCES_FILA8_1.jpg"));
		icons[19] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_DULCES_FILA8_2.jpg"));
		icons[20] = new ImageIcon(getClass().getResource("resources/images/CONTADOR_DULCES_FILA8_3.jpg"));

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


	public void repaint(CaperucitaState agState){

		int[] position = agState.getPosicion();
		int[][] matrix = agState.getMapaBosque();
		int vidasPerdidas = agState.getVidasPerdidas();
		int dulcesPorJuntar = agState.getDulcesPorJuntar();


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
		
		//System.out.println("vidas perdidas: "+vidasPerdidas);
		if(vidasPerdidas > 0) {
			if(vidasPerdidas == 1) {
				jLleftColumn[0][0].setIcon(icons[11]);
				jLleftColumn[1][0].setIcon(icons[10]);
			}
			else if(vidasPerdidas == 2) {
				jLleftColumn[0][0].setIcon(icons[12]);
				jLleftColumn[1][0].setIcon(icons[13]);
			}
			else {
				jLleftColumn[0][0].setIcon(icons[12]);
				jLleftColumn[1][0].setIcon(icons[14]);
			}
		}

		if(dulcesPorJuntar == 3) {
			jLrightColumn[8][0].setIcon(icons[17]);
		}
		else if(dulcesPorJuntar == 2) {
			jLrightColumn[8][0].setIcon(icons[18]);
		}
		else if(dulcesPorJuntar == 1) {
			jLrightColumn[8][0].setIcon(icons[19]);
		}
		else {
			jLrightColumn[8][0].setIcon(icons[20]);
		}

		this.contentPaneToImage(frame.getContentPane(),frameName);
	}


	public void contentPaneToImage(Container container, String board_name) {

		try {
			TimeUnit.MILLISECONDS.sleep(500);
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