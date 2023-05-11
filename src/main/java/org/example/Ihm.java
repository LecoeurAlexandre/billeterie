package org.example;

import dao.ClientDao;
import dao.PlaceDao;
import jdk.jshell.spi.ExecutionControl;
import utils.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ihm {
    Scanner sc;
    static List<Place> allPlaces = new ArrayList<>();
    private Connection connection;
    private PlaceDao placeDao;

    private ClientDao clientDao;

    public Ihm() {
        sc = new Scanner(System.in);
    }
    public void menu() {

        //Affichage du menu
        String choices[] = {"1- Ajouter, modifier ou supprimer un lieu", "2- Ajouter, modifier ou supprimer un événement", "3- Ajouter, modifier ou supprimer un client", "4- Acheter un billet", "5- Annuler un achat de billet", "6- Afficher la liste des événements disponibles ", "7- Afficher la liste des billets achetés par un client ", "0- Quitter"};
        System.out.println("Bienvenue sur le service de réservations");
        for (String c : choices) {
            System.out.println(c);
        }

        int generalChoice = sc.nextInt();

        switch (generalChoice) {
            case 1:
                String placeChoice[] = {"1- Ajouter un lieu", "2- Modifier un lieu", "3- Supprimer un lieu"};
                for (String pc : placeChoice) {
                    System.out.println(pc);
                }
                int choiceActionPlace = sc.nextInt();
                switch (choiceActionPlace) {
                    case 1:
                        createPlaceAction();
                        menu();
                    case 2:
                        setPlaceAction();
                        menu();
                    case 3:
                        deletePlaceAction();
                        menu();

                };
            case 2:
            case 3:
                String clientChoice[] = {"1- Ajouter un client", "2- Modifier un client", "3- Supprimer un client"};
                for (String pc : clientChoice) {
                    System.out.println(pc);
                }
                int choiceActionClient = sc.nextInt();
                switch (choiceActionClient) {
                    case 1:
                        createClientAction();
                        menu();
                    case 2:
                        setClientAction();
                        menu();
                    case 3:
                        deleteClientAction();
                        menu();

                };

        }
    };
    private Place createPlaceAction () {
        Place place = null;
        System.out.println("Veuillez saisir le nom du lieu");
        String placeName = sc.next();
        System.out.println("Veuillez saisir l'adresse");
        String placeAddress = sc.next();
        System.out.println("Veuillez saisir la capacité");
        int placeCapacity = sc.nextInt();
        sc.nextLine();
        place = new Place(placeName, placeAddress, placeCapacity);
        try {
            connection = DataBaseManager.getInstance().getConnection();
            placeDao = new PlaceDao(connection);
            if(placeDao.save(place)) {
                System.out.println("Lieu ajouté "+ place.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return place;
    }
    private void deletePlaceAction () {
        Place place = null;
        System.out.println("Veuillez saisir l'id du lieu à supprimer");
        int deletePlaceId = sc.nextInt();
        sc.nextLine();
        try {
            connection = DataBaseManager.getInstance().getConnection();
            placeDao = new PlaceDao(connection);
            place = placeDao.getById(deletePlaceId);
            if(place !=null) {
                if(placeDao.delete(place)) {
                    System.out.println("Lieu supprimé");
                }
            }
            else {
                System.out.println("Aucun lieu avec cet id");
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
    private Place getPlaceAction() {
        Place place = null;
        System.out.println("Merci de saisir l'id du lieu");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            connection = DataBaseManager.getInstance().getConnection();
            placeDao = new PlaceDao(connection);
            place = placeDao.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return place;
    }
    private Place setPlaceAction() {
        Place place = getPlaceAction();
        System.out.print("Merci de saisir le nom à modifier : ");
        place.setName(sc.nextLine());
        System.out.print("Merci de saisir l'adresse à modifier : ");
        place.setAddress(sc.nextLine());
        sc.nextLine();
        System.out.print("Merci de saisir la capacité à modifier : ");
        place.setCapacity(sc.nextInt());
        sc.nextLine();
        try {
            connection = DataBaseManager.getInstance().getConnection();
            placeDao = new PlaceDao(connection);
            placeDao.update(place);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Produit mise à jour");
        return place;
    }
    private Client createClientAction () {
        Client client = null;
        System.out.println("Veuillez saisir le nom du client");
        String clientLastName = sc.next();
        System.out.println("Veuillez saisir le prénom");
        String clientFirstName = sc.next();
        System.out.println("Veuillez saisir le mail");
        String clientEmail = sc.next();
        client = new Client(clientLastName, clientFirstName, clientEmail);
        try {
            connection = DataBaseManager.getInstance().getConnection();
            clientDao = new ClientDao(connection);
            if(clientDao.save(client)) {
                System.out.println("Lieu ajouté "+ client.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
    private Client getClientAction() {
        Client client = null;
        System.out.println("Merci de saisir l'id du lieu");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            connection = DataBaseManager.getInstance().getConnection();
            clientDao = new ClientDao(connection);
            client = clientDao.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
    private Client setClientAction() {
        Client client = getClientAction();
        System.out.print("Merci de saisir le nom à modifier : ");
        client.setLastName(sc.nextLine());
        System.out.print("Merci de saisir le prénom à modifier : ");
        client.setFirstName(sc.nextLine());
        System.out.print("Merci de saisir le mail à modifier : ");
        client.setEmail(sc.nextLine());
        try {
            connection = DataBaseManager.getInstance().getConnection();
            clientDao = new ClientDao(connection);
            clientDao.update(client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Produit mise à jour");
        return client;
    }
    private void deleteClientAction () {
        Client client = null;
        System.out.println("Veuillez saisir l'id du client à supprimer");
        int deleteClientId = sc.nextInt();
        sc.nextLine();
        try {
            connection = DataBaseManager.getInstance().getConnection();
            clientDao = new ClientDao(connection);
            client = clientDao.getById(deleteClientId);
            if(client !=null) {
                if(clientDao.delete(client)) {
                    System.out.println("Client supprimé");
                }
            }
            else {
                System.out.println("Aucun client avec cet id");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
