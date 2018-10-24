package fall2018.csc2017.slidingtiles;

public class Client {
	private String name;

	private String password;

	private HashMap<String, Game> history;

	public Client(String name, String password){
		this.name=name;
		this.password=password;
		this.history=new HashMap<String, Game>();
	}

	public void changeName(String name){
		this.name=name;
	}

	public void changePassword(String ps){
		this.password=ps;
	}

	public String getName(){
		return name;
	}

	public String getPassword(){
		return password
	}


}