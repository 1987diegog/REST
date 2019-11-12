package uy.com.demente.ideas.services;

import java.util.List;

import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.utils.DataCollection;

public class PersonServices {

	public PersonDTO getPerson(Long valueOf) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPerson(PersonDTO personDTO) {
		// TODO Auto-generated method stub

	}

	public List<PersonDTO> getPersons() {
		DataCollection allPersons = new DataCollection(100);
		return allPersons.getList();
	}

	public void modifyPerson(PersonDTO personDTO) {
		// TODO Auto-generated method stub

	}

	public void deletePerson(Long valueOf) {
		// TODO Auto-generated method stub

	}

}
