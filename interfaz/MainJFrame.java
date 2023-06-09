/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Package interfaz that includes the class MainJFrame, class CreateJPanel and DisplayPanel.
 * 
 * @author 
 * @version 3.0
 */
package interfaz;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import business.Person;

/**
 * Class MainJFrame
 * 
 * Creates the main panel where main buttons are included to give
 * functionalities to program.
 * 
 * @author ankit
 * @version 3.0
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Declaration of object person of class Person
     */
    Person person;

    /**
     * Represents personal code of person. Used to casting from int to String to
     * show it on textfield.
     */
    String pNumber;

    /**
     * Represents an index used on button "Next" to go next profiles.
     */
    int indexNext = 0;

    /**
     * Represents an index used on button "Back" to go back profiles.
     */
    int indexBack = 0;

    /**
     * Initialize all components included on MainFrame panel.
     */
    public MainJFrame() {
	initComponents(person);
	this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    /**
     * Initializes all components included on this panel.
     */
    private void initComponents(Person person) {

	jSplitPane1 = new javax.swing.JSplitPane();
	controlJPanel = new javax.swing.JPanel();
	displayJPanel = new javax.swing.JPanel();
	createPerson = new javax.swing.JButton();
	viewPerson = new javax.swing.JButton();
	loadProfileBtn = new javax.swing.JButton();
	saveProfileBtn = new javax.swing.JButton();
	backButton = new javax.swing.JButton();
	nextButton = new javax.swing.JButton();
	firstButton = new javax.swing.JButton();
	lastButton = new javax.swing.JButton();

	createPerson.setText("Create Profile");
	createPerson.setFont(new Font("Silom", Font.BOLD, 15));
	createPerson.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		createPersonActionPerformed(evt);
	    }
	});

	viewPerson.setText("Display Profile");
	viewPerson.setFont(new Font("Silom", Font.BOLD, 15));
	viewPerson.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		viewPersonActionPerformed(evt);
	    }
	});

	/**
	 * Generates event to load profiles from external file to list (profileList) and
	 * shows first profile on DisplayPanel.
	 */
	loadProfileBtn.setText("Load Profiles");
	loadProfileBtn.setFont(new Font("Silom", Font.BOLD, 15));
	loadProfileBtn.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		firstButton.setEnabled(true);
		lastButton.setEnabled(true);

		try {

		    FileReader reader = new FileReader("login.txt");
		    BufferedReader br = new BufferedReader(reader);
		    Scanner sc = new Scanner(br);

		    while (sc.hasNext()) {

			int pCode = sc.nextInt();
			String name = sc.next();
			String geoData = sc.next();
			String dateOfBirth = sc.next();
			String phone = sc.next();
			String email = sc.next();
			String socialSecurNum = sc.next();
			String bankAccNum = sc.next();
			String dateOfStart = sc.next();
			String department = sc.next();
			String urlImage = sc.next();

			CreateJPanel.getProfileList().add(new Person(name, geoData, dateOfBirth, phone, email,
				socialSecurNum, bankAccNum, dateOfStart, department, urlImage, pCode));

		    }
		    br.close();

		    Person a = CreateJPanel.getProfileList().get(indexBack);

		    pNumber = Integer.toString(a.getPersonalCode());
		    DisplayJPanel.perCodeTextField.setText(pNumber);
		    DisplayJPanel.nameTextField.setText(a.getName());
		    DisplayJPanel.geoTextField.setText(a.getGeographic_data());
		    DisplayJPanel.dobTextField.setText(a.getDob());
		    DisplayJPanel.phoneTextField.setText(a.getPhone());
		    DisplayJPanel.emailTextField.setText(a.getEmail());
		    DisplayJPanel.ssnTextField.setText(a.getSsn());
		    DisplayJPanel.bankTextField.setText(a.getBankAccNum());
		    DisplayJPanel.dosTextField.setText(a.getDateOfStart());
		    DisplayJPanel.departmTextField.setText(a.getDepart());
		    indexBack--;
		    indexNext = indexBack + 2;

		    String imageTxt = a.getImageTxt();
		    BufferedImage img = ImageIO.read(new File(imageTxt));
		    Image dimg = img.getScaledInstance(DisplayJPanel.imageLabel.getWidth(),
			    DisplayJPanel.imageLabel.getHeight(), Image.SCALE_SMOOTH);
		    ImageIcon icon = new ImageIcon(dimg);
		    DisplayJPanel.imageLabel.setIcon(icon);

		    JOptionPane.showMessageDialog(null, "Profiles loaded successfully!!");

		} catch (IOException e) {
		    System.out.println("ERROR: It was not possible to write from file");
		} catch (NumberFormatException e1) {
		    System.out.println("ERROR2: It was not possible to make a casting");
		} catch (InputMismatchException e2) {
		    System.out.println("ERROR3: There are more lines to read");
		}

	    }

	});

	/**
	 * Generates event to save on external file all the elements to list
	 * (profileList).
	 */
	saveProfileBtn.setText("Save Profiles");
	saveProfileBtn.setFont(new Font("Silom", Font.BOLD, 15));
	saveProfileBtn.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		if (!CreateJPanel.getProfileList().isEmpty()) {

		    try {
			FileWriter writer = new FileWriter("login.txt");
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter pw = new PrintWriter(bw);
			Iterator<Person> iter = CreateJPanel.getProfileList().iterator();

			while (iter.hasNext()) {

			    Person p = iter.next();

			    pNumber = Integer.toString(p.getPersonalCode());
			    pw.write(pNumber + "\n");
			    pw.write(p.getName() + "\n");
			    pw.write(p.getGeographic_data() + "\n");
			    pw.write(p.getDob() + "\n");
			    pw.write(p.getPhone() + "\n");
			    pw.write(p.getEmail() + "\n");
			    pw.write(p.getSsn() + "\n");
			    pw.write(p.getBankAccNum() + "\n");
			    pw.write(p.getDateOfStart() + "\n");
			    pw.write(p.getDepart() + "\n");
			    pw.write(p.getImageTxt() + "\n");

			}
			pw.close();
			indexBack=0;
			indexNext = indexBack + 2;
			CreateJPanel.getProfileList().clear();
			JOptionPane.showMessageDialog(null, "Profiles saved successfully!!");

		    } catch (IOException e) {
			System.out.println("ERROR: It was not possible to save");
		    }

		} else {
		    JOptionPane.showMessageDialog(null,
			    "There is not personal profiles to save. Please add from create profile button.");

		}
	    }
	});

	/**
	 * Generates event to show personal profiles back to back until beginning of
	 * list (profilelist). If there is no more profiles a JOptionPane advise showing
	 * a message.
	 */
	backButton.setText("Back");
	backButton.setFont(new Font("Silom", Font.BOLD, 15));
	backButton.setEnabled(false);
	backButton.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		try {

		    if (indexBack >= 0) {

			Person a = CreateJPanel.getProfileList().get(indexBack);

			pNumber = Integer.toString(a.getPersonalCode());
			DisplayJPanel.perCodeTextField.setText(pNumber);
			DisplayJPanel.nameTextField.setText(a.getName());
			DisplayJPanel.geoTextField.setText(a.getGeographic_data());
			DisplayJPanel.dobTextField.setText(a.getDob());
			DisplayJPanel.phoneTextField.setText(a.getPhone());
			DisplayJPanel.emailTextField.setText(a.getEmail());
			DisplayJPanel.ssnTextField.setText(a.getSsn());
			DisplayJPanel.bankTextField.setText(a.getBankAccNum());
			DisplayJPanel.dosTextField.setText(a.getDateOfStart());
			DisplayJPanel.departmTextField.setText(a.getDepart());
			indexBack--;
			indexNext = indexBack + 2;

			String imageTxt = a.getImageTxt();
			BufferedImage img = ImageIO.read(new File(imageTxt));
			Image dimg = img.getScaledInstance(DisplayJPanel.imageLabel.getWidth(),
				DisplayJPanel.imageLabel.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(dimg);
			DisplayJPanel.imageLabel.setIcon(icon);

		    } else {
			JOptionPane.showMessageDialog(null,
				"This is the last profile on list, please press next button");
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("ERROR: Image could not be loaded, check url");
		} catch (NullPointerException e1) {
		    System.out.println("ERROR: Image could not be loaded");
		} catch (NumberFormatException e2) {
		    System.out.println("ERROR2: It was not possible to write from file");
		} catch (InputMismatchException e3) {
		    System.out.println("ERROR3: More lines to read");
		}

	    }
	});

	/**
	 * Generates event to show personal profiles next by next until end of list
	 * (profilelist). If there is no more profiles a JOptionPane advise showing a
	 * message.
	 */
	nextButton.setText("Next");
	nextButton.setFont(new Font("Silom", Font.BOLD, 15));
	nextButton.setEnabled(false);
	nextButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		try {

		    if (indexNext < CreateJPanel.getProfileList().size()) {

			Person a = CreateJPanel.getProfileList().get(indexNext);

			pNumber = Integer.toString(a.getPersonalCode());
			DisplayJPanel.perCodeTextField.setText(pNumber);
			DisplayJPanel.nameTextField.setText(a.getName());
			DisplayJPanel.geoTextField.setText(a.getGeographic_data());
			DisplayJPanel.dobTextField.setText(a.getDob());
			DisplayJPanel.phoneTextField.setText(a.getPhone());
			DisplayJPanel.emailTextField.setText(a.getEmail());
			DisplayJPanel.ssnTextField.setText(a.getSsn());
			DisplayJPanel.bankTextField.setText(a.getBankAccNum());
			DisplayJPanel.dosTextField.setText(a.getDateOfStart());
			DisplayJPanel.departmTextField.setText(a.getDepart());
			indexNext++;
			indexBack = indexNext - 2;

			String imageTxt = a.getImageTxt();
			BufferedImage img = ImageIO.read(new File(imageTxt));
			Image dimg = img.getScaledInstance(DisplayJPanel.imageLabel.getWidth(),
				DisplayJPanel.imageLabel.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(dimg);
			DisplayJPanel.imageLabel.setIcon(icon);

		    } else {
			JOptionPane.showMessageDialog(null,
				"This is the last profile on list, please press back button");
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("ERROR: Image could not be loaded, check url");
		} catch (NullPointerException e1) {
		    System.out.println("ERROR: Image could not be loaded");
		} catch (NumberFormatException e2) {
		    System.out.println("ERROR2: It was not possible to write from file");
		} catch (InputMismatchException e3) {
		    System.out.println("ERROR3: More lines to read");
		}

	    }
	});

	/**
	 * Generates event to show directly the first personal profile of list
	 * (profilelist). If there is no more profiles a JOptionPane advise showing a
	 * message.
	 */
	firstButton.setText("First");
	firstButton.setFont(new Font("Silom", Font.BOLD, 15));
	firstButton.setEnabled(false);
	firstButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		try {

		    Person a = CreateJPanel.getProfileList().get(0);

		    pNumber = Integer.toString(a.getPersonalCode());
		    DisplayJPanel.perCodeTextField.setText(pNumber);
		    DisplayJPanel.nameTextField.setText(a.getName());
		    DisplayJPanel.geoTextField.setText(a.getGeographic_data());
		    DisplayJPanel.dobTextField.setText(a.getDob());
		    DisplayJPanel.phoneTextField.setText(a.getPhone());
		    DisplayJPanel.emailTextField.setText(a.getEmail());
		    DisplayJPanel.ssnTextField.setText(a.getSsn());
		    DisplayJPanel.bankTextField.setText(a.getBankAccNum());
		    DisplayJPanel.dosTextField.setText(a.getDateOfStart());
		    DisplayJPanel.departmTextField.setText(a.getDepart());
		

		    String imageTxt = a.getImageTxt();
		    BufferedImage img = ImageIO.read(new File(imageTxt));
		    Image dimg = img.getScaledInstance(DisplayJPanel.imageLabel.getWidth(),
			    DisplayJPanel.imageLabel.getHeight(), Image.SCALE_SMOOTH);
		    ImageIcon icon = new ImageIcon(dimg);
		    DisplayJPanel.imageLabel.setIcon(icon);

		    indexNext = 1;
		    indexBack = -1;
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("ERROR: Image could not be loaded, check url");
		} catch (NullPointerException e1) {
		    System.out.println("ERROR: Image could not be loaded");
		} catch (NumberFormatException e2) {
		    System.out.println("ERROR2: It was not possible to write from file");
		} catch (InputMismatchException e3) {
		    System.out.println("ERROR3: More lines to read");
		}

	    }
	});

	/**
	 * Generates event to show directly the last personal profile of list
	 * (profilelist). If there is no more profiles a JOptionPane advise showing a
	 * message.
	 */
	lastButton.setText("Last");
	lastButton.setFont(new Font("Silom", Font.BOLD, 15));
	lastButton.setEnabled(false);
	lastButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {

		try {
		    Person a = CreateJPanel.getProfileList().get(CreateJPanel.getProfileList().size() - 1);

		    pNumber = Integer.toString(a.getPersonalCode());
		    DisplayJPanel.perCodeTextField.setText(pNumber);
		    DisplayJPanel.nameTextField.setText(a.getName());
		    DisplayJPanel.geoTextField.setText(a.getGeographic_data());
		    DisplayJPanel.dobTextField.setText(a.getDob());
		    DisplayJPanel.phoneTextField.setText(a.getPhone());
		    DisplayJPanel.emailTextField.setText(a.getEmail());
		    DisplayJPanel.ssnTextField.setText(a.getSsn());
		    DisplayJPanel.bankTextField.setText(a.getBankAccNum());
		    DisplayJPanel.dosTextField.setText(a.getDateOfStart());
		    DisplayJPanel.departmTextField.setText(a.getDepart());

		    indexBack = CreateJPanel.getProfileList().size() - 2;
		    indexNext = CreateJPanel.getProfileList().size();

		    String imageTxt = a.getImageTxt();
		    BufferedImage img = ImageIO.read(new File(imageTxt));
		    Image dimg = img.getScaledInstance(DisplayJPanel.imageLabel.getWidth(),
			    DisplayJPanel.imageLabel.getHeight(), Image.SCALE_SMOOTH);
		    ImageIcon icon = new ImageIcon(dimg);
		    DisplayJPanel.imageLabel.setIcon(icon);

		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("ERROR: Image could not be loaded, check url");
		} catch (NullPointerException e1) {
		    System.out.println("ERROR: Image could not be loaded");
		} catch (NumberFormatException e2) {
		    System.out.println("ERROR2: It was not possible to write from file");
		} catch (InputMismatchException e3) {
		    System.out.println("ERROR3: More lines to read");
		}
	    }

	});

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	/**
	 * Generates group layout with all components.
	 */
	javax.swing.GroupLayout controlJPanelLayout = new javax.swing.GroupLayout(controlJPanel);
	controlJPanelLayout.setHorizontalGroup(controlJPanelLayout.createParallelGroup(Alignment.TRAILING)
		.addGroup(controlJPanelLayout.createSequentialGroup().addContainerGap().addGroup(controlJPanelLayout
			.createParallelGroup(Alignment.LEADING, false)
			.addGroup(controlJPanelLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(createPerson, Alignment.TRAILING)
				.addComponent(viewPerson, Alignment.TRAILING)
				.addComponent(loadProfileBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 153,
					GroupLayout.PREFERRED_SIZE)
				.addComponent(saveProfileBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 153,
					GroupLayout.PREFERRED_SIZE))
			.addGroup(Alignment.TRAILING,
				controlJPanelLayout.createSequentialGroup()
					.addGroup(controlJPanelLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(firstButton, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(backButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
							77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(controlJPanelLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(nextButton).addComponent(lastButton,
							GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))))
			.addContainerGap()));
	controlJPanelLayout.setVerticalGroup(controlJPanelLayout.createParallelGroup(Alignment.LEADING)
		.addGroup(controlJPanelLayout.createSequentialGroup().addGap(83).addComponent(createPerson).addGap(53)
			.addComponent(viewPerson).addGap(59)
			.addComponent(loadProfileBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			.addGap(65)
			.addComponent(saveProfileBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			.addGap(57)
			.addGroup(controlJPanelLayout.createParallelGroup(Alignment.BASELINE).addComponent(backButton)
				.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(ComponentPlacement.RELATED)
			.addGroup(controlJPanelLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(firstButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addComponent(lastButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(146, Short.MAX_VALUE)));
	controlJPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { nextButton, lastButton });
	controlJPanelLayout.linkSize(SwingConstants.HORIZONTAL,
		new Component[] { createPerson, viewPerson, loadProfileBtn, saveProfileBtn });
	controlJPanel.setLayout(controlJPanelLayout);

	jSplitPane1.setLeftComponent(controlJPanel);

	javax.swing.GroupLayout displayJPanelLayout = new javax.swing.GroupLayout(displayJPanel);
	displayJPanel.setLayout(displayJPanelLayout);
	displayJPanelLayout.setHorizontalGroup(displayJPanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 830, Short.MAX_VALUE));
	displayJPanelLayout.setVerticalGroup(displayJPanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 450, Short.MAX_VALUE));

	jSplitPane1.setRightComponent(displayJPanel);

	getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

	pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Creates an CreateJPanel object generating a new CreateJPanel form associating
     * the group layout.
     */
    private void createPersonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createPersonActionPerformed
	CreateJPanel createJPanel = new CreateJPanel(person);
	jSplitPane1.setRightComponent(createJPanel);

    }// GEN-LAST:event_createPersonActionPerformed

    /**
     * Creates an DisplayPanel object generating a new DisplayPanel form associating
     * the group layout.
     */
    private void viewPersonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewPersonActionPerformed
	DisplayJPanel viewJPanel = new DisplayJPanel(person);
	jSplitPane1.setRightComponent(viewJPanel);

    }// GEN-LAST:event_viewPersonActionPerformed

    /**
     * Main program class.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
	// (optional) ">

	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
	 * look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new MainJFrame().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Declaration of JSplitPane jSplitPane1 object.
     */
    private javax.swing.JSplitPane jSplitPane1;

    /**
     * Declaration of JPanel controlJPanel object.
     */
    private javax.swing.JPanel controlJPanel;

    /**
     * Declaration of JPanel displayJPanel object.
     */
    public javax.swing.JPanel displayJPanel;

    /**
     * Declaration of JButton createPerson object.
     */
    private javax.swing.JButton createPerson;

    /**
     * Declaration of JButton viewPerson object.
     */
    private javax.swing.JButton viewPerson;

    /**
     * Declaration of JButton loadProfileBtn object.
     */
    public javax.swing.JButton loadProfileBtn;

    /**
     * Declaration of JButton saveProfileBtn object.
     */
    public javax.swing.JButton saveProfileBtn;

    /**
     * Declaration of JButton backButton object.
     */
    private javax.swing.JButton backButton;

    /**
     * Declaration of JButton nextButton object.
     */
    private javax.swing.JButton nextButton;

    /**
     * Declaration of JButton firstButton object.
     */
    private javax.swing.JButton firstButton;

    /**
     * Declaration of JButton lastButton object.
     */
    private javax.swing.JButton lastButton;

}