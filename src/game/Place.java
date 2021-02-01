package game;

public class Place {
	private String capital = "";
	private String country = "";

	public Place(String capital, String country) {
		this.capital = capital;
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	public String getCountry() {
		return country;
	}
}
