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
 * Kontroler namenjen za upravljanje interakcije korisnika sa aplikacijom i komunikacijom sa serverom.
 * 
 * @author Jelena Repac
 */
public class ControllerUI {
    
	/**
	 * Instanca koja sadrzi singleton instancu clase ControllerUI
	 */
    private static ControllerUI instance;
    /**
     * Trenutno prijavljen korsinik/trener na sistem
     */
    private Trainer currentUser;
    /**
     * Varijabla koja obavestava da li je server zaustavljen
     */
    private ServerStoppedListener serverStoppedListener;
    
    /**
     * Konstruktor
     * @param serverStoppedListener objekat klase ServerStoppedListener
     */
    public void setServerStoppedListener(ServerStoppedListener serverStoppedListener) {
        this.serverStoppedListener = serverStoppedListener;
    }

    private ControllerUI() {

    }
    /**
     * Poziva metodu za zaustavljanje servera ukoliko objekat serverStoppedListener nije null
     */
    public void finish() {
        if (serverStoppedListener != null) {
            serverStoppedListener.serverStopped();
        }
    }
    /**
     * Kreira singleton instancu objekta ControllerUI, ukoliko prethodno nije kreirana.
     * @return singleton instancu objekta ControllerUI
     */
    public static ControllerUI getInstance() {
        if (instance == null) {
            instance = new ControllerUI();
        }
        return instance;
    }
    /**
     * Vrsi se prijavljivanje korisnika na sistem, slanjem korisnickog imena i lozinke serveru.
     * @param user korisnicko ime 
     * @param pass lozinka
     * @return Trenera/korisnika koji je ulogovan u sistem
     * @throws Exception ukoliko je odgovor dobijen od servera null ili ukoliko tip odgovora nije jednak SUCCESS baca se opsti izuzetak
     */
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
    /**
     * Vraca trenutno prijavljenog korisnika na sistem
     * @return trenutno prijavljeni korisnik
     */
    public Trainer getCurrentUser(){
        return this.currentUser;
    }
    /**
     * Vraca listu svih clanova iz baze
     * @return lista svih clanova karate kluba 
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<Member> getMembers() throws Exception {
        Request request= new Request(Operations.GET_ALL_MEMBERS, null);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Member>) response.getResult();
    }
    /**
     * Vraca listu svih clanova iz baze koji odgovaraju prosledjenom upitu
     * @param query upit 
     * @return lista svih clanova karate kluba 
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public ArrayList<Member> getByQuery(String query) throws Exception {
        Request request= new Request(Operations.GET_MEMBERS_BY_QUERY, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (ArrayList<Member>) response.getResult();
    }

    /**
     * Vraca listu svih clanarina iz baze
     * @return lista svih clanarina karate kluba 
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<MembershipFee> getMembershipFees() throws Exception {
       Request request= new Request(Operations.GET_ALL_MEMBERSHIPFEES, null);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
       return (List<MembershipFee>) response.getResult();
    }
    /**
     * Vraca listu svih gradova iz baze
     * @return lista svih gradova 
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<City> getCities() throws Exception{
        Request request= new Request(Operations.GET_CITIES, null);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<City>) response.getResult();
    }
    /**
     * Dodaje clana u bazu 
     * @param member clan koji se dodaje u bazu
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public void addMember(Member member) throws Exception{
        Request request= new Request(Operations.ADD_MEMBER, member);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
    }
    /**
     * Brise clana iz baze
     * @param member clan koji se brise iz baze
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public void deleteMember(Member member) throws Exception {
       Request request= new Request(Operations.DELETE_MEMBER, member);
       Response response= Communication.getInstance().sendRequest(request);
       if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }
    /**
     * Metoda kojom se menja vec postojeci clan u bazi
     * @param member clan koji postoji u bazi
     * @param newMember clan sa izmenjenim atributima
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
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

    /**
     * Dodaje takmicenje u bazu 
     * @param competition takmicenje koje se dodaje u bazu
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public void addCompetition(Competition competition) throws Exception {
       Request request= new Request(Operations.ADD_COMPETITION, competition);
       Response response= Communication.getInstance().sendRequest(request);
       if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
       }
    }
    /**
     * Vraca sve takmicenja iz baze
     * @param query upit
     * @return lista takmicenja iz baze
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<Competition> getCompetitions(String query) throws Exception {
        Request request= new Request(Operations.GET_ALL_COMPETITIONS, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Competition>) response.getResult();
    }
    /**
     * Vraca sve takmicenja iz baze koji zadovoljavaju poslati upit
     * @param query upit
     * @return lista takmicenja iz baze
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<Competition> getByQueryCompetition(String query) throws Exception {
        Request request= new Request(Operations.GET_COMPETITIONS_BY_QUERY, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Competition>) response.getResult();
    }
    /**
     * Dodaje tim u bazu
     * @param team tim koji se dodaje
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public void addTeam(Team team) throws Exception {
        Request request= new Request(Operations.ADD_TEAM, team);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
    }
    /**
     * Vraca sve timove iz baze
     * @param query upit
     * @return lista timova iz baze
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<Team> getByQueryTeams(String query) throws Exception {
        Request request= new Request(Operations.GET_ALL_TEAMS, query);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }
        return (List<Team>) response.getResult();
    }
    /**
     * Dodaje rezultat u bazu
     * @param result rezultat
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public void addResult(Result result) throws Exception {
        Request request= new Request(Operations.ADD_RESULT, result);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
    }
    /**
     * Vraca sve rezultate iz baze koji zadovoljavaju poslati upit
     * @param string upit
     * @return lista rezultat iz baze
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public List<Result> getByQueryResults(String string) throws Exception {
        Request request= new Request(Operations.GET_RESULTS_BY_QUERY, string);
        Response response= Communication.getInstance().sendRequest(request);
        if(response.getResponseType().equals(ResponseType.ERROR)){
           throw response.getException();
        }
        return (List<Result>) response.getResult();
    }
    /**
     * Metoda kojom se menja broj medalja odredjenog clana u bazi
     * @param member clan koji postoji u bazi
     * @param newMember clan sa izmenjenim brojem medalja
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
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
    /**
     * Metoda kojom se menja ostvareni rezultat u bazi
     * @param r rezultat koji postoji u bazi
     * @param newResult rezultat sa izmenjenim atributima
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
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
    /**
     * Metoda kojom se menja ostvareni rezultat tima u bazi
     * @param r rezultat koji postoji u bazi
     * @param newResult rezultat sa izmenjenim atributima
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
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
