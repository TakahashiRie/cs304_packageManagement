package cs304_packageManagement;

import cs304_packageManagement.Order;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CreateOrder {
    private Connection con;

    public CreateOrder(){

        try
        {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // may be oracle.jdbc.driver.OracleDriver as of Oracle 11g

        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            System.exit(-1);
        }

        try
        {
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug","","");

            System.out.println("\nConnected to Oracle!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

        }

    }
    public void addOrder(Order o) throws Exception {

        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO CreateOrder VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1,o.getOrderid());
            ps.setString(2,o.getSenderName());
            ps.setString(3,o.getSenderAddress());

            ps.setString(4,o.getReceiverAddress());
            ps.setString(5,o.getReceiverName());
            ps.setDouble(6,o.getPrice());
            ps.setDate(7,o.getDateCreated());
            ps.setDate(8,o.getExpectedArrival());

            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            try {
                // undo the insert
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    public Order selectOrder(String oID) throws Exception {
        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from CREATEORDER WHERE ORDERID = oID");
            return getOrder(rs);
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }


    //return list of values in a selected column
    public List<Object> selectColumn(String colName) throws Exception{
        Statement stmt = null;
        List<Object> columnVal = new ArrayList<>();
        ResultSet rs;
        int i = 1;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT "+ colName + "FROM CREATEORDER");

            while (rs.next()) {
                Object tempObj = rs.getObject(i);
                columnVal.add(tempObj);
                i++;
            }
            return columnVal;
        }
        finally{
            assert stmt != null;
            stmt.close();
        }

    }



    //return a list of all orders(all column)
    public List<Order> getAllOrders() throws Exception {
        List<Order> orderslist = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from CREATEORDER");



            while (rs.next()) {
                Order tempOrder = getOrder(rs);
                orderslist.add(tempOrder);
            }


            return orderslist;
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }

    //helper function that return a single column for getAllOrders
    private static Order getOrder(ResultSet rs) throws SQLException {
        String orderid = rs.getString("ORDERID");
        String senderAddress = rs.getString("SENDERADDRESS");
        String senderName = rs.getString("SENDERNAME");
        String receiverAddress = rs.getString("RECEIVERADDRESS");
        String receiverName = rs.getString("RECEIVERNAME");
        float price = rs.getFloat("PRICE");
        Date dateCreated = rs.getDate("DATECREATED");
        Date expectedArrival = rs.getDate("EXPECTEDARRIVAL");



        return new Order(orderid, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
    }




    public static void main(String[] args) {
        CreateOrder c = new CreateOrder();
    }
}

