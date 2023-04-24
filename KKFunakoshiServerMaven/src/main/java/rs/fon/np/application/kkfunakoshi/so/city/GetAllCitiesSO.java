/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.city;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class GetAllCitiesSO extends AbstractSO{

    private List<City> cities;
    
    @Override
    protected void precondition(Object param) throws Exception {
        //no precodintion to check
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        cities=repository.getAll(new City());
    }

    public List<City> getCities() {
        return cities;
    }
 
    
   
}
