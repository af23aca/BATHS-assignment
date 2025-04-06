package wars;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version Updated for Task 3.6
 */
public class GameGUI 
{
    private BATHS gp = new SeaBattles("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea(20, 40);
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight Encounter");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();

    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }

    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(new JScrollPane(listing), BorderLayout.CENTER);
        listing.setVisible(false);
        listing.setEditable(false);
        contentPane.add(eastPanel, BorderLayout.EAST);
        
        eastPanel.setLayout(new GridLayout(6, 1)); // 6 buttons now

        eastPanel.add(viewBtn);
        viewBtn.addActionListener(new ViewStateHandler());

        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightHandler());

        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearHandler());

        eastPanel.add(quitBtn);
        quitBtn.addActionListener(e -> System.exit(0));

        clearBtn.setVisible(true);
        quitBtn.setVisible(true);

        myFrame.pack();
        myFrame.setVisible(true);
    }

    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu fileMenu = new JMenu("Ships");
        menubar.add(fileMenu);

        JMenuItem listReserve = new JMenuItem("List Reserve Ships");
        listReserve.addActionListener(new ListFleetHandler());
        fileMenu.add(listReserve);

        JMenuItem listSquadron = new JMenuItem("List Squadron");
        listSquadron.addActionListener(new ListSquadronHandler());
        fileMenu.add(listSquadron);

        JMenuItem viewShip = new JMenuItem("View a Ship");
        viewShip.addActionListener(new ViewShipHandler());
        fileMenu.add(viewShip);

        JMenuItem commissionShip = new JMenuItem("Commission a Ship");
        commissionShip.addActionListener(new CommissionShipHandler());
        fileMenu.add(commissionShip);

        JMenuItem decommission = new JMenuItem("Decommission Ship");
        decommission.addActionListener(new DecommissionHandler());
        fileMenu.add(decommission);
    }

    // ===================== Menu Item Handlers ========================

    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String text = gp.getReserveFleet();
            listing.setText(text);
        }
    }

    private class ListSquadronHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            listing.setVisible(true);
            String text = gp.getSquadron();
            listing.setText(text);
        }
    }

    private class ViewShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String shipName = JOptionPane.showInputDialog(myFrame, "Enter ship name:");
            if (shipName != null && !shipName.isEmpty()) {
                String result = gp.getShipDetails(shipName);
                JOptionPane.showMessageDialog(myFrame, result);
            }
        }
    }

    private class CommissionShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String shipName = JOptionPane.showInputDialog(myFrame, "Enter ship name to commission:");
            if (shipName != null && !shipName.isEmpty()) {
                String result = gp.commissionShip(shipName);
                JOptionPane.showMessageDialog(myFrame, result);
            }
        }
    }
    private class DecommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String shipName = JOptionPane.showInputDialog("Ship code?:");
            if (gp.isInSquadron(shipName)) {
                gp.decommissionShip(shipName);
                JOptionPane.showMessageDialog(myFrame, shipName + " is decommissioned");
            } else {
                JOptionPane.showMessageDialog(myFrame, shipName + " not in fleet");
            }
        }
    }

    // ===================== Button Handlers ===========================
    
    private class ViewStateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            listing.setVisible(true);
            listing.setText(gp.toString());
        }
    }

    private class FightHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String input = JOptionPane.showInputDialog("Enter encounter number:");
            try {
                int encounterNo = Integer.parseInt(input);
                String result = gp.fightEncounter(encounterNo);
                JOptionPane.showMessageDialog(myFrame, result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(myFrame, "Invalid number entered.");
            }
        }
    }

    private class ClearHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText("");
            listing.setVisible(false);
        }
    }
}