package utdallas.librarymanagement.bean;

public class BookList {

	private String bookName;
	private String bookId;
	private String authorName;
	private String branchID;
	private int noOfCopiesAvailable;
	private int noOfCopiesInventored;

	
	/**
	 * @return the branchID
	 */
	public String getBranchID() {
		return branchID;
	}

	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public BookList(String bookTitle, String bookID, String bookAuthor, int noOfAvailablecopies, int noOfCopiesInventored, String branchID)
	{
		this.bookName = bookTitle;
		this.bookId = bookID;
		this.branchID = branchID;
		this.authorName = bookAuthor;
		this.noOfCopiesAvailable = noOfAvailablecopies;
		this.noOfCopiesInventored= noOfCopiesInventored;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName
	 *            the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the noOfCopiesAvailable
	 */
	public int getNoOfCopiesAvailable() {
		return noOfCopiesAvailable;
	}

	/**
	 * @param noOfCopiesAvailable
	 *            the noOfCopiesAvailable to set
	 */
	public void setNoOfCopiesAvailable(int noOfCopiesAvailable) {
		this.noOfCopiesAvailable = noOfCopiesAvailable;
	}

	/**
	 * @return the noOfCopiesInventored
	 */
	public int getNoOfCopiesInventored() {
		return noOfCopiesInventored;
	}

	/**
	 * @param noOfCopiesInventored
	 *            the noOfCopiesInventored to set
	 */
	public void setNoOfCopiesInventored(int noOfCopiesInventored) {
		this.noOfCopiesInventored = noOfCopiesInventored;
	}

}
