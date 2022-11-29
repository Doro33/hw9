package ir.maktab.repository;

import ir.maktab.entity.Prescription;

import java.sql.SQLException;

public interface PrescriptionRepository {
    Prescription addPrescription (Prescription prescription) throws SQLException;

    static void deletePrescription(int id) throws SQLException {

    }

    void setIsValid(int prescriptionId, boolean isValid) throws SQLException;
}
