package ir.maktab.entity;

import java.time.LocalDate;

public class Prescription {
    int id;
    int patientId;
    private String drName;
    LocalDate date;
    Item[] items = new Item[10];
    Boolean isValid;
    Double totalPrice;
    Boolean hasPaid;

    public Prescription(int patientId, String drName, LocalDate date) {
        this.patientId = patientId;
        this.drName = drName;
        this.date = date;
    }
    public Prescription(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Boolean isValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean hasPaid() {
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Double getTotalPrice(){
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                " | Dr Name: " + drName +
                " | Date: " + date +
                " | Total Price: " + totalPrice ;
    }

    public static String showPrescription(Prescription prescription){
        return "Id: " + prescription.id+
                " | Dr Name: " + prescription.drName+
                " | Date: " + prescription.date;
    }

    public static String[] showPrescriptions(Prescription[] prescriptions) {
        String[] output = new String[100];
        for (int i = 0; i < 100; i++) {
            if (prescriptions[i] == null)
                break;
            output[i] = showPrescription(prescriptions[i]);
        }
        return output;
    }

    public static String[] showConfirmedPrescriptions(Prescription[] prescriptions){
        String[] output = new String[100];
        for (int i = 0; i < 100; i++) {
            if (prescriptions[i] == null)
                break;
            output[i] = prescriptions[i].toString();
        }
        return output;
    }
}