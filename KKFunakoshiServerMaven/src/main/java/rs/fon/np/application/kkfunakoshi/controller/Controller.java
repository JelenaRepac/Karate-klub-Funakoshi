/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.so.city.GetAllCitiesSO;
import rs.fon.np.application.kkfunakoshi.so.competition.AddCompetitionSO;
import rs.fon.np.application.kkfunakoshi.so.competition.GetAllCompetitionSO;
import rs.fon.np.application.kkfunakoshi.so.competition.GetCompetitionsByQuerySO;
import rs.fon.np.application.kkfunakoshi.so.member.AddMemberSO;
import rs.fon.np.application.kkfunakoshi.so.member.DeleteMemberSO;
import rs.fon.np.application.kkfunakoshi.so.member.GetAllMembersSO;
import rs.fon.np.application.kkfunakoshi.so.member.GetMembersByQuerySO;
import rs.fon.np.application.kkfunakoshi.so.member.UpdateMemberSO;
import rs.fon.np.application.kkfunakoshi.so.result.AddResultSO;
import rs.fon.np.application.kkfunakoshi.so.result.GetByQueryResultSO;
import rs.fon.np.application.kkfunakoshi.so.result.UpdateResultSO;
import rs.fon.np.application.kkfunakoshi.so.team.AddTeamSO;
import rs.fon.np.application.kkfunakoshi.so.team.GetTeamsByQuerySO;
import rs.fon.np.application.kkfunakoshi.so.trainer.AddTrainerSO;
import rs.fon.np.application.kkfunakoshi.so.trainer.GetTrainerSO;
import rs.fon.np.application.kkfunakoshi.so.trainer.LogOutSO;
import rs.fon.np.application.kkfunakoshi.so.trainer.LoginSO;
import rs.fon.np.application.kkfunakoshi.so.trainer.RemoveTrainerSO;
/**
 *
 * Serverski kontroler koji upravlja svim procesima.
 * @author Jelena Repac
 */
public class Controller {
    /**
     * Kontroler
     */
    private static Controller controller;
    /**
     * Trenutno prijavljen trener
     */
    private Trainer currentUser;

  
    /**
     * Singleton metoda
     * @return instanca Controller-a
     */
    public static Controller getInstance(){
        if(controller== null)
            controller= new Controller();
        return controller;
    }
    
    /**
     * Metoda za prijavljivanje na sistem
     * @param username korisnicko ime
     * @param password lozinka 
     * @return Trener koji je prijavljen na sistem
     * @throws Exception Ukoliko dodje do greske prilikom prijavljivanja na sistem
     */
    public Trainer login(String username, String password) throws Exception{
        LoginSO loginSO= new LoginSO();
        try {
            Trainer tr= new Trainer();
            tr.setUsername(username);
            tr.setPassword(password);
            loginSO.execute(tr);
            currentUser= loginSO.getCurrentUser();
            return currentUser;
            
        } catch (Exception e) {
              throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Metoda za odjavljivanje sa sistema
     * @param trainer korisnik koji se odjavljuje 
     */
    public void logout(Trainer trainer){
       LogOutSO logoutSO= new LogOutSO();
        try {
            logoutSO.execute(trainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda koja vraca sve trenere
     * @return lista trenera
     * @throws Exception Ukoliko dodje do greske prilikom vracanja svih trenera
     */
    public List<Trainer> getTrainers() throws Exception {
       GetTrainerSO getTrainersSO= new GetTrainerSO();
       getTrainersSO.execute(null);
       return (List<Trainer>) getTrainersSO.getTrainers();
    }

    /**
     * Metoda za brisanje trenera
     * @param trainer Trener koji se brise
     * @throws Exception Ukoliko dodje do greske prilikom brisanja trenera
     */
    public void removeTrainer(Trainer trainer) throws Exception {
       RemoveTrainerSO removeTrainerSO= new RemoveTrainerSO();
       removeTrainerSO.execute(trainer);
    }
    /**
     * Metoda za dodavanje trenera
     * @param trainer trener koji se dodaje u bazu
     * @throws Exception Ukoliko dodje do greske prilikom dodavanje trenera u bazu
     */
    public void addTrainer(Trainer trainer) throws Exception {
       AddTrainerSO addTrainerSO= new AddTrainerSO();
       addTrainerSO.execute(trainer);
    }
    /**
     * Metoda koja vraca sve clanove karate kluba
     * @return lista clanova
     * @throws Exception Ukoliko dodje do greske prilikom vracanja svih clanova
     */
    public List<Member> getAllMembers() throws Exception{
        GetAllMembersSO getAllMembersSO= new GetAllMembersSO();
        try {
            getAllMembersSO.execute(null);
            return (List<Member>) getAllMembersSO.getMembers();
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }
    
//    public List<MembershipFee> getAllMembershipFees() throws Exception{
//        GetMembershipFeesSO getMembershipFeesSO= new GetMembershipFeesSO();
//        getMembershipFeesSO.execute(null);
//        return (List<MembershipFee>) getMembershipFeesSO.getMembershipFees();
//    }
    /**
     * Metoda koja dodaje clana u bazu
     * @param clan koji se dodaje u bazu
     * @throws Exception Ukoliko dodje do greske prilikom dodavanja clana u bazu
     */
    public void addMember(Member member)throws Exception{
        AddMemberSO addMemberSO= new AddMemberSO();
        addMemberSO.execute(member);
    }
    /**
     * Metoda koja vraca sve clanove koji zadovoljavaju upit.
     * @param query upit 
     * @return lista svih clanova 
     * @throws Exception Ukoliko dodje do greske prilikom vracanja svih clanova
     */
    public List<Member> getByQueryMember(String query) throws Exception {
       GetMembersByQuerySO getMembersByQuerySO= new GetMembersByQuerySO();
       getMembersByQuerySO.execute(query);
       List<Member> members= (List<Member>) getMembersByQuerySO.getMembers();
       return members;
    }
   /**
    * Metoda za brisanje clana iz baze
    * @param member clan koji se brise
    * @throws Exception Ukoliko dodje do greske prilikom brisanja clana
     */
    public void deleteMember(Member member) throws Exception {
      DeleteMemberSO deleteMemberSO= new DeleteMemberSO();
        try {
            deleteMemberSO.execute(member);
        } catch (Exception e) {
             throw new Exception(e.getMessage());
        }
    }

    /**
     * Metoda za izmenu clana
     * @param oldMember clan koji je trenutno u bazi
     * @param newMember clan koji se ubacuje
     * @throws Exception Ukoliko dodje do greske prilikom azuriranja clana
     */
    public void updateMember(Member oldMember, Member newMember) throws Exception {
        List<Member> membersForUpdate= new ArrayList<>();
        membersForUpdate.add(oldMember);
        membersForUpdate.add(newMember);
        UpdateMemberSO updateMemberSO= new UpdateMemberSO();
        
        try {
            updateMemberSO.execute(membersForUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Metoda za dodavanje tima u bazu
     * @param team tim 
     * @throws Exception Ukoliko dodje do greske prilikom dodavanja tima u bazu
     */
    public void addTeam(Team team) throws Exception {
        AddTeamSO addTeamSO= new AddTeamSO();
        addTeamSO.execute(team);
    }

    /**
     * Metoda koja vraca timove koji zadovoljavaju prosledjeni uslov.
     * @param query upit 
     * @return lista timova
     * @throws Exception Ukoliko dodje do greske prilikom vraca timova iz baze
     */
    public List<Team> getByQueryTeams(String query) throws Exception {
        GetTeamsByQuerySO getTeamsByQuerySO= new GetTeamsByQuerySO();
        getTeamsByQuerySO.execute(query);
        List<Team> teams= getTeamsByQuerySO.getTeams();
        return teams;
    }

    /**
     * Metoda za dodavanje rezultata u bazu
     * @param result rezultat koji se dodaje
     * @throws Exception Ukoliko dodje do greske prilikom dodavanja rezultata u bazu
     */
    public void addResults(Result result) throws Exception {
        AddResultSO addResultSO= new AddResultSO();
        addResultSO.execute(result);
    }
	/**
	 * Metoda za vracanje rezultata iz baze koji zadovaljavaju prosledjeni uslov.
	 * @param query upit
	 * @return lista rezultata
	 * @throws Exception Ukoliko dodje do greske prilikom vracanja rezultata iz baze
	 */
    public List<Result> getByQueryResults(String query) throws Exception {
        GetByQueryResultSO getByQueryResultSO = new GetByQueryResultSO();
        getByQueryResultSO.execute(query);
        List<Result> results=(List<Result>) getByQueryResultSO.getResults();
        return results;
    }

    /**
     * Metoda za izmenu rezultata
     * @param oldResult rezultat koji je trenutno u bazi
     * @param newResult rezultat koji se ubacuje
     * @throws Exception Ukoliko dodje do greske prilikom azuriranja rezultata
     */
    public void updateResult(Result oldResult, Result newResult) throws Exception {
        List<Result> resultsForUpdate= new ArrayList<>();
        resultsForUpdate.add(oldResult);
        resultsForUpdate.add(newResult);
        UpdateResultSO updateResultSO= new UpdateResultSO();
        
        try {
            updateResultSO.execute(resultsForUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

   /**
    * Metoda koja vraca sva takmicenja iz baze
    * @return lista svih takmicenja iz baze
    * @throws Exception Ukoliko dodje do greske prilikom vracanja takmicenja iz baze
    */
    public List<Competition> getCompetitions() throws Exception {
        GetAllCompetitionSO getAllCompetitionSO= new GetAllCompetitionSO();
        getAllCompetitionSO.execute(null);
        List<Competition> competitions= getAllCompetitionSO.getCompetitions();
        return competitions;
    }

    /**
     * Metoda koja vraca sva takmicenja iz baze koji zadovoljavaju odredjeni uslov
     * @param query upit
     * @return lista svih takmicenja
     * @throws Exception Ukoliko dodje do greske prilikom vracanja takmicenja iz baze
     */
    public List<Competition> getCompetitionsByQuery(String query) throws Exception {
        GetCompetitionsByQuerySO getCompetitionsByQuerySO= new GetCompetitionsByQuerySO();
        getCompetitionsByQuerySO.execute(query);
        List<Competition> competitions= getCompetitionsByQuerySO.getCompetitions();
        return competitions;
    }
    /**
     * Metoda za dodavanje takmicenja u bazu
     * @param competition takmicenje koje se dodaje
     * @throws Exception Ukoliko dodje do greske prilikom dodavanja takmicenja u bazu
     */
    public void addCompetition(Competition competition) throws Exception {
        AddCompetitionSO addCompetitionSO= new AddCompetitionSO();
        addCompetitionSO.execute(competition);
    }
    /**
     * Metoda koja vraca sve gradove iz baze
     * @return lista gradova 
     * @throws Exception Ukoliko dodje do greske prilikom vracanja gradova iz baze
     */
     public List<City> getAllCities() throws Exception{
        GetAllCitiesSO getAllCitiesSO= new GetAllCitiesSO();
        getAllCitiesSO.execute(null);
        List<City> cities= getAllCitiesSO.getCities();
                
        return cities;
    }

    

   
    
}
