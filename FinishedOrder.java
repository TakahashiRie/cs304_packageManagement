import java.sql.Date;

public class FinishedOrder extends Order {

    private Date finishedDate;
    private String status;
    public FinishedOrder(String orderid, String senderAddress, String senderName, String receiverAddress, String receiverName,
                         float price, Date dateCreated, Date expectedArrival, Date finishedDate, String status) {
        super(orderid, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
        this.finishedDate = finishedDate;
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }
}
