package rs.fon.np.application.kkfunakoshi.so;

import rs.fon.np.application.kkfunakoshi.db.DbRepository;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

public abstract class AbstractSO {

	  
    protected final Repository repository;

    public AbstractSO() {
        this.repository = new RepositoryDBGeneric();
    }
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

    protected abstract void precondition(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception {
         ((DbRepository) repository).connect();
    }

    protected void comitTransaction() throws Exception {
         ((DbRepository) repository).commit();

    }

    protected void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }
    
     private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
