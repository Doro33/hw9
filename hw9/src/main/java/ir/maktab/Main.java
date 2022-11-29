package ir.maktab;

import ir.maktab.entity.Prescription;
import ir.maktab.service.prescription_services.PrescriptionService;
import ir.maktab.view.patient.PatientMenu;
import ir.maktab.view.patient.PrescriptionDeleting;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*Item item1 = new Item(new Drug("whey", 2));
        Item item2 = new Item(new Drug("amino", 1));
        PrescriptionEditing.editAnItem(4,new Drug("creatine", 4));*/
        PatientMenu.startMenu();


    }
}