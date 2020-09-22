package bankomat;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Racun> racuni = new ArrayList<Racun>();
	static Racun racun;
	static boolean postoji = false;
	static boolean negativan = false;
	static boolean negativanBroj = false;
	static boolean validanBroj = false;
	static boolean dovoljnoNovca = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner unos = new Scanner(System.in);
		System.out.println("Dobrodosli.");

		menu: while (true) {

			String ime;
			int broj;
			double stanje;

			System.out.println("Molimo, izaberite zeljenu opciju: ");
			System.out.println("Odaberite 1-za kreiranje racuna.");
			System.out.println("Odaberite 2-ta transfer novca.");
			System.out.println("Odaberite 3-za provjeru stanja racuna.");
			System.out.println("Odaberite 0-za izlaz.");
			int opcija = unos.nextInt();

			if (opcija == 1) {
				unos.nextLine();

				System.out.println("Molimo, unesite ime i prezime: ");
				ime = unos.nextLine();
				System.out.println("Sada unesite broj racuna: ");
				broj = unos.nextInt();
				provjeraNegativanBrojRacuna(broj);
				if (negativanBroj)
					continue menu;
				else {
					provjeraPostojanjaIstogRacuna(broj);

					if (postoji)
						continue menu;
					else {
						System.out.println("Iznos na racunu: ");
						stanje = unos.nextDouble();
						validacijaNegativanIznos(stanje);
						if (negativan)
							continue menu;
						else {
							racun = new Racun(broj, ime, stanje);
							racuni.add(racun);
						}
					}
				}
			}
			int prviRacun, drugiRacun;
			double iznosZaTransfer;

			if (opcija == 2) {
				System.out.println("Molimo unesite broj glavnog racuna: ");
				prviRacun = unos.nextInt();
				System.out.println("Molimo unesite broj racuna na koji zelite prebaciti novac: ");
				drugiRacun = unos.nextInt();
				System.out.println("Molimo, unesite iznos za transfer: ");
				iznosZaTransfer = unos.nextDouble();

				transfer(prviRacun, drugiRacun, iznosZaTransfer);
			}

			int num;
			if (opcija == 3) {

				System.out.println("Unesite broj racuna za prikaz detalja: ");
				num = unos.nextInt();
				prikazDetalja(num);
			}
			if (opcija == 0) {
				unos.close();
				break;
			}
		}
	}

	public static void prikazDetalja(int number) {
		for (int i = 0; i < racuni.size(); i++) {
			if (racuni.get(i).brojRacuna == number) {
				System.out.println("Ime vlasnika: " + racuni.get(i).imeVlasnika);
				System.out.println("Iznos na racunu: " + racuni.get(i).iznosNaRacunu);
			}

		}
	}

	public static void provjeraPostojanjaIstogRacuna(int broj) {
		for (int i = 0; i < racuni.size(); i++) {
			if (racuni.get(i).brojRacuna == broj) {
				System.out.println("Nemoguce kreirati racun, molimo pokusajte ponovo");
				postoji = true;
			} else
				postoji = false;

		}
	}

	public static void validacijaNegativanIznos(double broj) {
		if (broj < 0) {
			System.out.println("Neispravan unos, molimo pokusajte ponovo.");
			negativan = true;
		}
	}

	public static void provjeraNegativanBrojRacuna(int broj) {
		if (broj < 0) {
			System.out.println("Nemoguce kreirati racun, molimo pokusajte ponovo.");
			negativanBroj = true;
		}
	}

	public static void transfer(int brojPrvogRacuna, int brojDrugogRacuna, double novac) {

		if (provjeraPostojanjaRacuna(brojPrvogRacuna)) {
			if (provjeraPostojanjaRacuna(brojDrugogRacuna)) {

				for (int i = 0; i < racuni.size(); i++) {
					if (racuni.get(i).brojRacuna == brojPrvogRacuna) {
						if (novac > racuni.get(i).iznosNaRacunu) {
							System.out.println("Nemate dovoljno novca na racunu.");
							break;
						}

					}
				}
				for (int i = 0; i < racuni.size(); i++) {
					if (racuni.get(i).brojRacuna == brojPrvogRacuna) {
						racun.setIznosNaRacunu(racuni.get(i).iznosNaRacunu - novac);
					}
					if (racuni.get(i).brojRacuna == brojDrugogRacuna) {
						racun.setIznosNaRacunu(racuni.get(i).iznosNaRacunu + novac);
					}
				}
			} else
				System.out.println("Racun ne postoji.");
		} else
			System.out.println("Racun ne postoji.");
	}

	public static boolean provjeraPostojanjaRacuna(int broj) {
		for (int i = 0; i < racuni.size(); i++) {
			if (racuni.get(i).brojRacuna == broj) {

				validanBroj = true;

			} else {
				validanBroj = false;
				System.out.println("Racun ne postoji.");
				break;
			}

		}
		return validanBroj;
	}
}