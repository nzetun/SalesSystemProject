package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mgg.Store;

import io.DBParser;
import item.Item;
import list.AdtList;
import list.AdtReport;
import list.CustomerComparator;
import list.GrandTotalComparator;
import list.StoreComparator;
import person.Person;
import sale.Sale;

public class GUI implements ActionListener{
	
	private JButton buttonReports;
	private JButton buttonSale;
	private JButton buttonStore;
	private JButton buttonPerson;
	private JButton buttonService;
	private JButton salesMadeButton;
	private JButton inventoryButton;
	
	private JLabel title;
	private JLabel title1;
	private JLabel title2;
	
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel panelBack;
	
	private JFrame frame;
	
	private List<Item> items;
	private List<Sale> sales;
	private List<Person> persons;
	private List<Store> stores;
	
	public GUI() {
		
		loadDatabaseApp();
		
		frame = new JFrame();
		
		//Panels
		topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createEmptyBorder(50, 800, 20, 20));
		topPanel.setLayout(new GridLayout(0, 1));
		topPanel.setBackground(Color.DARK_GRAY);
		
		panelLeft = new JPanel();
		panelLeft.setBorder(BorderFactory.createEmptyBorder());
		panelLeft.setLayout(new GridLayout(8, 1));
		panelLeft.setBackground(Color.WHITE);
		
		panelBack = new JPanel();
		panelBack.setBorder(BorderFactory.createEmptyBorder(20, 800, 20, 20));
		panelBack.setLayout(new GridLayout(1, 1));
		panelBack.setBackground(Color.BLACK);
		
		panelRight = new JPanel();
		panelRight.setLayout(null);
		panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelRight.setPreferredSize(new Dimension(800, 500));
		panelRight.setBackground(Color.LIGHT_GRAY);
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(800, 30));
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBackground(Color.BLACK);
		
		//Title
		title = new JLabel("MODERN GEEK GAMES");
		title.setPreferredSize(new Dimension(250, 100));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 28));
		title.setForeground(Color.WHITE);
		Border border1 = BorderFactory.createLineBorder(Color.BLUE);
		topPanel.setBorder(border1);
		topPanel.add(title);
		
		title2 = new JLabel("Click a button to begin");
		title2.setFont(new Font("Verdana", Font.BOLD, 16));
		title2.setForeground(Color.WHITE);
		title2.setBounds(300, 110, 200, 50);
		panelRight.add(title2);
		JLabel pic = loadGameImg();	
		panelRight.add(pic);
		
		title1 = new JLabel("Built by Noah Zetocha and Joel Bargen");
		title1.setPreferredSize(new Dimension(250, 30));
		title1.setHorizontalAlignment(JLabel.CENTER);
		title1.setFont(new Font("Verdana", Font.BOLD, 12));
		title1.setForeground(Color.WHITE);
		Border border2 = BorderFactory.createEmptyBorder();
		bottomPanel.setBorder(border2);
		bottomPanel.add(title1);
		
		//Buttons
		panelLeft.add(homeButton());
		panelLeft.add(saleButton());
		panelLeft.add(buttonReport());
		panelLeft.add(salesMade());
		panelLeft.add(inventoryButton());
		panelLeft.add(storeButton());

		panelLeft.add(personButton());
		
		buttonService = new JButton("Services");
		buttonService.setBackground(Color.WHITE);
		panelLeft.add(buttonService);
		
		
		/**
		 * add panel to frame, set what happens when frame is closed,
		 * set title of frame, set window to match a certain size,
		 * set window to be visible and in focus
		 */
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(panelLeft, BorderLayout.WEST);
		frame.add(panelRight, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Modern Geek Games");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public JButton homeButton() {
		JButton home = new JButton("Home");
		home.setBackground(Color.WHITE);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				panelRight.add(title2);
				panelRight.add(loadGameImg());
				buttonSale.setEnabled(true);
				buttonReports.setEnabled(true);
				salesMadeButton.setEnabled(true);
				buttonPerson.setEnabled(true);
				panelRight.revalidate();
				panelRight.repaint();
			}
		});
		return home;
	}
	
	public JButton saleButton() {
		buttonSale = new JButton("New Sale");
		buttonSale.setBackground(Color.WHITE);
		buttonSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				JTextArea ta = new JTextArea(5, 20);
				for(Item p : items) {
					ta.append(p.getName() + "\n");
				}
				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 400, 100);
				panelRight.add(sbrText);			
				
				JLabel userLabel = new JLabel("Search Item");
				userLabel.setBounds(10, 150, 80, 25);
				panelRight.add(userLabel);
				JTextField userText = new JTextField(20);
				userText.setBounds(100, 150, 165, 25);
				panelRight.add(userText);

				JButton enterButton = new JButton("Search");
				enterButton.setBounds(280, 152, 80, 20);
				JTextArea ta2 = new JTextArea(5, 20);
				ta2.setEditable(false);
				enterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(Item p : items) {
							if (p.getName().contains(userText.getText())) {
								ta2.append(p.getName() + ": " + Double.toString(p.getPrice()) + "\n");
								ta2.setBounds(550, 150, 200, 300);
//								SalesData.addSale(getPerson, null, null, null);
//								SalesData.addProductToSale(null, null, 2);
								panelRight.add(ta2);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
							}
						}
						panelRight.revalidate();
						panelRight.repaint();
					}
				});
				panelRight.add(enterButton);
				
				JLabel quantity = new JLabel("Enter Quantity");
				quantity.setBounds(10, 200, 80, 25);
				panelRight.add(quantity);
				JTextField userText1 = new JTextField(20);
				userText1.setBounds(100, 200, 165, 25);
				panelRight.add(userText1);
				
				JLabel total = new JLabel("Total");
				total.setBounds(10, 250, 80, 25);
				panelRight.add(total);
				JTextField userText2 = new JTextField(20);
				userText2.setBounds(100, 250, 165, 25);
				panelRight.add(userText2);
				
				panelRight.revalidate();
				panelRight.repaint();
				buttonSale.setEnabled(true);
				
			}
		});
		return buttonSale;
	}
	
	public JButton buttonReport() {
		buttonReports = new JButton("Simple Sale Reports");
		buttonReports.setBackground(Color.WHITE);
		buttonReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				
				AdtList<Sale> customerSort = AdtReport.sortReport(sales, new CustomerComparator());
				AdtList<Sale> totalSort = AdtReport.sortReport(sales, new GrandTotalComparator());
				AdtList<Sale> storeSort = AdtReport.sortReport(sales, new StoreComparator());
				
				JTextArea ta = new JTextArea(5, 20);
				ta.setEditable(false);
				ta.append(AdtReport.guiReport(customerSort, "Customer"));
				ta.append(AdtReport.guiReport(totalSort, "Total"));
				ta.append(AdtReport.guiReport(storeSort, "Store"));
				
				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 650,400);
				panelRight.add(sbrText);
				panelRight.revalidate();
				panelRight.repaint();
				
			}
		});
		return buttonReports;
	}
	
	public JButton salesMade() {
		salesMadeButton = new JButton("Sale History");
		salesMadeButton.setBackground(Color.WHITE);
		salesMadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				
				JTextArea ta = new JTextArea(5, 20);
				ta.setEditable(false);
				AdtList<Sale> totalSort = AdtReport.sortReport(sales, new GrandTotalComparator());
				ta.append(AdtReport.salesReport(totalSort));

				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 650,400);
				panelRight.add(sbrText);
				panelRight.revalidate();
				panelRight.repaint();
				
			}
		});
		return salesMadeButton;
	}
	
	public JButton personButton() {
		buttonPerson = new JButton("Persons");
		buttonPerson.setBackground(Color.WHITE);
		buttonPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				JTextArea ta = new JTextArea(5, 20);
				ta.setEditable(false);
				for(Person p : persons) {
					ta.append(p.personNameToString() + "\n");
				}
				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 397, 200);
				panelRight.add(sbrText);
				panelRight.revalidate();
				panelRight.repaint();
				
				JLabel userLabel = new JLabel("Search Person");
				userLabel.setBounds(10, 250, 100, 25);
				panelRight.add(userLabel);
				JTextField userText = new JTextField(20);
				userText.setBounds(150, 250, 165, 25);
				panelRight.add(userText);

				JButton enterButton = new JButton("Search");
				enterButton.setBounds(328, 252, 80, 20);
				JTextArea ta3 = new JTextArea(5, 20);
				ta3.setEditable(false);
				enterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ta3.selectAll();
						ta3.replaceSelection("");
						panelRight.revalidate();
						panelRight.repaint();
						for(Person p : persons) {
							if (p.getLastName().equalsIgnoreCase(userText.getText())) {
								ta3.append(p.personNameToString() + ": " + p.getType() + "\n");
								ta3.setBounds(10, 300, 397, 100);
								panelRight.add(ta3);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
							}
						}
						panelRight.revalidate();
						panelRight.repaint();
					}
				});
				panelRight.add(enterButton);
				buttonPerson.setEnabled(true);
			}
		});
		return buttonPerson;
	}
	
	public JButton inventoryButton() {
		inventoryButton = new JButton("Inventory");
		inventoryButton.setBackground(Color.WHITE);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				JTextArea ta = new JTextArea(5, 20);
				for(Item p : items) {
					ta.append(p.getName() + "\n");
				}
				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 400, 100);
				panelRight.add(sbrText);			
				
				JLabel userLabel = new JLabel("Search Item");
				userLabel.setBounds(10, 150, 80, 25);
				panelRight.add(userLabel);
				JTextField userText = new JTextField(20);
				userText.setBounds(100, 150, 165, 25);
				panelRight.add(userText);
				
				JButton enterButton = new JButton("Search");
				enterButton.setBounds(280, 152, 80, 20);
				JTextArea ta3 = new JTextArea(5, 20);
				ta3.setEditable(false);
				ta3.setBounds(550, 150, 200, 300);
				enterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(Item p : items) {
							if (p.getName().equalsIgnoreCase(userText.getText())) {
								ta3.append(p.getName() + ": " + Double.toString(p.getPrice()) + "\n");
								panelRight.add(ta3);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
							} else if (!p.getName().equalsIgnoreCase(userText.getText())){
								ta3.append("No Item Found\n");
								panelRight.add(ta3);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
								break;
							}
							
						}
						panelRight.revalidate();
						panelRight.repaint();
					}
				});
				panelRight.add(enterButton);
				panelRight.revalidate();
				panelRight.repaint();
				inventoryButton.setEnabled(true);
			}
		});
		return inventoryButton;
				
	}
	
	public JButton storeButton() {
		buttonStore = new JButton("Stores");
		buttonStore.setBackground(Color.WHITE);
		buttonStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRight.removeAll();
				panelRight.setLayout(null);
				JTextArea ta = new JTextArea(5, 20);
				for(Store s : stores) {
					ta.append(s.getStoreCode() + "\n" + s.getAddress().toString() + "\n" + s.getManager().personNameToString() + "\n\n");
				}
				JScrollPane sbrText = new JScrollPane(ta);
				sbrText.setBounds(10, 20, 400, 100);
				panelRight.add(sbrText);			
				
				JLabel userLabel = new JLabel("Search Stores");
				userLabel.setBounds(10, 150, 90, 25);
				panelRight.add(userLabel);
				JTextField userText = new JTextField(20);
				userText.setBounds(100, 150, 165, 25);
				panelRight.add(userText);
				
				JButton enterButton = new JButton("Search");
				enterButton.setBounds(280, 152, 80, 20);
				JTextArea ta3 = new JTextArea(5, 20);
				ta3.setEditable(false);
				ta3.setBounds(550, 150, 200, 300);
				enterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(Store s : stores) {
							if (s.getStoreCode().equalsIgnoreCase(userText.getText()) || 
									s.getManager().getLastName().equalsIgnoreCase(userText.getText())) {
								ta3.append(s.toString() + ": " + s.getManager().personNameToString() + "\n");
								panelRight.add(ta3);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
							} else if (!s.getStoreCode().equalsIgnoreCase(userText.getText()) || 
									!s.getManager().getLastName().equalsIgnoreCase(userText.getText())){
								ta3.append("No Store Found\n");
								panelRight.add(ta3);
								panelRight.revalidate();
								panelRight.repaint();
								enterButton.setEnabled(true);
								break;
							}
							
						}
						panelRight.revalidate();
						panelRight.repaint();
					}
				});
				panelRight.add(enterButton);
				panelRight.revalidate();
				panelRight.repaint();
				buttonStore.setEnabled(true);
			}
		});
		return buttonStore;
	}
	
	public JButton enterButton() {
//		JButton enterButton = new JButton("Persons");
//		enterButton.setBackground(Color.WHITE);
//		enterButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JScrollPane sbrText = new JScrollPane(ta);
//				panelRight.add(sbrText);
//				panelRight.revalidate();
//				panelRight.repaint();
//				buttonPerson.setEnabled(false);
//				
//				//panelRight.add(homeButton());
//			}
//		});
		return buttonPerson;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Sale> getSales() {
		return sales;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Person> getPersons() {
		return persons;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Store> getStores() {
		return stores;
	}
	
	/**
	 * 
	 * @return
	 */
	public void loadDatabaseApp() {
		JFrame load = ProgressBarDemo();
		items = DBParser.loadItemsDatabase();
		sales = DBParser.loadSalesDatabase();
		persons = DBParser.loadPersonDatabase();
		stores = DBParser.loadStoresDatabase();
		load.dispose();
	}
	
	public JFrame ProgressBarDemo() {
		JFrame frameLoad = new JFrame();
		JPanel panel = new JPanel();
        panel.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setIndeterminate(true);
        progressBar.setStringPainted(false);  
        
        JLabel label = new JLabel("Loading");
        label.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(progressBar);
        panel.add(label);
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setLayout(new GridLayout(6, 20));
        panel.setBackground(Color.WHITE);
        
        frameLoad.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(70, 20, 10, 20));
        frameLoad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLoad.setTitle("Modern Geek Games");
		frameLoad.pack();
		frameLoad.setLocationRelativeTo(null);
		frameLoad.setVisible(true);
		return frameLoad;
    }
	
	public JLabel loadGameImg() {
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("data/game.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(0, 0, 800, 500);
		return picLabel;
	}

}
