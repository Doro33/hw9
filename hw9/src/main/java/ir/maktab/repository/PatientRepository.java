package ir.maktab.repository;

import ir.maktab.entity.Patient;

import java.sql.SQLException;

public interface PatientRepository {
    public Patient addPatient(Patient patient) throws SQLException;

    public Patient getPatient(String nationalCode) throws SQLException;

    public boolean nationalCodeContains(String nationalCode) throws SQLException;
}
