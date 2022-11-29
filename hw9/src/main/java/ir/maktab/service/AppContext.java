package ir.maktab.service;

import ir.maktab.repository.PatientRepository;
import ir.maktab.repository.impl.ItemRepositoryImpl;
import ir.maktab.repository.impl.PatientRepositoryImpl;
import ir.maktab.repository.impl.PrescriptionRepositoryImpl;
import ir.maktab.service.prescription_services.PrescriptionService;
import ir.maktab.view.patient.PrescriptionDeleting;

import java.util.Scanner;

public class AppContext {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PatientRepositoryImpl PATIENT_REPOSITORY = new PatientRepositoryImpl();
    private static final PrescriptionRepositoryImpl PRESCRIPTION_REPOSITORY=new PrescriptionRepositoryImpl();
    private static final ItemRepositoryImpl ITEM_REPOSITORY=new ItemRepositoryImpl();
    private static final PrescriptionService PRESCRIPTION_SERVICE=new PrescriptionService();
    private static final PrescriptionDeleting PRESCRIPTION_DELETING=new PrescriptionDeleting();
    public static Scanner getScanner(){
        return new Scanner(System.in);
    }
    public static PatientRepositoryImpl getPATIENT_REPOSITORY() {
        return PATIENT_REPOSITORY;
    }

    public static PrescriptionRepositoryImpl getPRESCRIPTION_REPOSITORY() {
        return PRESCRIPTION_REPOSITORY;
    }

    public static ItemRepositoryImpl getITEM_REPOSITORY() {
        return ITEM_REPOSITORY;
    }

    public static PrescriptionService getPRESCRIPTION_SERVICE() {
        return PRESCRIPTION_SERVICE;
    }
    public static PrescriptionDeleting getPrescriptionDeleting(){
        return PRESCRIPTION_DELETING;
    }

    public static void printStrings(String[] input) {
        if (input[0] == null)
            System.out.println("There Is No Item To Show.");
        else
            for (int i = 0; i < input.length; i++) {
                if (input[i] == null)
                    break;
                System.out.println(i + 1 + ") " + input[i]);
            }
    }

    public static void continuePermit() {
        System.out.println("Press Enter To Continue");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }
}