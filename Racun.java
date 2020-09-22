package bankomat;

public class Racun {
	int brojRacuna;
	String imeVlasnika;
	double iznosNaRacunu;

	public Racun() {

	}

	public void setIznosNaRacunu(double iznosNaRacunu) {
		this.iznosNaRacunu = iznosNaRacunu;
	}

	public Racun(int brojRacuna, String imeVlasnika, double iznosNaRacunu) {

		this.brojRacuna = brojRacuna;
		this.imeVlasnika = imeVlasnika;
		this.iznosNaRacunu = iznosNaRacunu;
	}

}
