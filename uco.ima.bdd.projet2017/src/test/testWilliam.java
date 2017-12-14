package test;

public class testWilliam {

	public static void main(String[] args) {
		String format = "dd/MM/yyyy"; 

		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 

		System.out.println( formater.format( date ) );

	}

}
