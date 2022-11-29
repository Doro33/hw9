package ir.maktab.view.patient;

import ir.maktab.service.AppContext;
import ir.maktab.service.prescription_services.PrescriptionService;

import java.sql.SQLException;
import java.util.Scanner;

public class PatientMenu {
    //we should get patientId After Login
    static Scanner scanner=AppContext.getScanner();
    public static void startMenu() throws SQLException {
        int patientId=5;//we should get it.
        AppContext.printStrings(new String[]{
                "My Prescriptions",
                "Add A Prescription",
                "Edit A Prescription",
                "Delete A Prescription",
                "Log Out"});
        System.out.print("What Do You Want To Do: ");
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
                case "1":
                    System.out.println("----------------");
                    AppContext.getPRESCRIPTION_SERVICE().showPatientPrescription(patientId);
                    AppContext.continuePermit();
                    PatientMenu.startMenu();
                case "2":
                    System.out.println("----------------");
                    input="";// for break the loop1
                    break;
                case "3":
                    System.out.println("----------------");

                    break;
                case "4":
                    System.out.println("----------------");
                    AppContext.getPrescriptionDeleting().deleteAPrescription(patientId);
                case "5":
                    System.out.println("----------------");

                    break;
                default:
                    System.out.println("Your Input Is Not Valid.");
                    System.out.print("You Can Only Choose 1-5 : ");
                    input=scanner.nextLine();
            }
        }
    }
}