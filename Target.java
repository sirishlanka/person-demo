import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
class Target
{
	final static 
	Target d = new Target ();
	
	static 
	Map<Integer, Person> db =  null;
	
	static
	{
		try {
			try(FileInputStream fos = new FileInputStream("personMap.ser")) {
				try(ObjectInputStream oos = new ObjectInputStream(fos)){
					//////////////////////////////////
					db = (HashMap)oos.readObject();
					//////////////////////////////////
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		if(db == null) {
			/////////////////////////
			db = new HashMap<> ();
			////////////////////////
		}
	}
	
	
	/*
	 * public static Target getInstance () { return d; }
	 */
	

	public static Map<Integer, Person> getDatastore () {
		return db;
	}

}