package wars; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "team number";
        
        details[1] = "Bemanzadeh";
        details[2] = "Barbod";
        details[3] = "23006294";

        details[4] = "Atabay";
        details[5] = "Zeynepsu";
        details[6] = "22105696";

        details[7] = "Studzinski";
        details[8] = "Dominik";
        details[9] = "21047881";


        details[10] = "Farhan";
        details[11] = "Amna";
        details[12] = "23010250";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
