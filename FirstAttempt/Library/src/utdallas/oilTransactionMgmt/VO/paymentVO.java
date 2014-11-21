package utdallas.oilTransactionMgmt.VO;

import java.util.Date;

public class paymentVO 
{
  int pid ;
  Date date ;
  float amount;
  int status;
  String statusStr ;
  int clientID;
  int traderID;
  int trxID;
  String dateStr;
  boolean select =false;
  
		public String getDateStr() {
			return dateStr;
		}
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}
		public int getPid() {
			return pid;
		}
		public void setPid(int pid) {
			this.pid = pid;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
			if(this.status==1)
			{
				this.setStatusStr("Cancelled");
			}
			else
			{
				this.setStatusStr("Not Cancelled");
			}
		}
		public String getStatusStr() {
			return statusStr;
		}
		public void setStatusStr(String statusStr) {
			this.statusStr = statusStr;
		}
		public int getClientID() {
			return clientID;
		}
		public void setClientID(int clientID) {
			this.clientID = clientID;
		}
		public int getTraderID() {
			return traderID;
		}
		public void setTraderID(int traderID) {
			this.traderID = traderID;
		}
		public int getTrxID() {
			return trxID;
		}
		public void setTrxID(int trxID) {
			this.trxID = trxID;
		}
		public boolean isSelect() {
			return select;
		}
		public void setSelect(boolean select) {
			this.select = select;
		}
		  
  
}
