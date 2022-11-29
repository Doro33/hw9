package ir.maktab.repository.impl;

import ir.maktab.config.AppConnection;
import ir.maktab.entity.Patient;
import ir.maktab.repository.PatientRepository;

import java.sql.*;

public class PatientRepositoryImpl implements PatientRepository {
    private final Connection connection = AppConnection.getConnection();

    @Override
    public Patient addPatient(Patient patient) throws SQLException {
        String sql = """
                INSERT INTO patient(name, "nationalCode")
                VALUES (?, ?)
                """;

        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, patient.getName());
        ps.setString(2, patient.getNationalCode());
        ps.execute();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt("id");
        patient.setId(id);
        ps.close();
        generatedIds.close();
        return patient;
    }
    @Override
    public Patient getPatient(String nationalCode) throws SQLException {
        String sql = """
                Select * from patient
                where "nationalCode"=?;
                """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nationalCode);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Patient patient = new Patient(rs.getString("name"),
                    rs.getString("nationalCode"));
            patient.setId(rs.getInt("id"));
            ps.execute();
            ps.close();
            rs.close();
            return patient;
    }

    @Override
    public boolean nationalCodeContains(String nationalCode) throws SQLException {
        String sql = """
                SELECT "nationalCode" from patient
                WHERE "nationalCode"=? ;
                """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nationalCode);
            ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}