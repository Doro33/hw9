package ir.maktab.view.patient;

import ir.maktab.entity.Item;
import ir.maktab.entity.Prescription;
import ir.maktab.service.AppContext;

import java.sql.SQLException;

public class PrescriptionDeleting {
    public void deleteAPrescription(int patientId) throws SQLException {
        System.out.println("Delete A Prescription:");
        Prescription[] prescriptions= AppContext.getPRESCRIPTION_SERVICE().showUncheckedPrescriptions(patientId);
        System.out.println("Press 0 To Get Back To Menu.");
        System.out.print("Which Prescription Must Be Deleted? ");
        int input=AppContext.getScanner().nextInt();
        if (input==0) {
            PatientMenu.startMenu();
        }else {
            int prescriptionId = prescriptions[input - 1].getId();
            AppContext.getPRESCRIPTION_SERVICE().deleteAPrescription(prescriptionId);
            AppContext.continuePermit();
            deleteAPrescription(patientId);
        }
    }
}
