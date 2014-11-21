package utdallas.oilTransactionMgmt.VO;

import java.util.Date;

public class AggregateTrxInfoListVO {

	private Date trxTime;
	private int noOfTrx;
	private float totalTrxQuantity;
	private float avgTrxQuantity;
	
	public int getNoOfTrx() {
		return noOfTrx;
	}

	public void setNoOfTrx(int noOfTrx) {
		this.noOfTrx = noOfTrx;
	}

	public float getTotalTrxQuantity() {
		return totalTrxQuantity;
	}

	public void setTotalTrxQuantity(float totalTrxQuantity) {
		this.totalTrxQuantity = totalTrxQuantity;
	}

	public float getAvgTrxQuantity() {
		return avgTrxQuantity;
	}

	public void setAvgTrxQuantity(float avgTrxQuantity) {
		this.avgTrxQuantity = avgTrxQuantity;
	}

	public Date getTrxTime() {
		return trxTime;
	}
	
	public void setTrxTime(Date trxTime) {
		this.trxTime = trxTime;
	}
	
	public AggregateTrxInfoListVO(Date trxTime)
	{	
		this.trxTime = trxTime;
	}

	public AggregateTrxInfoListVO(int noOfTrx,
			float totalTrxQuantity, float avgTrxQuantity) 
	{
		this.noOfTrx=noOfTrx;
		this.totalTrxQuantity =totalTrxQuantity;
		this.avgTrxQuantity=avgTrxQuantity;
	}
	
}
