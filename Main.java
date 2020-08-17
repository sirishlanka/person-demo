import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	static Integer addrCounter = readLastSavedCounter ();
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner (Main.class.getClassLoader().getResourceAsStream("help_text.txt"));
		while (sc.hasNextLine())
			System.out.println(sc.nextLine());
		sc.close();

		Runtime.getRuntime().addShutdownHook(new PersistMyMap(Target.getDatastore()));
		sc = new Scanner(System.in);

		final int choice = sc.nextInt();


		String fn;
		String ln;
		Person p;
		String street;
		String city ;
		String state ;
		String code ;


		switch ( choice ) {
		case 1: 
			System.out.println("Enter FN, LN");
			fn = sc.nextLine();
			ln = sc.nextLine();
			
//			System.out.println("Enter LN");
//			ln = sc.nextLine();
			
			p = new Person(fn, ln);
			int pId = new InMem().save( p.hashCode(), p) ;
			System.out.println("saved with Id " + pId);
			break;
		case 2: 

			System.out.println("Enter ID");
			int id;
			id = sc.nextInt();
			
			sc.nextLine();
			System.out.print("Enter FN");
			String fn1;
			fn1 = sc.nextLine();
			
			System.out.print("Enter LN");
			String ln1;
			ln1 = sc.nextLine();

			p = new InMem().edit(id, fn1, ln1) ;
			System.out.println("Person updated " + p);
			break;

		case 3:
			System.out.println("Enter ID");
			id = sc.nextInt();
			p = new InMem().delete(id) ;
			System.out.println("Removed " + p);
			break;
		case 4:
			System.out.println("Enter ID");
			id = sc.nextInt();
			String input = "" ;
			Set<Address> addr = new HashSet<>();
			while (!(input = sc.nextLine()).equalsIgnoreCase("n")) {
				System.out.println("Enter Street: ");
				street = sc.nextLine();

				System.out.println("Enter City: ");
				city = sc.next ();

				sc.nextLine();
				System.out.println("Enter State: ");
				state = sc.nextLine();

				sc.nextLine();
				System.out.println("Enter Pin Code: ");
				code = sc.nextLine();
				
				addrCounter += 1;
				addr.add(new Address (street, city, state, code, addrCounter)) ;
				System.out.println("Add more address ? Y/N ");
			}

			System.out.println("Saving " + addr);
			new InMem().addMultipleAddress(id, addr) ;
			break;
		case 5:
			System.out.println("Enter ID: ");
			id = sc.nextInt();// pid

			System.out.println("Enter Address ID: ");
			int addrId = sc.nextInt();//a id


			System.out.println("Enter Street: ");
			street = sc.nextLine();

			sc.nextLine();
			System.out.println("Enter City: ");
			city = sc.nextLine();

			System.out.println("Enter State: ");
			state = sc.nextLine();

			sc.nextLine();
			System.out.println("Enter Pin Code: ");
			code = sc.nextLine();


			Address a = new Address (street, city, state, code, addrId) ;
			new InMem().updateSingleAddress(id, a) ;
			break;
		case 6: 
			System.out.println("Enter ID: ");
			id = sc.nextInt();

			System.out.println("Enter Address ID: ");
			addrId = sc.nextInt();//a id
			p = new InMem().deleteAddress(id,  addrId) ;
			System.out.println("Address deleted in " + p);
			break;
		case 7:
			new InMem().list();
			break;
		default:
			System.out.println("Unknown Option '" + choice + "'");
			break;
		}
		sc.close();
	}

	
	
	private static Integer readLastSavedCounter() {
		Integer i = null;
		try {
			try(FileInputStream fos = new FileInputStream("addressCounter.ser")) {
				try(ObjectInputStream oos = new ObjectInputStream(fos)){
					//////////////////////////////////
					i = (Integer)oos.readObject();
					//////////////////////////////////
					return i ;
				}
			}
		} catch (Exception e) { }
		return new Integer(0);
	}
}
