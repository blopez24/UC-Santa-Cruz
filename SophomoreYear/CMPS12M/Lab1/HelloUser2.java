//----------------------------------------------------------------------------------
// Bryan Lopez
// blopez24 (1612626)
//-------------------------------------------------------------------------
// HelloUser2.java
// Prints only the line ("Hello <USER>")
//-------------------------------------------------------------------------
class HelloUser2
{
	public static void main(String[] args)
	{
		String userName = System.getProperty("user.name");

		System.out.println("Hello "+userName);
	}
}
