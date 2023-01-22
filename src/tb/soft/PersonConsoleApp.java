package tb.soft;

import java.util.Arrays;
import java.io.*;
import java.util.*;


/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";


	private static final String MENU_KOLEKCJI =
			"    M E N U   K O L E K C J I  \n" +
					"1 - Hashset				\n" +
					"2 - Treeset        		\n" +
					"3 - Arraylist	   			\n" +
					"4 - LinkedList   			\n" +
					"5 - HashMap   				\n" +
					"6 - TreeMap				\n" +
					"0 - Wyjdź        			\n";

	private static final Collections collections = new Collections();

	private static final String MENU =
				"    M E N U   G Ł Ó W N E  \n" +
				"1 - Podaj dane nowej osoby \n" +
				"2 - Usuń dane osoby        \n" +
				"3 - Modyfikuj dane osoby   \n" +
				"4 - Wczytaj dane z pliku   \n" +
				"5 - Zapisz dane do pliku   \n" +
				"6 - Wyświetl wszystkich	\n" +
				"7 - equals() i hashcode() 	\n" +
				"0 - Zakończ program        \n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;

	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);
		try {
			loadLista();
		} catch (PersonException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		while (true) {
				try {
					UI.clearConsole();
					showCurrentPerson();
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					collections.add(currentPerson);
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					collections.remove(currentPerson);
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					collections.remove(currentPerson);
					changePersonData(currentPerson);
					collections.add(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					collections.add(currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}

					break;
				case 6:
				{MenuKolekcji();}
					break;
				case 7:{
					showEqualsHashDiff();}
					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} }// koniec pętli while

		public void MenuKolekcji() throws PersonException{
			while(true){
				UI.clearConsole();
				showCurrentPerson();

				switch (UI.enterInt(MENU_KOLEKCJI + "==>> ")){
					case 1: showHashSet(); break;
					case 2: showTreeSet(); break;
					case 3: showArrayList(); break;
					case 4: showLinkedList(); break;
					case 5: showHashMap(); break;
					case 6: showTreeMap(); break;
					case 0: return;}}}
	


		public void showHashSet() throws PersonException{
			Set<Person> hashSet1 = collections.getHashSet1();
			Set<EqualsHash> hashSet2 = collections.getHashSet2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("Hashset - brak equals() i hashCode()");
			for(Person person: hashSet1) {
				showPerson(person);
				if(person.equals(currentPerson)) UI.printMessage("Ta sama osoba");
				else UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("Hashset - equals() i hashCode()");
			for(EqualsHash EqualsHash:hashSet2){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showTreeSet() throws PersonException{
			Set<Person> treeSet1 = collections.getTreeSet1();
			Set<EqualsHash> treeSet2 = collections.getTreeSet2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("TreeSet - brak equals() i hashCode()");
			for(Person person: treeSet1) {
				showPerson(person);
				if(person.equals(currentPerson))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("TreeSet - equals() i hashCode()");
			for(EqualsHash EqualsHash:treeSet2){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showArrayList() throws PersonException{
			List<Person> arrayList1 = collections.getArrayList1();
			List<EqualsHash> arrayList2 = collections.getArrayList2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("ArrayList - brak equals() i hashCode()");
			for(Person person: arrayList1){
				showPerson(person);
				if(person.equals(currentPerson))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("ArrayList - equals() i hashCode()");
			for(EqualsHash EqualsHash:arrayList2){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showLinkedList() throws PersonException{
			List<Person> linkedList1 = collections.getLinkedList1();
			List<EqualsHash> linkedList2 = collections.getLinkedList2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("LinkedList - brak equals() i hashCode()");
			for(Person person: linkedList1){
				showPerson(person);
				if(person.equals(currentPerson))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("LinkedList - equals() i hashCode()");
			for(EqualsHash EqualsHash:linkedList2){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showHashMap() throws PersonException{
			Map<Person, Integer> hashMap1 = collections.getHashMap1();
			Map<EqualsHash, Integer> hashMap2 = collections.getHashMap2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("HashMap - brak equals() i hashCode()");
			for(Person person: hashMap1.keySet()){
				showPerson(person);
				if(person.equals(currentPerson))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("HashMap - equals() i hashCode()");
			for(EqualsHash EqualsHash:hashMap2.keySet()){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showTreeMap() throws PersonException{
			Map<Person, Integer> treeMap1 = collections.getTreeMap1();
			Map<EqualsHash, Integer> treeMap2 = collections.getTreeMap2();
			EqualsHash currentPersonEqualHash = new EqualsHash(currentPerson);
			UI.printMessage("TreeMap - brak equals() i hashCode()");
			for(Person person: treeMap1.keySet()){
				showPerson(person);
				if(person.equals(currentPerson))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
			UI.clearConsole();
			UI.printMessage("TreeMap - equals() i hashCode()");
			for(EqualsHash EqualsHash:treeMap2.keySet()){
				showPerson(EqualsHash);
				if(EqualsHash.equals(currentPersonEqualHash))
					UI.printMessage("Ta sama osoba");
				else
					UI.printMessage("Inna osoba");
			}
		}

		public void showEqualsHashDiff() throws PersonException {
			EqualsHash EqualsHash = new EqualsHash(currentPerson);
			showCurrentPerson();
			UI.printMessage("hashCode() dla niezdefiniowanej" + currentPerson.hashCode() );
			UI.printMessage("hashCode() dla zdefiniowanej" + EqualsHash.hashCode());

			Person clonedPerson = new Person(currentPerson.getFirstName(), currentPerson.getLastName());
			clonedPerson.setBirthYear(currentPerson.getBirthYear());
			clonedPerson.setJob(currentPerson.getJob());
			EqualsHash clonedPersonEqualHash = new EqualsHash(clonedPerson);

			UI.printMessage("Kopia aktualnie wybranej osoby: ");
			showPerson(clonedPerson);
			UI.printMessage("Equals() dla niezdefiniowanej" + currentPerson.equals(clonedPerson));
			UI.printMessage("Equals() dla zdefiniowanej" + clonedPersonEqualHash.equals(EqualsHash));

		}

	private void loadLista() throws PersonException, IOException {
		String filepath = "C:\\Users\\Kinga\\Desktop\\pwr\\semestr 7\\JP\\kolekcje-master\\src\\tb\\soft\\Lista.txt";
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = br.readLine();
		while(line != null){
			String[] txt = line.split(" ");
			Person person = new Person(txt[0], txt[1]);
			person.setBirthYear(txt[2]);
			person.setJob(txt[3]);
			collections.add(person);
			currentPerson = person;
			line = br.readLine();
		}}

	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	}

	private static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();

		if (person != null) {
			sb.append("Aktualna osoba: \n")
					.append("      Imię: ").append(person.getFirstName()).append("\n")
					.append("  Nazwisko: ").append(person.getLastName()).append("\n")
					.append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
					.append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append("Brak danych osoby\n");
		UI.printMessage(sb.toString());
	}

	/*
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try {
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}


	/*
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
					case 1:
						person.setFirstName(UI.enterString("Podaj imię: "));
						break;
					case 2:
						person.setLastName(UI.enterString("Podaj nazwisko: "));
						break;
					case 3:
						person.setBirthYear(UI.enterString("Podaj rok ur.: "));
						break;
					case 4:
						UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
						person.setJob(UI.enterString("Podaj stanowisko: "));
						break;
					case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

} // koniec klasy PersonConsoleApp
