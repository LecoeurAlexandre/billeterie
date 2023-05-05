package org.example;

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

    public Ihm() {
        sc = new Scanner(System.in);
    }
    public void menu() {

        //Affichage du menu
        String choises[] = {"1- Ajouter, modifier ou supprimer un lieu", "2- Ajouter, modifier ou supprimer un événement", "3- Ajouter, modifier ou supprimer un client", "4- Acheter un billet", "5- Annuler un achat de billet", "6- Afficher la liste des événements disponibles ", "7- Afficher la liste des billets achetés par un client ", "0- Quitter"};
        System.out.println("Bienvenue sur le service de réservations");
        for (String c : choises) {
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

                    case 3:
                        deletePlaceAction();
                        menu();

                }
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
            connection = new DataBaseManager().getConnection();
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
            connection = new DataBaseManager().getConnection();
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
}
