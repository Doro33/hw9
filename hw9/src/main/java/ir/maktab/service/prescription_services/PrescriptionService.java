package ir.maktab.service.prescription_services;

import ir.maktab.entity.Drug;
import ir.maktab.entity.Item;
import ir.maktab.entity.Prescription;
import ir.maktab.repository.impl.ItemRepositoryImpl;
import ir.maktab.repository.impl.PrescriptionRepositoryImpl;
import ir.maktab.service.AppContext;

import java.sql.SQLException;

public class PrescriptionService {
    PrescriptionRepositoryImpl prescriptionRepo = new PrescriptionRepositoryImpl();
     ItemRepositoryImpl itemRepo = new ItemRepositoryImpl();

    private  void showConfirmedPrescriptions(int patientId) throws SQLException {
        System.out.println("- Confirmed Prescriptions:");
        Prescription[] p = prescriptionRepo.showConfirmedPrescription(patientId);
        String[] s = Prescription.showConfirmedPrescriptions(p);
        AppContext.printStrings(s);
    }

    private  void showRejectedPrescriptions(int patientId) throws SQLException {
        System.out.println("- Rejected Prescriptions:");
        Prescription[] p = prescriptionRepo.showRejectedPrescription(patientId);
        String[] s = Prescription.showPrescriptions(p);
        AppContext.printStrings(s);
    }

    public Prescription[] showUncheckedPrescriptions(int patientId) throws SQLException {
        System.out.println("- Unchecked Prescriptions:");
        Prescription[] p = prescriptionRepo.showUncheckedPrescription(patientId);
        String[] s = Prescription.showPrescriptions(p);
        AppContext.printStrings(s);
        return p;
    }

    public  void showPatientPrescription(int patientId) throws SQLException {
        System.out.println("My Prescription:");
        showConfirmedPrescriptions(patientId);
        System.out.println("----");
        showUncheckedPrescriptions(patientId);
        System.out.println("----");
        showRejectedPrescriptions(patientId);
    }

    public void addPrescription(Prescription prescription) throws SQLException {
        int prescriptionId = prescriptionRepo.addPrescription(prescription).getId();
        Item[] items = prescription.getItems();
        for (int i = 0; i < 10; i++) {
            if (items[i] == null)
                break;
            items[i].setRow(i + 1);// I might comment this line. it turns to view Package
            items[i].setPrescriptionId(prescriptionId);
            itemRepo.addItem(items[i]);
        }
    }

    public void deleteAPrescription(int prescriptionId) throws SQLException {
        itemRepo.deletePrescriptionItems(prescriptionId);
        prescriptionRepo.deletePrescription(prescriptionId);
    }

    public  void editAnItem(int itemId, Drug drug) throws SQLException {
        AppContext.getITEM_REPOSITORY().editItem(itemId, drug);
    }
}