package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;
import dk.dtu.gbar.gitlab.shipment.Searcher;

import javax.swing.JTable;

public class LogisticsCompanyScreen extends JFrame implements PropertyChangeListener {


    private LoginScreen parentWindow;
    private LogisticsCompany logisticsCompany;
    private JPanel panelMainMenuFunctions;
    private JButton btnLogOut;
    private JRadioButton btnShowConcluded;
    private JRadioButton btnShowCurrent;
    private JRadioButton btnShowAll;
    private JButton btnSearch;
    private JButton btnExamine;
    private JButton btnRegisterJourney;
    private JTable tblJourneys;
    private JTable tblClients;
    private DefaultTableModel journeyTable;
    private DefaultTableModel clientTable;
    private Searcher search;
    private String keyword;
    private ArrayList<Journey> journeys;


    ///
    public LogisticsCompanyScreen(LoginScreen parentWindow, LogisticsCompany logisticsCompany) {
        this.parentWindow = parentWindow;
        this.logisticsCompany = logisticsCompany;
        search = new Searcher(logisticsCompany);
        journeys = logisticsCompany.getJourneyList();
        keyword = "";

        logisticsCompany.addObserver(this);

        initialize();
    }

    private void initialize() {
        panelMainMenuFunctions = new JPanel();
        JFrame frame;
        frame = new JFrame();
        frame.setBounds(100, 100, 1000,500);
        parentWindow.addPanel(panelMainMenuFunctions);
        panelMainMenuFunctions.setLayout(null);
        panelMainMenuFunctions.setBorder(BorderFactory.createTitledBorder("Main Menu"));

        JTextField txtKeywordSearch = new JTextField(30);
        JLabel lblKeywordSearch = new JLabel("Keyword");

        JTextField txtCargoKeywordSearch = new JTextField(30);
        JLabel lblCargoKeywordSearch = new JLabel("Cargo");

        JTextField txtOriginKeywordSearch = new JTextField(30);
        JLabel lblOriginKeywordSearch = new JLabel("Origin");

        JTextField txtDestinationKeywordSearch = new JTextField(30);
        JLabel lblDestinationKeywordSearch = new JLabel("Destination");

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                keyword = txtKeywordSearch.getText();
                ArrayList searchResults = search.journeySearchByString(journeys, keyword);
                if(txtFieldNotEmpty(txtCargoKeywordSearch)){
                    searchResults = filterSearchBy(searchResults, search.cargoContains(txtCargoKeywordSearch.getText()));
                }
                if (txtFieldNotEmpty(txtOriginKeywordSearch)){
                    searchResults = filterSearchBy(searchResults, search.originContains(txtOriginKeywordSearch.getText()));
                }
                if (txtFieldNotEmpty(txtDestinationKeywordSearch)){
                    searchResults = filterSearchBy(searchResults, search.destinationContains(txtDestinationKeywordSearch.getText()));
                }
                journeyTable.setRowCount(0);
                display(searchResults);
                //Checks what's in the txtKeywordSearch as well as if showConcluded and showCurrent are enabled
                //pulls up journeys based on keyword and showConcluded and showCurrent
            }

            private ArrayList filterSearchBy(ArrayList searchResults, Predicate predicate) {
                return search.search(searchResults, predicate);
            }

            private boolean txtFieldNotEmpty(JTextField txtCargoKeywordSearch) {
                return txtCargoKeywordSearch.getText().length() > 0;
            }


        });

        btnLogOut = new JButton("Log Out");
        btnLogOut.setLocation(690, 11);
        btnLogOut.setSize(150, 29);
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	parentWindow.frame.setBounds(100,100,475,500);
                setVisible(false);
                parentWindow.setVisible(true);
            }
        });

        btnShowConcluded = new JRadioButton("Show Concluded");
        btnShowConcluded.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnShowCurrent.setSelected(false);
                btnShowAll.setSelected(false);
                journeyTable.setRowCount(0);
                journeys = search.getConcludedJourneys(logisticsCompany.getJourneyList());
                ArrayList searchResults = search.journeySearchByString(journeys, keyword);
                display(searchResults);
            }
        });

        btnShowCurrent = new JRadioButton("Show Current");
        btnShowCurrent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnShowConcluded.setSelected(false);
                btnShowAll.setSelected(false);
                journeyTable.setRowCount(0);
                journeys = search.getCurrentJourneys(logisticsCompany.getJourneyList());
                ArrayList searchResults = search.journeySearchByString(journeys, keyword);
                display(searchResults);
            }
        });

        btnShowAll = new JRadioButton("Show All");
        btnShowAll.setLocation(18, 183);
        btnShowAll.setSize(77, 29);
        btnShowAll.setSelected(true);
        btnShowAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnShowConcluded.setSelected(false);
                btnShowCurrent.setSelected(false);
                journeyTable.setRowCount(0);
                journeys = logisticsCompany.getJourneyList();
                ArrayList searchResults = search.journeySearchByString(journeys, keyword);
                display(searchResults);
            }
        });

        journeyTable = new DefaultTableModel();
        journeyTable.addColumn("Origin");
        journeyTable.addColumn("Destination");
        journeyTable.addColumn("Cargo");
        journeyTable.addColumn("Journey ID");
        for(Journey journey: logisticsCompany.getJourneyList()) {
            journeyTable.addRow(new Object[] {journey.getOrigin().getPlaceName(),	journey.getDestination().getPlaceName(),journey.getCargo(),journey.getID()});
        }

        clientTable = new DefaultTableModel();
        clientTable.addColumn("Name");
        clientTable.addColumn("Email");
        clientTable.addColumn("No. of Journeys");
        for(Client client: logisticsCompany.getClientList()) {
            clientTable.addRow(new Object[] {client.getName(), client.getEmail(), (Integer)(client.getJourneys().size())});
        }


        JScrollPane scrollJourneys = new JScrollPane();
        scrollJourneys.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tblJourneys = new JTable(journeyTable);
        scrollJourneys.setViewportView(tblJourneys);

        JScrollPane scrollClients = new JScrollPane();
        scrollClients.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_ALWAYS));
        tblClients = new JTable(clientTable);
        scrollClients.setViewportView(tblClients);


        btnSearch.setBounds(690,74,150,29);
        txtKeywordSearch.setBounds(102,75, 130, 26);
        lblKeywordSearch.setBounds(21,75, 83, 26);

        txtCargoKeywordSearch.setBounds(102,105, 130, 26);
        lblCargoKeywordSearch.setBounds(21,105, 83, 26);
        txtOriginKeywordSearch.setBounds(102,135, 130, 26);
        lblOriginKeywordSearch.setBounds(21,135, 83, 26);
        txtDestinationKeywordSearch.setBounds(322,135, 130, 26);
        lblDestinationKeywordSearch.setBounds(241,135, 83, 26);

        btnShowConcluded.setBounds(18,158,129,29);
        btnShowCurrent.setBounds(149,158,105,29);

        scrollJourneys.setSize(338, 214);
        scrollJourneys.setLocation(102, 203);

        scrollClients.setSize(338,214);
        scrollClients.setLocation(502,203);

        panelMainMenuFunctions.add(lblKeywordSearch);
        panelMainMenuFunctions.add(txtKeywordSearch);
        panelMainMenuFunctions.add(txtDestinationKeywordSearch);
        panelMainMenuFunctions.add(lblDestinationKeywordSearch);
        panelMainMenuFunctions.add(txtOriginKeywordSearch);
        panelMainMenuFunctions.add(lblOriginKeywordSearch);
        panelMainMenuFunctions.add(txtCargoKeywordSearch);
        panelMainMenuFunctions.add(lblCargoKeywordSearch);
        panelMainMenuFunctions.add(btnShowConcluded);
        panelMainMenuFunctions.add(btnShowCurrent);
        panelMainMenuFunctions.add(btnShowAll);
        panelMainMenuFunctions.add(btnLogOut);
        panelMainMenuFunctions.add(btnSearch);
        panelMainMenuFunctions.add(scrollJourneys);
        panelMainMenuFunctions.add(scrollClients);



    }

    public void addJourney(Journey journey) {
        journeyTable.addRow(new Object[] {journey.getOrigin().getPlaceName(),journey.getDestination().getPlaceName(),
                journey.getCargo()});
    }



    private void setEnableButtons(boolean enabled) {
        btnLogOut.setEnabled(enabled);
        btnShowConcluded.setEnabled(enabled);
        btnShowCurrent.setEnabled(enabled);
        btnShowConcluded.setEnabled(enabled);
        btnShowAll.setEnabled(enabled);


    }

    private void enableButtons() {
        setEnableButtons(true);
    }

    private void disableButtons() {
        setEnableButtons(false);
    }

    public void setVisible(boolean visible) {
        if (!visible) {
            disableButtons();
        }
        else {
            enableButtons();
        }
        panelMainMenuFunctions.setVisible(visible);
    }

    private void display(ArrayList<Journey> searchResults) {
        for(Journey journey: searchResults) {
            journeyTable.addRow(new Object[] {journey.getOrigin().getPlaceName(),	journey.getDestination().getPlaceName(),journey.getCargo(),journey.getContainer().getID()});
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        initialize();

    }

}

