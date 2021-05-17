package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.DBParser;
import list.AdtList;
import list.AdtReport;
import list.CustomerComparator;
import list.GrandTotalComparator;
import list.StoreComparator;
import sale.Sale;

public class GUI implements ActionListener{
	
	private JButton buttonReports;
	private JButton buttonSale;
	private JLabel label1;
	private JLabel title;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel topPanel;
	private JFrame frame;
	
	public GUI() {
		
		frame = new JFrame();
		
		topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createEmptyBorder(50, 800, 20, 20));
		topPanel.setLayout(new GridLayout(5, 20));
		topPanel.setBackground(Color.DARK_GRAY);
		
		title = new JLabel("MODERN GEEK GAMES");
		topPanel.add(title);
		
		panelLeft = new JPanel();
		panelLeft.setBorder(BorderFactory.createEmptyBorder(200, 400, 250, 250));
		panelLeft.setLayout(new GridLayout(5, 20));
		panelLeft.setBackground(Color.WHITE);
		
		panelRight = new JPanel();
		panelRight.setBorder(BorderFactory.createEmptyBorder(200, 400, 250, 250));
		panelRight.setLayout(new GridLayout(5, 20));
		panelRight.setBackground(Color.LIGHT_GRAY);
		
		buttonSale = new JButton("New Sale");
		panelLeft.add(buttonSale);
		
		buttonReports = new JButton("Simple Sale Reports");
		buttonReports.addActionListener(this);
		panelLeft.add(buttonReports);
		
		label1 = new JLabel("MGG Sale Reports");
		panelLeft.add(label1);
		
		
		
		/**
		 * add panel to frame, set what happens when frame is closed,
		 * set title of frame, set window to match a certain size,
		 * set window to be visible and in focus
		 */
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(panelLeft, BorderLayout.WEST);
		frame.add(panelRight, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Modern Geek Games");
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		List<Sale> sales = DBParser.loadSalesDatabase();
		AdtList<Sale> customerSort = AdtReport.sortReport(sales, new CustomerComparator());
		AdtList<Sale> totalSort = AdtReport.sortReport(sales, new GrandTotalComparator());
		AdtList<Sale> storeSort = AdtReport.sortReport(sales, new StoreComparator());
		AdtReport.printReport(customerSort, "Customer");
		AdtReport.printReport(totalSort, "Total");
		AdtReport.printReport(storeSort, "Store");
	}
	
}
