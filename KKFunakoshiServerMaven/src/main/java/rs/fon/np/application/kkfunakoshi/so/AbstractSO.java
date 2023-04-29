package rs.fon.np.application.kkfunakoshi.so;

import rs.fon.np.application.kkfunakoshi.db.DbRepository;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
/**
 * Apstraktna klasa koju nasledjuju sve klase koje rade sa bazom.
 * @author Jelena Repac
 *
 */
public abstract class AbstractSO {

	/**
	 * Referenca na vezu sa bazom
	 */
    protected final Repository repository;

    /**
     * Konstruktor
     * 
     */
    public AbstractSO() {
        this.repository = new RepositoryDBGeneric();
    }
    /**
     * Metoda koja izvrsava operaciju
     * @param param objekat nad kojim se izvrsava operacija
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    public final void  execute(Object param) throws Exception {
        try {
            //svaka klasa sistemske operacije treba da implementira preduslove, i nacin izvrsenja operacije
            precondition(param);
            startTransaction();
            executeOperation(param);
            comitTransaction();
        } catch (Exception exception) {
            rollbackTransaction();
            throw exception;
        } finally {
            disconnect();
        }

    }

    /**
     * Metoda u okviru koje se ispituje da li su ispunjeni potrebni uslovi
     * @param param objekat nad kojim se izvrsava operacija
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    protected abstract void precondition(Object param) throws Exception;

    /**
     * Metoda koja izvrsava operaciju u bazi
     * @param param objekat nad kojim se izvrsava operacija
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Pokrece transakciju 
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    private void startTransaction() throws Exception {
         ((DbRepository) repository).connect();
    }

    /**
     * Potvrdjuje transakciju
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    protected void comitTransaction() throws Exception {
         ((DbRepository) repository).commit();

    }

    /**
     * Opoziva transakciju 
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
    protected void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }
    /**
     * Prekida konekciju 
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
     private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
