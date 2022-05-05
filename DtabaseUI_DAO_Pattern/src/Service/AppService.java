package Service;

import java.util.List;

import Dao.CategoryDao;
import Dao.PersonDao;
import model.Category;
import model.Person;

public class AppService {

	public List<Category> getAllCategories() {

		return new CategoryDao().findAll();
	}

	public List<Person> getAllPeople() {
		return new PersonDao().findAll();
	}

	public List<Person> getContactsByCategoryId(int cat_id) {

		return new PersonDao().findByCategoryId(cat_id);

	}

	public int savePerson(Person p) {
		return new PersonDao().save(p);
	}

	public int deletePerson(Person p) {
		return new PersonDao().delete(p);
	}

	public int UpdatePerson(Person p) {
		return new PersonDao().update(p);
	}

	public int addCategory(Category c) {
		return new CategoryDao().save(c);

	}

	public int updateCategory(Category c) {
		return new CategoryDao().update(c);

	}

}
