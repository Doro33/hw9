package ir.maktab.repository.impl;

import ir.maktab.config.AppConnection;
import ir.maktab.entity.Drug;
import ir.maktab.entity.Item;
import ir.maktab.entity.Prescription;

import java.sql.*;

public class ItemRepositoryImpl {
    private static final Connection connection = AppConnection.getConnection();

    public  Item addItem (Item item) throws SQLException {
        String sql = """
                INSERT INTO item ("prescriptionId",row,drug,quantity)
                VALUES (?,?,?,?)
                """;

        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,item.getPrescriptionId());
        ps.setInt(2,item.getRow());
        ps.setString(3,item.getDrug().getName());
        ps.setInt(4,item.getDrug().getQuantity());

        ps.execute();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt("id");
        item.setId(id);
        ps.close();
        generatedIds.close();
        return item;
    }
    public  void deleteItem(int itemId) throws SQLException{
            String sql = """
                DELETE FROM item
                WHERE id=?
                """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.execute();
            ps.close();
    }
    public void editItem(int itemId,Drug drug)throws SQLException{
        String sql = """
                UPDATE item
                SET drug=?,quantity=?
                WHERE id = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,drug.getName());
        ps.setInt(2, drug.getQuantity());
        ps.setInt(3, itemId);
        ps.execute();
        ps.close();
    }
    public  Item getItem(int itemId) throws SQLException {
        String sql = """
                Select * from item
                where id=?;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, itemId);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Item item =new Item();
        item.setId(itemId);
        item.setPrescriptionId(rs.getInt("prescriptionId"));
        item.setRow(rs.getInt("row"));
        String drugName = rs.getString("drug");
        int quantity = rs.getInt("quantity");
        item.setDrug(new Drug(drugName,quantity));
        item.setDoesExist(rs.getBoolean("doesExist"));
        item.setUnitPrice(rs.getDouble("unitPrice"));
        item.setRetailPrice(rs.getDouble("retailPrice"));
        ps.execute();
        ps.close();
        rs.close();
        return item;
    }
    // I can remove boolean doesExist from method Signature. I Haven't decided yet.
    public  void setPrice(int itemId,double unitPrice) throws SQLException {
        int quantity= getItem(itemId).getDrug().getQuantity();
        Double retailPrice = unitPrice * quantity;
        String sql = """
                UPDATE item
                SET "doesExist"=? , "unitPrice"=?,"retailPrice"=?
                WHERE id = ?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setDouble(2, unitPrice);
        ps.setDouble(3, retailPrice);
        ps.setInt(4,itemId);
        ps.execute();
        ps.close();
    }
    public Double totalPrice(int prescriptionId) throws SQLException {
        Double totalPrice;
        String sql = """
                Select sum("retailPrice")from item
                where "prescriptionId"=?;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, prescriptionId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        totalPrice=rs.getDouble("sum");
        return totalPrice;
    }

    public Item[] showItemsOfPrescription(int prescriptionId) throws SQLException {
        String sql = """
                        select * from item
                        where  "prescriptionId"=? 
                        order by id;
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,prescriptionId);
        ResultSet rs = ps.executeQuery();
        Item[] items = new Item[15];
        int index = 0;
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setPrescriptionId(prescriptionId);
            item.setRow(rs.getInt("row"));
            String drugName =rs.getString("drug");
            int quantity  =rs.getInt("quantity");
            item.setDrug(new Drug(drugName,quantity));
            item.setDoesExist(rs.getBoolean("doesExist"));
            item.setUnitPrice(rs.getDouble("unitPrice"));
            item.setRetailPrice(rs.getDouble("retailPrice"));
            items[index] = item;
            index++;
        }
        ps.close();
        rs.close();
        return items;
    }

    public void deletePrescriptionItems(int prescriptionId) throws SQLException{
        String sql = """
                DELETE FROM item
                WHERE "prescriptionId"=?
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, prescriptionId);
        ps.execute();
        ps.close();
    }
}