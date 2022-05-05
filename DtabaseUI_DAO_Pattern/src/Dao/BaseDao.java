package Dao;

import java.util.List;

public interface BaseDao<T> {
	
	public List<T> findAll();
	public T findById(int id);
	
	
	public int update (T item);
	public int save (T item);
	public int delete (T item);

	

}
