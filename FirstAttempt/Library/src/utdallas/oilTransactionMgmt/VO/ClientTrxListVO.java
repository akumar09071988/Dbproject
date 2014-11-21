package utdallas.oilTransactionMgmt.VO;

import java.util.Date;

public class ClientTrxListVO {

	private int trxid;
	//private int cid;
	private Date trxTime;
	private float quantity;
	private boolean trxType;
	private float trxAmount;
	private float feeAmount;
	private float feeInOil;
	private int commType;
	private boolean isCancelled;
	private int uid;

	
	public int getTrxid() {
		return trxid;
	}
	
	public void setTrxid(int trxid) {
		this.trxid = trxid;
	}
	
//	public int getCid() {
//		return cid;
//	}
//	
//	public void setCid(int cid) {
//		this.cid = cid;
//	}
	
	public Date getTrxTime() {
		return trxTime;
	}
	
	public void setTrxTime(Date trxTime) {
		this.trxTime = trxTime;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	public boolean getTrxType() {
		return trxType;
	}
	
	public void setTrxtype(boolean trxType) {
		this.trxType = trxType;
	}
	
	public float getTrxAmount() {
		return trxAmount;
	}
	
	public void setTrxAmount(float trxAmount) {
		this.trxAmount = trxAmount;
	}
	
	public float getFeeAmount() {
		return feeAmount;
	}
	
	public void setFeeAmount(float feeAmount) {
		this.feeAmount = feeAmount;
	}
	
	public float getFeeInOil() {
		return feeInOil;
	}
	
	public void setFeeInOil(float feeInOil) {
		this.feeInOil = feeInOil;
	}
	
	public int getCommType() {
		return commType;
	}
	
	public void setCommType(int commType) {
		this.commType = commType;
	}
	
	public boolean getIsCancelled() {
		return isCancelled;
	}
	
	public void setIsCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public ClientTrxListVO(int trxid,Date trxTime,float quantity,boolean trxType,float trxAmount,float feeAmount,float feeInOil,int commType,boolean isCancelled,int uid)
	{
		this.trxid = trxid;
		//this.cid = cid;
		this.trxTime = trxTime;
		this.quantity = quantity;
		this.trxType = trxType;
		this.trxAmount= trxAmount;
		this.feeAmount = feeAmount;
		this.feeInOil = feeInOil;
		this.commType = commType;
		this.isCancelled= isCancelled;
		this.uid=uid;
	}
	
}
