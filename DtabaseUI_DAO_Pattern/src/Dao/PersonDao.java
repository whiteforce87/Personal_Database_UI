package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Person;

public class PersonDao implements BaseDao<Person> {

	@Override
	public List<Person> findAll() {
		List<Person> people = new ArrayList<Person>();
		try (Connection connection = ConnectionManager.getConnection();) {

			String query = "select * from person";
			PreparedStatement psmt = connection.prepareStatement(query);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"),
						rs.getString("workphone"), rs.getInt("category_id"), rs.getString("address"),
						rs.getString("homephone"), rs.getString("email"), rs.getString("city"),
						rs.getDate("birthdate"));
				people.add(person);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return people;

	}

	public List<Person> findByCategoryId(int category_id) {
		List<Person> people = new ArrayList<Person>();

		try (Connection connection = ConnectionManager.getConnection()) {

			String query = "select * from person where category_id=?";

			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.setInt(1, category_id);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {

				Person p = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"),
						rs.getString("workphone"), rs.getInt("category_id"), rs.getString("address"),
						rs.getString("homephone"), rs.getString("email"), rs.getString("city"),
						rs.getDate("birthdate"));

				people.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return people;

	}

	@Override
	public Person findById(int id) {
		return null;
	}

	@Override
	public int update(Person item) {

		int result = 0;
		try (Connection conn = ConnectionManager.getConnection()) {

			String query = "update person set name=?, lastname=?,workphone=?,category_id=?, "
					+ "address=?,homephone=?,email=?,city=?,birthdate=? where id=" + item.getId();

			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, item.getName());
			psmt.setString(2, item.getLastname());
			psmt.setString(3, item.getWorkphone());
			psmt.setInt(4, item.getCategory_id());
			psmt.setString(5, item.getAddress());
			psmt.setString(6, item.getHomephone());
			psmt.setString(7, item.getEmail());
			psmt.setString(8, item.getCity());
			psmt.setDate(9, item.getBirthdate());

			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int save(Person item) {
		int result = 0;

		try (Connection conn = ConnectionManager.getConnection()) {

			String query = "insert into person(name,lastname,workphone,category_id,address,homephone,email,city,birthdate) values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, item.getName());
			psmt.setString(2, item.getLastname());
			psmt.setString(3, item.getWorkphone());
			psmt.setInt(4, item.getCategory_id());
			psmt.setString(5, item.getAddress());
			psmt.setString(6, item.getHomephone());
			psmt.setString(7, item.getEmail());
			psmt.setString(8, item.getCity());
			psmt.setDate(9, item.getBirthdate());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Person item) {
		int result = 0;

		try (Connection conn = ConnectionManager.getConnection()) {

			String query = "delete from person where id=?";

			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, item.getId());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
