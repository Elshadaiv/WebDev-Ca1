import java.util.Map;

public class logoff {
	String result = "success";
	
	   private Map<String, Object> session;

	    public void setSession(Map<String, Object> session) 
	    {
	        this.session = session;
	    }

	    public String logoff() 
	    {
	        if (session != null) 
	        {
	            session.clear(); //logoff session
	        }
	        return result;
	    }
}
