import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class Person  implements Serializable  {
	String fn ;
	String ln ;
	Set<Address> addr = new HashSet<>();
	LocalDateTime created = LocalDateTime.now();


	@Override
	public int hashCode() {
		final int prime = 2;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((fn == null) ? 0 : fn.hashCode());
		result = prime * result + ((ln == null) ? 0 : ln.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (fn == null) {
			if (other.fn != null)
				return false;
		} else if (!fn.equals(other.fn))
			return false;
		if (ln == null) {
			if (other.ln != null)
				return false;
		} else if (!ln.equals(other.ln))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "Person [fn=" + fn + ", ln=" + ln + ", addr=" + addr + ", created=" + created + "]";
	}


	public Person(String fn, String ln) {
		super();
		this.fn = fn;
		this.ln = ln;
	}


}