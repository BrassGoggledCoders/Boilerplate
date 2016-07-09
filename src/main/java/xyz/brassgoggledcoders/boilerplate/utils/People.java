package xyz.brassgoggledcoders.boilerplate.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People {
	private static People INSTANCE;
	private Map<String, Person> everyone;
	private Map<PersonType, List<Person>> peopleByTypes;

	static People getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new People();
		}
		return INSTANCE;
	}

	private People() {
		everyone = new HashMap<>();
		peopleByTypes = new HashMap<>();
		for(PersonType personType : PersonType.values()) {
			peopleByTypes.put(personType, new ArrayList<>());
		}
		addPeople();
	}

	private void addPeople() {
		addPerson(new Person("SkySom", "27672103-b8c7-400d-8817-49de433336dd", PersonType.BGC));
		addPerson(new Person("warlordjones", "c2e83bd4-e8df-40d6-a639-58ba8b05401e", PersonType.BGC));

		addPerson(new Person("decebaldecebal", "5eed1615-0ec9-4f4b-a4c9-58454ad5b04f", PersonType.CONTRIBUTOR));

		addPerson(new Person("BluSunrize", "f34afdfb-996b-4020-b8a2-b740e2937b29", PersonType.FRIEND));
		addPerson(new Person("Glazzmaker", "3df99427-2e78-4f4d-828c-95f09fe60144", PersonType.FRIEND));
		addPerson(new Person("UndertheBridge", "434bf509-ce83-4787-8176-32d202f8c6f6", PersonType.FRIEND));
	}

	public static void addPerson(Person person) {
		getInstance().everyone.put(person.name, person);
		person.personTypeList.stream().forEach(type -> getInstance().peopleByTypes.get(type).add(person));
	}

	public static Map<String, Person> getEveryone() {
		return getInstance().everyone;
	}

	public static List<Person> getPeopleByPersonType(PersonType personType) {
		return getInstance().peopleByTypes.get(personType);
	}

	public static class Person {
		public String name;
		public String UUID;
		public List<PersonType> personTypeList;

		public Person(String name, String UUID, PersonType... personType) {
			this.name = name;
			this.UUID = UUID;
			this.personTypeList = new ArrayList<>(personType.length);
			Collections.addAll(this.personTypeList, personType);
		}
	}

	public enum PersonType {
		BGC, CONTRIBUTOR, FRIEND
	}
}
