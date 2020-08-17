import java.io.Serializable;

class Address implements Serializable {
	String street;
	String city;
	String state ;
	String postCode;
	int id;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "street=" + street + ", city=" + city + ", state=" + state + ", postCode=" + postCode + ", id=" + id;
	}


	public Address(String street, String city, String state, String postCode, int id) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.id = id;
	}

	public Address(String street, String city, String state, String postCode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
	}





}