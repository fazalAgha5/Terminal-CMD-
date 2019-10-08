
public class Link {

	private String address;
	
	private Link next;
	 
	public Link(String a){
		address=a;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}
	
	public String toString (){
		return address;
	}
}
