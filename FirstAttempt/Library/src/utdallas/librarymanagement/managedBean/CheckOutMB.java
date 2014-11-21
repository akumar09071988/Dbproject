package utdallas.librarymanagement.managedBean;

import utdallas.librarymanagement.service.CheckOutService;

public class CheckOutMB {

	private String bookID;
	private Integer branchID = null;
	private Integer cardNo = null;
	private String message;
	
	/**
	 * @return the branchID
	 */
	public Integer getBranchID() {
		return branchID;
	}
	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the bookID
	 */
	public String getBookID() {
		return bookID;
	}
	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String checkOut()
	{
		Integer loanID = null;
		try
		{
			if(this.bookID.isEmpty()&&(this.branchID==null||this.branchID==0)&&(this.cardNo==null||this.cardNo==0))
			{
				throw new Exception("Please enter atleast one value");
			}
		CheckOutService checkOut =new CheckOutService();
		loanID = checkOut.checkOut(this.bookID, this.branchID, this.cardNo);
		}catch(Exception e)
		{
			this.setMessage(e.getMessage());
			return "failure";
		}
		this.setBranchID(null);
		this.setCardNo(null);
		this.setMessage("Check out successfull!!! Your loan ID: " + loanID);
		return "success";
	}
	public String goBack()
	{
		return "home";
	}
	
}
