package utdallas.librarymanagement.service;

import utdallas.librarymanagement.dao.AddBorrowerDAO;

public class AddBorrowerService {
	
	public Integer addBorrower(String fname, String lname, String address,String city, String stateCode, String telephoneNumber) throws Exception
	{
		Integer cardNo = null;
		try
		{
		String addressFull = address + " " + city + " " + stateCode;
		AddBorrowerDAO addBorrowerDAO = new AddBorrowerDAO();
		cardNo = addBorrowerDAO.addBorrower(fname, lname, addressFull, telephoneNumber);
		}
		catch(Exception e)
		{
			throw e;
		}
		return cardNo;
	}

}
