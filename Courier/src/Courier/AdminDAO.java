package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminDAO {
    public SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
    private static ObservableList<Admin> getAllCouriers(ResultSet rs)throws SQLException,ClassNotFoundException
    {
        ObservableList<Admin> crList= FXCollections.observableArrayList();
        while(rs.next())
        {
            Admin cr=new Admin();
            cr.setCourierId(rs.getInt("ID_COURIER"));
            cr.setNameCourier(rs.getString("NAME_COURIER"));
            cr.setEgnCourier(rs.getString("EGN_COURIER"));
            cr.setPhoneCourier(rs.getString("PHONE_COURIER"));
            cr.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            crList.add(cr);
        }
        return crList;
    }
    private static ObservableList<Admin> getAllOffice(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
        ObservableList<Admin> ofList=FXCollections.observableArrayList();
        while(rs.next())
        {
            Admin cr=new Admin();
            cr.setOfficeId(rs.getInt("ID_OFFICE"));
            cr.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            cr.setOpenOffice(rs.getTimestamp("OPEN_OFFICE"));
            cr.setCloseOffice(rs.getTimestamp("CLOSE_OFFICE"));
            cr.setNameCourier(rs.getString("NAME_COURIER"));
            cr.setEgnCourier(rs.getString("EGN_COURIER"));
            ofList.add(cr);
        }
        return ofList;
    }
    public static void insertOffice(String location, Timestamp open,Timestamp close)throws SQLException,ClassNotFoundException
    {
        String insertO=
                "BEGIN \n"+
                        "insert into OFFICE(ID_OFFICE,LOCATION_OFFICE,OPEN_OFFICE,CLOSE_OFFICE) \n"+
                        "values(sequence_client.nextval,'"+location+"','"+open+"','"+close+"')\n"+
                        "END; \n";
        try {
            DBConn.executeinsert(insertO);
        }catch (SQLException e){
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertCourier(String name_c,String phone,String id_office,String egn)throws SQLException,ClassNotFoundException
    {
        String insertC=
                "BEGIN \n"+
                        "insert into COURIER(ID_COURIER,NAME_COURIER,PHONE_COURIER,OFFICE_ID_OFFICE,EGN_COURIER)" +
                        "values(sequence_client.nextval,'"+name_c+"','"+phone+"','"+id_office+"','"+egn+"')\n"+
                        "END; \n";
        try {
            DBConn.executeinsert(insertC);
        }catch (SQLException e){
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static ObservableList<Admin> selectAllCouriers()throws SQLException,ClassNotFoundException
    {
        String selectallCourier="SELECT C.ID_COURIER,C.NAME_COURIER,C.EGN_COURIER,C.PHONE_COURIER,O.LOCATION_OFFICE \n"+
                "FROM COURIER C \n"+
                "INNER JOIN OFFICE O ON C.OFFICE_ID_OFFICE = O.ID_OFFICE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectallCourier);
            ObservableList<Admin> crList=getAllCouriers(rs);
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Admin> selectAllOffices() throws SQLException, ClassNotFoundException, ParseException {
        String selectalloffices="SELECT O.ID_OFFICE,O.LOCATION_OFFICE,O.OPEN_OFFICE,O.CLOSE_OFFICE,O.LOCATION_OFFICE,C.NAME_COURIER,C.EGN_COURIER \n"+
                "FROM OFFICE O \n"+
                "INNER JOIN COURIER C ON C.OFFICE_ID_OFFICE = O.ID_OFFICE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectalloffices);
            ObservableList<Admin> ofList=getAllOffice(rs);
            return  ofList;
        }catch (SQLException | ParseException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}