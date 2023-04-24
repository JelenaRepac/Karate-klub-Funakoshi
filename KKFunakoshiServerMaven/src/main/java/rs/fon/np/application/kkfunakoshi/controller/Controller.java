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
 * @author Jeks
 */
public class Controller {
    
    private static Controller controller;
    private Trainer currentUser;

  
    
    public static Controller getInstance(){
        if(controller== null)
            controller= new Controller();
        return controller;
    }
    
    //TRAINER 
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
    
    public void logout(Trainer trainer){
       LogOutSO logoutSO= new LogOutSO();
        try {
            logoutSO.execute(trainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Trainer> getTrainers() throws Exception {
       GetTrainerSO getTrainersSO= new GetTrainerSO();
       getTrainersSO.execute(null);
       return (List<Trainer>) getTrainersSO.getTrainers();
    }

    public void removeTrainer(Trainer trainer) throws Exception {
       RemoveTrainerSO removeTrainerSO= new RemoveTrainerSO();
       removeTrainerSO.execute(trainer);
    }
    
    public void addTrainer(Trainer trainer) throws Exception {
       AddTrainerSO addTrainerSO= new AddTrainerSO();
       addTrainerSO.execute(trainer);
    }
    
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
    
    public void addMember(Member member)throws Exception{
        AddMemberSO addMemberSO= new AddMemberSO();
        addMemberSO.execute(member);
    }
    
    public List<Member> getByQueryMember(String query) throws Exception {
       GetMembersByQuerySO getMembersByQuerySO= new GetMembersByQuerySO();
       getMembersByQuerySO.execute(query);
       List<Member> members= (List<Member>) getMembersByQuerySO.getMembers();
       return members;
    }
   
    public void deleteMember(Member member) throws Exception {
      DeleteMemberSO deleteMemberSO= new DeleteMemberSO();
        try {
            deleteMemberSO.execute(member);
        } catch (Exception e) {
             throw new Exception(e.getMessage());
        }
    }

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

    public void addTeam(Team team) throws Exception {
        AddTeamSO addTeamSO= new AddTeamSO();
        addTeamSO.execute(team);
    }

    public List<Team> getByQueryTeams(String query) throws Exception {
        GetTeamsByQuerySO getTeamsByQuerySO= new GetTeamsByQuerySO();
        getTeamsByQuerySO.execute(query);
        List<Team> teams= getTeamsByQuerySO.getTeams();
        return teams;
    }

    public void addResults(Result result) throws Exception {
        AddResultSO addResultSO= new AddResultSO();
        addResultSO.execute(result);
    }

    public List<Result> getByQueryResults(String query) throws Exception {
        GetByQueryResultSO getByQueryResultSO = new GetByQueryResultSO();
        getByQueryResultSO.execute(query);
        List<Result> results=(List<Result>) getByQueryResultSO.getResults();
        return results;
    }

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

    public List<Competition> getCompetitions() throws Exception {
        GetAllCompetitionSO getAllCompetitionSO= new GetAllCompetitionSO();
        getAllCompetitionSO.execute(null);
        List<Competition> competitions= getAllCompetitionSO.getCompetitions();
        return competitions;
    }

    public List<Competition> getCompetitionsByQuery(String query) throws Exception {
        GetCompetitionsByQuerySO getCompetitionsByQuerySO= new GetCompetitionsByQuerySO();
        getCompetitionsByQuerySO.execute(query);
        List<Competition> competitions= getCompetitionsByQuerySO.getCompetitions();
        return competitions;
    }
    
    public void addCompetition(Competition competition) throws Exception {
        AddCompetitionSO addCompetitionSO= new AddCompetitionSO();
        addCompetitionSO.execute(competition);
    }
    
     public List<City> getAllCities() throws Exception{
        GetAllCitiesSO getAllCitiesSO= new GetAllCitiesSO();
        getAllCitiesSO.execute(null);
        List<City> cities= getAllCitiesSO.getCities();
                
        return cities;
    }

    

   
    
}
