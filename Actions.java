import java.util.Map;
import java.util.Set;

class Actions {

	Map<Integer, Person> db ;
	public Actions()
	{
		db = Target.getDatastore();
	}
	
	public int save(int id, Person p ) {
		return -1;
	}


	public Person delete(int id) {
		return null;
	}


	public Person edit(int id, String fn, String ln) {
		return null;
	}


	public Person addMultipleAddress(int id, Set<Address> addList) {
		return null ;
	}

	public Person updateSingleAddress(int id, Address a) {
		return null;
	}

	public Person deleteAddress(int id, int addrId) {
		return null;
	}
	
	public void list() {
	}

	protected void save () {

	}

}