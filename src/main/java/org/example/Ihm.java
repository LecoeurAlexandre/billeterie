package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ihm {
    static Scanner sc = new Scanner(System.in);
    static List<Place> allPlaces = new ArrayList<>();
    public static void menu() {

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
                        System.out.println("Veuillez saisir le nom du lieu");
                        String placeName = sc.next();
                        System.out.println("Veuillez saisir l'adresse");
                        String placeAddress = sc.next();
                        System.out.println("Veuillez saisir la capacité");
                        int placeCapacity = sc.nextInt();
                        allPlaces.add(new Place(placeName, placeAddress, placeCapacity));
                        System.out.println(allPlaces.toString());
                        menu();
                    case 2:

                    case 3:
                        System.out.println("Veuillez saisir le nom du lieu");
                        String placeNameForDelete = sc.next();
                        System.out.println("Veuillez saisir le nom du lieu");
                        String placeAddressForDelete = sc.next();
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
}
