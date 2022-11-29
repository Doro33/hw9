package ir.maktab.repository.impl;

import ir.maktab.config.AppConnection;
import ir.maktab.entity.Prescription;
import ir.maktab.repository.PrescriptionRepository;

import java.sql.*;
import java.time.LocalDate;

public class PrescriptionRepositoryImpl implements PrescriptionRepository {
    private static final Connection connection = AppConnection.getConnection();
    private static ItemRepositoryImpl itemRepository = new ItemRepositoryImpl();

    private Prescription getPrescription(ResultSet rs) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(rs.getInt("id"));
        prescription.setPatientId(rs.getInt("patientId"));
        prescription.setDrName(rs.getString("drName"));
        prescription.setDate(rs.getDate("date").toLocalDate());
        prescription.setValid(rs.getBoolean("isValid"));
        prescription.setTotalPrice(rs.getDouble("totalPrice"));
        prescription.setHasPaid(rs.getBoolean("hasPaid"));
        return prescription;
    }

    private Prescription[] getPrescriptions(ResultSet rs) throws SQLException {
        Prescription[] prescriptions = new Prescription[100];
        int index = 0;
        while (rs.next()) {
            Prescription prescription = getPrescription(rs);
            prescriptions[index] = prescription;
            index++;
        }
        rs.close();
        return prescriptions;
    }

    public Prescription addPrescription(Prescription prescription) throws SQLException {
        String sql = """
                INSERT INTO prescription("patientId","drName",date)
                VALUES (?,?,?)
                """;

        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, prescription.getPatientId());
        ps.setString(2, prescription.getDrName());
        ps.setDate(3, Date.valueOf(prescription.getDate()));
        ps.execute();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt("id");
        prescription.setId(id);
        generatedIds.close();
        return prescription;
    }

    public void deletePrescription(int id) throws SQLException {
        String sql = """
                DELETE FROM prescription
                WHERE id=?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    public void setIsValid(int prescriptionId, boolean isValid) throws SQLException {
        String sql = """
                UPDATE prescription
                SET "isValid"=?
                WHERE id = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBoolean(1, isValid);
        ps.setInt(2, prescriptionId);
        ps.execute();
    }

    public void setTotalPrice(int prescriptionId) throws SQLException {
        Double totalPrice = itemRepository.totalPrice(prescriptionId);
        String sql = """
                UPDATE prescription
                SET "totalPrice"=?
                WHERE id = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, totalPrice);
        ps.setInt(2, prescriptionId);
        ps.execute();
    }

    public Prescription[] showPrescriptionForValidation() throws SQLException {
        String sql = """
                        select * from prescription
                        where  "isValid" is null
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return getPrescriptions(rs);
    }

    public Prescription[] showConfirmedPrescription(int patientId) throws SQLException {
        String sql = """
                        select * from prescription
                        where  "patientId"=? and "isValid" is true
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        ResultSet rs = ps.executeQuery();
        return getPrescriptions(rs);
    }

    public  Prescription[] showRejectedPrescription(int patientId) throws SQLException {
        String sql = """
                        select * from prescription
                        where  "patientId"=? and "isValid" is false
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        ResultSet rs = ps.executeQuery();
        return getPrescriptions(rs);
    }

    public Prescription[] showUncheckedPrescription(int patientId) throws SQLException {
        String sql = """
                        select * from prescription
                        where  "patientId"=? and "isValid" is null
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        ResultSet rs = ps.executeQuery();
        return getPrescriptions(rs);
    }

    /* it does not work for notCheckedPrescriptions (where boolean isValid must be null)
    public static Prescription[] showPrescriptionByStatus(int patientId,Boolean status)throws SQLException{
        String sql = """
                        select * from prescription
                        where  "patientId"=? and "isValid" = ?
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        ps.setBoolean(2,status);
        ResultSet rs = ps.executeQuery();
        return getPrescriptions(rs);
    }*/
}