package utdallas.oilTransactionMgmt.MangedBean;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import utdallas.oilTransactionMgmt.Dao.PaymentDao;
import utdallas.oilTransactionMgmt.VO.TransactionVO;
import utdallas.oilTransactionMgmt.VO.UserVO;
import utdallas.oilTransactionMgmt.VO.paymentVO;

public class PaymentMB 
{
  private int trxID =-1;
  private int clientID=-1;
  private ArrayList<paymentVO> paymentList ;
  private TransactionVO transVO = new TransactionVO();
  private paymentVO payVO = new paymentVO();
  
  public PaymentMB() 
  {
	super();
	FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
    transVO=  (TransactionVO) session.getAttribute("transactionObj");
    this.trxID = transVO.getTransactionID();
    paymentList = new PaymentDao().fetchPayment(trxID);
  }
  
  public void pay(ActionEvent ae)
  {
	  FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		if(user.getUserType()==1)
		{
			this.payVO.setClientID(user.getUserID());
			this.payVO.setTraderID(user.getUserID());
		}
		else
		{
			this.payVO.setClientID(transVO.getClientID());
			this.payVO.setTraderID(user.getUserID());
		}
		this.payVO.setTrxID(transVO.getTransactionID());
		PaymentDao dao = new PaymentDao();
		dao.paymentExecute(payVO);
		paymentList = new PaymentDao().fetchPayment(trxID);
		transVO = dao.getTransactionDetails(trxID);
  }
  
  public void paymentCancel(ActionEvent ae)
  {
	  for(int i=0;i<paymentList.size();i++)
	  {
		  paymentVO vo = paymentList.get(i);
		  if(vo.isSelect())
		  {
			  PaymentDao dao = new PaymentDao();
			  dao.cancelPayment(vo.getPid());
			  paymentList = new PaymentDao().fetchPayment(trxID);
				transVO = dao.getTransactionDetails(trxID);
			  break;
		  }
	  }
	 
  }

public int getTrxID() {
	return trxID;
}

public void setTrxID(int trxID) {
	this.trxID = trxID;
}

public int getClientID() {
	return clientID;
}

public void setClientID(int clientID) {
	this.clientID = clientID;
}

public ArrayList<paymentVO> getPaymentList() {
	return paymentList;
}

public void setPaymentList(ArrayList<paymentVO> paymentList) {
	this.paymentList = paymentList;
}

public TransactionVO getTransVO() {
	return transVO;
}

public void setTransVO(TransactionVO transVO) {
	this.transVO = transVO;
}

public paymentVO getPayVO() {
	return payVO;
}

public void setPayVO(paymentVO payVO) {
	this.payVO = payVO;
}
  
  
  
  
  
}