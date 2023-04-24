package rs.fon.np.application.kkfunakoshi.repository;

import java.util.List;

public interface Repository<T> {

	 	List<T> getAll(T t) throws Exception;
	    void add(T t) throws Exception;
	    void addComplex(T t) throws Exception;
	    void editComplex(T oldT, T newT) throws Exception;
	    void delete(T t)throws Exception;
	    List<T> getByQuery(T t,String query) throws Exception;
	    void edit(T oldT, T newT) throws Exception;
	    T get(T t, String string) throws Exception;
	    void addComplexObject (T t) throws Exception;
	    public void addBoundObjects(T t);
}
