package utdallas.oilTransactionMgmt.VO;

import java.util.ArrayList;

public class paymentMasterVO 
{
    int pid;
    int balance;
    int transactionID;
    int clientID;
    int total;
    ArrayList<paymentVO> paymentList ;
	
    public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<paymentVO> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(ArrayList<paymentVO> paymentList) {
		this.paymentList = paymentList;
	}
    
    
    
    
}
