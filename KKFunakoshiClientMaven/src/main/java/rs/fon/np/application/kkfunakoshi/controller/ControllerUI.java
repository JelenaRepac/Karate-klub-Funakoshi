/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.controller;

import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import rs.fon.np.application.kkfunakoshi.client.thread.ServerStoppedListener;
import rs.fon.np.application.kkfunakoshi.communication.Communication;
import rs.fon.np.application.kkfunakoshi.communication.Operations;
import rs.fon.np.application.kkfunakoshi.communication.Request;
import rs.fon.np.application.kkfunakoshi.communication.Response;
import rs.fon.np.application.kkfunakoshi.communication.ResponseType;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

/**
 *
 * @author Jeks
 */
public class ControllerUI {
    
    private static ControllerUI instance;
    private Trainer currentUser;
    private ServerStoppedListener serverStoppedListener;
    
    
    public void setServerStoppedListener(ServerStoppedListener serverStoppedListener) {
        this.serverStoppedListener = serverStoppedListener;
    }

    private ControllerUI() {

    }

    public void finish() {
        if (serverStoppedListener != null) {
            serverStoppedListener.serverStopped();
        }
    }

    public static ControllerUI getInstance() {
        if (instance == null) {
            instance = new ControllerUI();
        }
        return instance;
    }

    public Trainer login(String user, String pass) throws Exception{
        Trainer u= new Trainer();
        u.setUsername(user);
        u.setPassword(pass);
        
        Request req = new Request(Operations.LOGIN,u);
        Response response = Communication.getInstance().login(req);
        
        if (response == null || !response.getResponseType().equals(ResponseType.SUCCESS)) {
            throw response.getException();
        }
        Trainer t= (Trainer) response.getResult();
        System.out.println(t.toString());
        return t;

    }
    
    public Trainer getCurrentUser(){
        return this.currentUser;
    }

    public List<Member> getMembers() throws Exception {
        Request request= new Request(Operations.GET_ALL_MEMBERS, null);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Member>) response.getResult();
    }
    public List<Member> getByQuery(String query) throws Exception {
        Request request= new Request(Operations.GET_MEMBERS_BY_QUERY, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Member>) response.getResult();
    }

    public List<MembershipFee> getMembershipFees() throws Exception {
       Request request= new Request(Operations.GET_ALL_MEMBERSHIPFEES, null);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
       return (List<MembershipFee>) response.getResult();
    }
    
    public List<City> getCities() throws Exception{
        Request request= new Request(Operations.GET_CITIES, null);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<City>) response.getResult();
    }
    
    public void addMember(Member member) throws Exception{
        Request request= new Request(Operations.ADD_MEMBER, member);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
    }

    public void deleteMember(Member member) throws Exception {
       Request request= new Request(Operations.DELETE_MEMBER, member);
       Response response= Communication.getInstance().sendRequest(request);
       if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }

    public void updateMember(Member member, Member newMember) throws Exception {
       List<Member> membersForUpdating= new ArrayList<>();
       membersForUpdating.add(member);
       membersForUpdating.add(newMember);
       
       Request request= new Request(Operations.UPDATE_MEMBER, membersForUpdating);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
    }

    public void addCompetition(Competition competition) throws Exception {
       Request request= new Request(Operations.ADD_COMPETITION, competition);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
    }
    
    public List<Competition> getCompetitions(String query) throws Exception {
        Request request= new Request(Operations.GET_ALL_COMPETITIONS, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Competition>) response.getResult();
    }
    public List<Competition> getByQueryCompetition(String query) throws Exception {
        Request request= new Request(Operations.GET_COMPETITIONS_BY_QUERY, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Competition>) response.getResult();
    }
    public void addTeam(Team team) throws Exception {
        Request request= new Request(Operations.ADD_TEAM, team);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
    }

    public List<Team> getByQueryTeams(String query) throws Exception {
        Request request= new Request(Operations.GET_ALL_TEAMS, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Team>) response.getResult();
    }

    public void addResult(Result result) throws Exception {
        Request request= new Request(Operations.ADD_RESULT, result);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
    }

    public List<Result> getByQueryResults(String string) throws Exception {
        Request request= new Request(Operations.GET_RESULTS_BY_QUERY, string);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
        return (List<Result>) response.getResult();
    }

    public void updateMemberMedals(Member member, Member newMember) throws Exception {
       List<Member> membersForUpdating= new ArrayList<>();
       membersForUpdating.add(member);
       membersForUpdating.add(newMember);
       
       Request request= new Request(Operations.UPDATE_MEMBER_MEDALS, membersForUpdating);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
    }

    public void updateResult(Result r, Result newResult) throws Exception {
       List<Result> resultsForUpdating= new ArrayList<>();
       resultsForUpdating.add(r);
       resultsForUpdating.add(newResult);
       
       Request request= new Request(Operations.UPDATE_RESULT, resultsForUpdating);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
    }

    public void updateResultTeam(Result r, Result newResult) throws Exception {
         List<Result> resultsForUpdating= new ArrayList<>();
        resultsForUpdating.add(r);
        resultsForUpdating.add(newResult);

        Request request= new Request(Operations.UPDATE_RESULT_TEAM, resultsForUpdating);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
    }

  
}
