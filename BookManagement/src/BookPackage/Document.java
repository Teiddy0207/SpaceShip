package BookPackage;

public class Document {
	private String id;
	private String name;
	
	public Document()
	{
		this.id = "";
		this.name = "";
	}
	
	
	public Document(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public static void main(String[] args) {
	

	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
