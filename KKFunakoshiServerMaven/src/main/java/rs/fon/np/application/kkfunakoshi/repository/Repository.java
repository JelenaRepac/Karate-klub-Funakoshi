package rs.fon.np.application.kkfunakoshi.repository;

import java.util.ArrayList;
import java.util.List;

import rs.fon.np.application.kkfunakoshi.domain.AbstractDO;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
/**
 * Interfejs koji sadrzi metode za CRUD operacije nad objektima.
 * 
 * @author Jelena Repac
 *
 * @param <T> tip objekta 
 */
public interface Repository<T> {
		/**
		 * Vraca sve objekta tipa T iz repozitorijuma
		 * @param t tip 
		 * @return lista objekata
		 * @throws Exception Ukoliko dodje do greske prilikom vracanja objekata
		 */
	 	List<T> getAll(T t) throws Exception;
	 	/**
	 	 * Dodaje objekat tipa T u repozitorijum
	 	 * @param t tip
	 	 * @throws Exception Ukoliko dodje do greske prilikom dodavanja objekta
	 	 */
	    void add(T t) throws Exception;
	    /**
	     * Dodavanje slozenog objekta u repozitorijum
	     * @param t tip
	     * @throws Exception Ukoliko dodje do greske prilikom dodavanja objekta
	     */
	    void addComplex(T t) throws Exception;
	    /**
	     * Azuriranje kompleksnog objekta
	     * @param oldT postojeci objekat
	     * @param newT novi objekat
	     * @throws Exception Ukoliko dodje do greske prilikoma azuiraranja objekta
	     */
	    void editComplex(T oldT, T newT) throws Exception;
	    /**
	     * Brise objekat iz repozitorijuma
	     * @param t tip
	     * @throws Exception Ukoliko dodje do greske prilikom brisanja objekta
	     */
	    void delete(T t)throws Exception;
	    /**
	     * Vraca listu objekta tipa T iz repozitorijuma koji odgovaraju odredjenom upitu.
	     * @param t tip
	     * @param query uoit
	     * @return lista objekata
	     * @throws Exception Ukoliko dodje do greske prilikom vracanja liste objekata
	     */
	    ArrayList<AbstractDO> getByQuery(T t,String query) throws Exception;
	    /**
	     * Azurira objekat 
	     * @param oldT postojeci objekat
	     * @param newT novi objekat
	     * @throws Exception Ukoliko dodje do greske prilikom azuriranja objekta
	     */
	    void edit(T oldT, T newT) throws Exception;
	    /**
	     * Vraca objekat iz repozitorijuma koji zadovoljava odgovarajuci upit
	     * @param t tip
	     * @param string upit
	     * @return objekat
	     * @throws Exception Ukoliko dodje do greske prilikom vracanja objekta
	     */
	    T get(T t, String string) throws Exception;
	    /**
	     * Dodavanje kompleksnog objekta u repozitorijum
	     * @param t tip
	     * @throws Exception Ukoliko dodje do greske prilikom dodavanje kompleksnog objekta
	     */
	    void addComplexObject (T t) throws Exception;
	    /**
	     * Dodavanje povezanih objekata
	     * @param t tip
	     */
	    public void addBoundObjects(T t);
}
