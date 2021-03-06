/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class MatrixWindow extends JFrame {
    
    JButton matOneBtn;
    JButton matTwoBtn;
    JLabel requestK;
    JTextField fieldK;
    JButton sendKBtn;
    
    public MatrixWindow() {
		setLayout(null);
		setSize(200,280);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Adyacencia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();

		setVisible(true);
    }
    
    private void init() {

	matOneBtn = new JButton("Grafo uno");
	matOneBtn.setSize(175, 50);
	matOneBtn.setLocation(10, 10);
	matOneBtn.setFocusable(false);
	matOneBtn.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		sendOne();
	    }
	});
	add(matOneBtn);

	matTwoBtn = new JButton("Grafo dos");
	matTwoBtn.setSize(175, 50);
	matTwoBtn.setLocation(10, 80);
	matTwoBtn.setFocusable(false);
	matTwoBtn.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		sendTwo();
	    }
	});
	add(matTwoBtn);
	
	requestK = new JLabel("K:");
	requestK.setSize(50, 30);
	requestK.setLocation(10, 140);
	requestK.setFocusable(false);
	add(requestK);

	fieldK = new JTextField();
	fieldK.setSize(90, 30);
	fieldK.setLocation(30, 140);
	add(fieldK);

	sendKBtn = new JButton("Resolver K");
	sendKBtn.setSize(175, 50);
	sendKBtn.setLocation(10, 180);
	sendKBtn.setFocusable(false);
	sendKBtn.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		requestK();
	    }
	});
	add(sendKBtn);
    }
    
    
    public void sendOne() {
	Vertex[] V = Vertex.createVertexArray("A", "B", "C", "D", "E", "F", "G", "H");
	int[][] C = {
	    {0, 1, 1, 0, 0, 1, 0, 0},
	    {1, 0, 1, 0, 0, 0, 0, 0},
	    {1, 1, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 1, 1, 0, 1},
	    {0, 0, 0, 1, 0, 0, 1, 1},
	    {1, 0, 0, 1, 0, 0, 0, 1},
	    {0, 0, 0, 0, 1, 0, 0, 1},
	    {0, 0, 0, 1, 1, 1, 1, 0},
	};

	Handler.createGraph(V,C);
    }
    
    public void sendTwo() {
	Vertex[] V = Vertex.createVertexArray("A", "B", "C", "D", "E", "F", "G", "H", "I");
	int[][] C = {
	//   A  B  C  D  E  F  G  H  I
	    {0, 1, 1, 1, 0, 1, 0, 0, 0}, // A
	    {1, 0, 1, 1, 0, 1, 0, 0, 0}, // B
	    {1, 1, 0, 1, 0, 1, 0, 0, 0}, // C
	    {1, 1, 1, 0, 1, 1, 1, 0, 0}, // D
	    {0, 0, 0, 1, 0, 1, 1, 0, 0}, // E
	    {1, 1, 1, 1, 1, 0, 1, 1, 0}, // F
	    {0, 0, 0, 1, 1, 1, 0, 1, 1}, // G
	    {0, 0, 0, 0, 0, 1, 1, 0, 1}, // H
	    {0, 0, 0, 0, 0, 0, 1, 1, 0}, // I
	};

	Handler.createGraph(V,C);
    }
    
    public void requestK() {
//	Handler.G.minimumGraphFor(Integer.parseInt(fieldK.getText()));
	Handler.G.maximumGraphFor(Integer.parseInt(fieldK.getText()));
    }
    
    
    
	/*
    JButton processBtn;

    JButton addVertexBtn;
    JButton resetBtn;

    JTable adyacency;
	
	JLabel requestK;
	JTextField fieldK;
	
	boolean didChanges;

    public MatrixWindow() {
		setLayout(null);
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Adyacencia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();

		setVisible(true);
    }
	
    private void init() {

		didChanges = false;
		
		adyacency = new JTable();
		adyacency.setModel(new TableModel(new String[] {"Nombre"},0));
		adyacency.setRowHeight(30);
		adyacency.getColumnModel().getColumn(0).setPreferredWidth(140);
		adyacency.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		adyacency.getTableHeader().setReorderingAllowed(false);
		adyacency.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleValue();
			}
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
		});

		JScrollPane scroll = new JScrollPane(adyacency);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setSize(600, 559);
		scroll.setLocation(1,1);
		add(scroll);

		addVertexBtn = new JButton("Agregar Vertice");
		addVertexBtn.setSize(175, 50);
		addVertexBtn.setLocation(605, 37);
		addVertexBtn.setFocusable(false);
		addVertexBtn.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				addVertex();
			}
		});
		add(addVertexBtn);
		
		requestK = new JLabel("K:");
		requestK.setSize(50, 30);
		requestK.setLocation(605, 150);
		requestK.setFocusable(false);
		add(requestK);
		
		fieldK = new JTextField();
		fieldK.setSize(100, 30);
		fieldK.setLocation(625, 150);
		add(fieldK);
		

		resetBtn = new JButton("Reset");
		resetBtn.setSize(175, 50);
		resetBtn.setLocation(605, 90);
		resetBtn.setFocusable(false);
		resetBtn.addActionListener(new ActionListener( ) {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				reset();
		    }
		});
		add(resetBtn);

		processBtn = new JButton("Resolver");
		processBtn.setSize(175, 60);
		processBtn.setLocation(605, 400);
		processBtn.setFocusable(false);
		processBtn.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				   sendGraph();
				} catch(Exception ex) { }
			}
		});
		add(processBtn);
    }
	
	private void reset() {
		didChanges = true;
		adyacency.setModel(new TableModel(new String[] {"Nombre"},0));
	}
	
    private void addVertex() {
		didChanges = true;
		String name = generateName(adyacency.getRowCount());
	    DefaultTableModel model = (DefaultTableModel) adyacency.getModel();

	    Object[] columnData = new Object[adyacency.getRowCount()];
	    for (int i = 0; i < columnData.length; i++) {
			columnData[i] = "0";
	    }
	    model.addColumn(name,columnData);

	    Object[] rowData = new Object[adyacency.getColumnCount()];
	    for (int i = 1; i < rowData.length; i++) {
			rowData[i] = "0";
	    }
	    rowData[0] = name;
	    model.addRow(rowData);
    }
	
	private String generateName(int id) {
		String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
							"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		String result = "";
		ArrayList<Integer> digits = new ArrayList<>();
		digits.add(0);
		int disposable = id;
		
		while (disposable > 0) {
			disposable--;
			digits.set(0, digits.get(0)+1);
			int i = 0;
			while (digits.get(i) >= 26) {
				digits.set(i, digits.get(i)-26);
				if (digits.size() == i+1) {
					digits.add(0);
				}
				digits.set(i+1, digits.get(i+1)+1);
			}
		}
		
		for (int i = 0; i < digits.size(); i++) {
			result += letters[digits.get(i)];
		}
		
		return result;
	}
	
	private void toggleValue() {
		didChanges = true;
		int x = adyacency.getSelectedColumn();
		int y = adyacency.getSelectedRow();
		int curValue = Integer.parseInt((String)adyacency.getValueAt(y,x));
		adyacency.setValueAt(""+(curValue+1)%2,y,x);
		adyacency.setValueAt(""+(curValue+1)%2,x-1,y+1);
	}
    
    private void sendGraph() {
		if (didChanges) {
			DefaultTableModel model = (DefaultTableModel) adyacency.getModel();
			String[] names = new String[model.getRowCount()];
			for (int i = 0; i < model.getRowCount(); i++) {
				names[i] = (String)adyacency.getValueAt(i,0);
			}

			Vertex[] V = Vertex.createVertexArray(names);
			int[][] C = new int[model.getRowCount()][model.getRowCount()];

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getRowCount(); j++) {
					C[i][j] = Integer.parseInt((String)adyacency.getValueAt(i,j+1));
				}
			}

			Handler.createGraph(V,C);
			didChanges = false;
		} else {
			Handler.G.minimumGraphFor(Integer.parseInt(fieldK.getText()));
		}
    }
	
    private class TableModel extends DefaultTableModel {

		public TableModel(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
    }
    */
}