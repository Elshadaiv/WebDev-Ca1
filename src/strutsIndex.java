
public class strutsIndex {
	private String message;
	public String getMessage()
	{
		return message;
	}
	
	public String indexMethod()
	{
		message = "Welcome!";
		return "success";
	}// strutsindex is the home page and is dictacted by the jsp file index.jsp that allows the user to click on the links to the other funtions 
	
	//it only prints out the message of welcome on the page
}
