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
        String choises[] = {"1- Ajouter, modifier ou supprimer un lieu", "2- Ajouter, modifier ou supprimer un événement", "3- Ajouter, modifier ou supprimer un client", "4- Acheter un billet", "5- Annuler un achat de billet", "6- Afficher la liste des événements disponibles ", "6- Afficher la liste des billets achetés par un client ", "0- Quitter"};
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
                        System.out.println("Veuillez saisir le nom du lieu");
                        String placeNameForDelete = sc.nextLine();
                        System.out.println("Veuillez saisir le nom du lieu");
                        String placeAddressForDelete = sc.nextLine();
                        for (int i = 0; i < allPlaces.size(); i++) {
                            if(allPlaces.get(i).getName().equals(placeNameForDelete) & allPlaces.get(i).getAddress().equals(placeAddressForDelete)) {
                                allPlaces.remove(allPlaces.get(i));
                                System.out.println(placeNameForDelete +" de "+ placeAddressForDelete + " a été supprimé");
                            }
                        }
                        System.out.println(allPlaces.toString());
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
}
