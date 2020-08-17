import java.util.Set;
import java.util.stream.Collectors;

class InMem extends Actions
{

	public int save(int id, Person p)
	{
		if(db.containsKey(id)) {
			System.out.println("Already exists");
			return id;
		}
		db.put(id, p);
		return id;
	}

	public Person get(int id) {
		return db.get(id) ;
	}

	public Person edit(int id, String fn, String ln) {
		Person p = get(id) ;
		if(p == null) {
			System.out.println("No person with ID " + id);
			return null;
		}
		p.ln = ln;
		p.fn = fn;
		db.put(id, p);
		return p;
	}

	public Person addMultipleAddress(int id, Set<Address> addList) {
		Person p = get(id) ;
		if(p == null) {
			System.out.println("No person with ID " + id);
			return null;
		}
		p.addr.addAll(addList);
		return p;
	}

	public Person updateSingleAddress(int id, Address a) {
		Person p = get(id) ;
		if(p == null) {
			System.out.println("No person with ID " + id);
			return null;
		}
		Set<Address> delAddress = p.addr.stream().filter( (address) -> address.equals(a)).collect(Collectors.toSet());
		p.addr.removeAll(delAddress);
 
		p.addr.add(a);
		return p; 
	}

	public Person deleteAddress(int id, int addrId) {
		Address delAddr = null ;
		Person p ;
		p = get(id);
		if (p == null) {
			System.out.println("No Person with id " + id);
			return null;
		}
		
		if(p.addr == null || p.addr.size() == 0) {
			System.out.println(p.fn + "" + p.ln + " has no address to remove");
			return null;
		}
		p.addr.stream().filter
		( 
				(a) -> a.id == addrId

				).peek( (eachDelAddress) -> p.addr.remove(eachDelAddress))
		;
		System.out.println(delAddr + " deleted ");
		System.out.println(p);
		return p ;
	}

	public Person delete(int id) {
		return db.remove(id) ;
	}
	
	public void list() {
		if(db.keySet().size() == 0) {
			System.out.println("No Persons in store");
			return ;
		}
		db.forEach(
					(k, v) -> System.out.println("\nCreated : " +v.created + "; ID: " + k + "\tPerson : " + v.fn + " " + v.ln + "\nAddress list " + v.addr)
				);
		return ;
	}
}