package ir.maktab.entity;

public class Item {
    int id;
    int prescriptionId;

    int row; // row must be 1 - 10 ;
    Drug drug;
    Boolean doesExist;
    Double unitPrice;

    Double retailPrice;

    public Item(Drug drug) {
        this.drug = drug;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Boolean isDoesExist() {
        return doesExist;
    }

    public void setDoesExist(Boolean doesExist) {
        this.doesExist = doesExist;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return
                "Item Id: " + id +
                        " | Prescription Id: " + prescriptionId +
                        " | Row: " + row +
                        " | " + drug +
                        " | Does Exist: " + doesExist +
                        " | Unit Price: " + unitPrice +
                        " | Retail Price: " + retailPrice;
    }

    public static String[] showItemsOfPrescription(Item[] items){
        String[] output=new String[100];
        for (int i =0 ; i < 100; i++){
            if(items[i]==null)
                break;
            output[i]=items[i].toString();
         }
        return output;
    }
}
