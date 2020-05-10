package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;
import dk.dtu.gbar.gitlab.shipment.Searcher;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;

public class LogisticsCompanyScreen extends JFrame implements PropertyChangeListener {


    private LoginScreen parentWindow;
    private LogisticsCompany logisticsCompany;
    private JPanel panelMainMenuFunctions;
    private JButton btnLogOut;
    private JRadioButton btnShowConcluded;
    private JRadioButton btnShowCurrent;
    private JRadioButton btnShowAll;
    private JRadioButton btnClientsByJourneyNo;
    private JRadioButton btnSortClientsChronologically;
    private JButton btnSearch;
    private JButton btnExamine;
    private JButton btnRegisterJourney;
    private JTable tblJourneys;
    private JTable tblClients;
    private JTable tblContainers;
    private DefaultTableModel journeyTable;
    private DefaultTableModel clientTable;
    private DefaultTableModel containerTable;
    private Searcher search;
    private String keyword;
    private List<Journey> journeys;
    private List<Client> clients;


    ///
    public LogisticsCompanyScreen(LoginScreen parentWindow, LogisticsCompany logisticsCompany) {
        this.parentWindow = parentWindow;
        this.logisticsCompany = logisticsCompany;
        search = new Searcher(logisticsCompany);
        journeys = logisticsCompany.getJourneys();
        keyword = "";
        clients = logisticsCompany.getClients();
        logisticsCompany.addObserver(this);

        initialize();
    }

    private void initialize() {
        clients = new ArrayList<>();
        panelMainMenuFunctions = new JPanel();
        JFrame frame;
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 500);
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
                List searchResults = search.journeySearchByString(journeys, keyword);
                if (txtFieldNotEmpty(txtCargoKeywordSearch)) {
                    searchResults = filterSearchBy(searchResults, search.cargoContains(txtCargoKeywordSearch.getText()));
                }
                if (txtFieldNotEmpty(txtOriginKeywordSearch)) {
                    searchResults = filterSearchBy(searchResults, search.originContains(txtOriginKeywordSearch.getText()));
                }
                if (txtFieldNotEmpty(txtDestinationKeywordSearch)) {
                    searchResults = filterSearchBy(searchResults, search.destinationContains(txtDestinationKeywordSearch.getText()));
                }
                journeyTable.setRowCount(0);
                displayJourneyTable(searchResults);
                //Checks what's in the txtKeywordSearch as well as if showConcluded and showCurrent are enabled
                //pulls up journeys based on keyword and showConcluded and showCurrent
            }

            private List filterSearchBy(List searchResults, Predicate predicate) {
                return search.search(searchResults, predicate);
            }

            private boolean txtFieldNotEmpty(JTextField txtCargoKeywordSearch) {
                return txtCargoKeywordSearch.getText().length() > 0;
            }


        });

        btnLogOut = new JButton("Log Out");
        btnLogOut.setLocation(1130, 11);
        btnLogOut.setSize(150, 29);
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentWindow.frame.setBounds(100, 100, 475, 500);
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
                journeys = search.getConcludedJourneys(journeys);
                List searchResults = search.journeySearchByString(journeys, keyword);
                displayJourneyTable(searchResults);
            }
        });

        btnShowCurrent = new JRadioButton("Show Current");
        btnShowCurrent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnShowConcluded.setSelected(false);
                btnShowAll.setSelected(false);
                journeyTable.setRowCount(0);
                journeys = search.getCurrentJourneys(journeys);
                List searchResults = search.journeySearchByString(journeys, keyword);
                displayJourneyTable(searchResults);
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
                journeys = logisticsCompany.getJourneys();
                List searchResults = search.journeySearchByString(journeys, keyword);
                displayJourneyTable(searchResults);
            }
        });

        journeyTable = new DefaultTableModel();
        journeyTable.addColumn("Origin");
        journeyTable.addColumn("Destination");
        journeyTable.addColumn("Cargo");
        journeyTable.addColumn("Journey ID");
        for (Journey journey : logisticsCompany.getJourneys()) {
            journeyTable.addRow(new Object[]{journey.getJourneyOrigin().getName(), journey.getJourneyDestination().getName(), journey.getContainerContent(), journey.getId()});
        }

        btnClientsByJourneyNo = new JRadioButton("Sort by no. of journeys");
        btnClientsByJourneyNo.setLocation(502, 167);
        btnClientsByJourneyNo.setSize(150, 29);
        btnClientsByJourneyNo.setSelected(false);
        btnClientsByJourneyNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnClientsByJourneyNo.setSelected(true);
                btnSortClientsChronologically.setSelected(false);
                clientTable.setRowCount(0);
                List clients = logisticsCompany.getClients();
                List searchResults = search.getClientsByMostJourneys(clients);

                displayClientTable(searchResults);
            }
        });

        btnSortClientsChronologically = new JRadioButton("Sort chronologically");
        btnSortClientsChronologically.setLocation(702, 167);
        btnSortClientsChronologically.setSize(150, 29);
        btnSortClientsChronologically.setSelected(true);
        btnSortClientsChronologically.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSortClientsChronologically.setSelected(true);
                btnClientsByJourneyNo.setSelected(false);
                clientTable.setRowCount(0);
                clients = logisticsCompany.getClients();
                displayClientTable(logisticsCompany.getClients());
                for(Client client: logisticsCompany.getClients()){
                    System.out.println(client.getClientName());
                }
            }
        });

        clientTable = new DefaultTableModel();
        clientTable.addColumn("Name");
        clientTable.addColumn("Email");
        clientTable.addColumn("No. of Journeys");
        for (Client client : logisticsCompany.getClients()) {
            clientTable.addRow(new Object[]{client.getClientName(), client.getEmail(), (client.getClientsJourneys().size())});
        }

        containerTable = new DefaultTableModel();
        containerTable.addColumn("Container ID");
        containerTable.addColumn("Container Location");
        containerTable.addColumn("Container on Journey");
        for(Container container : logisticsCompany.getContainers()) {
        	containerTable.addRow(new Object[] {container.getId().toString(), container.getContainerLocation().getName(),container.getOnJourney().toString()});
        }

        JScrollPane scrollJourneys = new JScrollPane();
        scrollJourneys.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tblJourneys = new JTable(journeyTable);
        scrollJourneys.setViewportView(tblJourneys);

        JScrollPane scrollClients = new JScrollPane();
        scrollClients.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_ALWAYS));
        tblClients = new JTable(clientTable);
        scrollClients.setViewportView(tblClients);
        
        JScrollPane scrollContainers = new JScrollPane();
        scrollContainers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tblContainers = new JTable(containerTable);
        scrollContainers.setViewportView(tblContainers);


        btnSearch.setBounds(302, 95, 150, 29);
        txtKeywordSearch.setBounds(102, 75, 130, 26);
        lblKeywordSearch.setBounds(21, 75, 83, 26);

        txtCargoKeywordSearch.setBounds(102, 105, 130, 26);
        lblCargoKeywordSearch.setBounds(21, 105, 83, 26);
        txtOriginKeywordSearch.setBounds(102, 135, 130, 26);
        lblOriginKeywordSearch.setBounds(21, 135, 83, 26);
        txtDestinationKeywordSearch.setBounds(322, 135, 130, 26);
        lblDestinationKeywordSearch.setBounds(241, 135, 83, 26);

        btnShowConcluded.setBounds(18, 158, 129, 29);
        btnShowCurrent.setBounds(149, 158, 105, 29);

        scrollJourneys.setSize(338, 214);
        scrollJourneys.setLocation(102, 203);

        scrollClients.setSize(338, 214);
        scrollClients.setLocation(502, 203);
        
        scrollContainers.setSize(380, 214);
        scrollContainers.setLocation(895,203);

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
        panelMainMenuFunctions.add(btnClientsByJourneyNo);
        panelMainMenuFunctions.add(btnSortClientsChronologically);

        panelMainMenuFunctions.add(scrollJourneys);
        panelMainMenuFunctions.add(scrollClients);
        panelMainMenuFunctions.add(scrollContainers);


    }

    public void addJourney(Journey journey) {
        journeyTable.addRow(new Object[]{journey.getJourneyOrigin().getName(), journey.getJourneyDestination().getName(),
                journey.getContainerContent()});
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
        } else {
            enableButtons();
        }
        panelMainMenuFunctions.setVisible(visible);
    }

    private void displayJourneyTable(List<Journey> searchResults) {
        for (Journey journey : searchResults) {
            journeyTable.addRow(new Object[]{journey.getJourneyOrigin().getName(), journey.getJourneyDestination().getName(), journey.getContainerContent(), journey.getJourneyContainer().getId()});
        }
    }

    private void displayClientTable(List<Client> searchResults) {
        for (Client client : searchResults) {
            clientTable.addRow(new Object[]{client.getClientName(), client.getEmail(), (client.getClientsJourneys().size())});
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        initialize();

    }

}

