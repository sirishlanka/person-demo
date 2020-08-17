import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

class PersistMyMap extends Thread  {
	final Map<Integer, Person> map;
	PersistMyMap(Map m ) {
		super.setDaemon(true);
		map = m;
	}


	public void run () {
		System.out.println("SAVING STATE ..");
		try(FileOutputStream fos = new FileOutputStream("personMap.ser")) {
			try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(map);
				System.out.println("Serialized HashMap data is saved in hashmap.ser");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		try(FileOutputStream fos = new FileOutputStream("addressCounter.ser")) {
			try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(Main.addrCounter);
				System.out.println("Serialized Address data counter is saved in addressCounter.ser with last value " + Main.addrCounter);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}



}