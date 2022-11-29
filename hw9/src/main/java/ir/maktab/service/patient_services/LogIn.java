package ir.maktab.service.patient_services;

import ir.maktab.entity.Patient;
import ir.maktab.repository.impl.PatientRepositoryImpl;

import java.sql.SQLException;

public class LogIn {
    static PatientRepositoryImpl patientRepository=new PatientRepositoryImpl();
    public String nameValidation(String name){
        if(name==null || name.matches(""))
            throw new RuntimeException ("Name Cannot Be Null");
        if(name.length()>32)
            throw new RuntimeException("Name Cannot Have More Than 32 Characters.");
        return name;
    }

    public String nationalCodeValidation(String nationalCode){
        if (nationalCode.length()!=3 || !nationalCode.matches("^\\d(\\d)?(\\d)?$"))
            throw new RuntimeException("National Code Must Have Exactly 3 Digits.");
        return nationalCode;
    }

    public static void logInReaction(String name , String nationalCode) throws SQLException {
        if(patientRepository.nationalCodeContains(nationalCode)){
            Patient patient=patientRepository.getPatient(nationalCode);
            if(name.matches(patient.getName())) {
                //go to patient menu
                System.out.println("hora");
            }else
                System.out.println("Name & National Code Are Not Match.");
            // should we do anything more? or may I Handle this with throwing an exception?
        }else{
            patientRepository.addPatient(new Patient(name,nationalCode));
            // go to patient Menu
        }
    }
}
