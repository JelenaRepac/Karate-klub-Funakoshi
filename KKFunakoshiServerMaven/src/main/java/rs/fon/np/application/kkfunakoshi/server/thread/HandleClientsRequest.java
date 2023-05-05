/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.server.thread;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import rs.fon.np.application.kkfunakoshi.communication.Operations;
import rs.fon.np.application.kkfunakoshi.communication.Receiver;
import rs.fon.np.application.kkfunakoshi.communication.Request;
import rs.fon.np.application.kkfunakoshi.communication.Response;
import rs.fon.np.application.kkfunakoshi.communication.ResponseType;
import rs.fon.np.application.kkfunakoshi.communication.Sender;
import rs.fon.np.application.kkfunakoshi.controller.Controller;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

/**
 * Klasa koja obradjuje zahteve korisnika.
 * @author Jelena Repac
 */
public class HandleClientsRequest extends Thread {
    
	/**
	 * Soket
	 */
    private Socket socket;
    /**
     * Serverska nit
     */
    private ServerThread server;
    /**
     * Korisnik
     */
    private Trainer user;
    
    /**
     * Konstruktor
     * @param server serverska nit
     * @param socket soket
     */
    public HandleClientsRequest(ServerThread server,Socket socket) {
        this.socket = socket;
        this.server=server;
    }

    /**
     * Vraca soket
     * @return soket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Postavlja soket
     * @param socket soket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Vraca serversku nit 
     * @return serverska nit
     */
    public ServerThread getServer() {
        return server;
    }
    /**
     * Postavlja serversku nit
     * @param server serverska nit
     */
    public void setServer(ServerThread server) {
        this.server = server;
    }
    /**
     * Vraca korisnika
     * @return korisnik
     */
    public Trainer getUser() {
        return user;
    }

    /**
     * Postavlja korisnika
     * @param user korisnik
     */
    public void setUser(Trainer user) {
        this.user = user;
    }

    /**
     * Metoda koja se pokrece nakon kreiranja objekta HandleClientRequest
     */
    @Override
    public void run() {
          try {
            handleClientsRequest(socket);
        } catch (SocketException se) {
            System.out.println("Client aborted the program.");
   
        } catch (Exception ex) {
            stopCommunication();
            System.out.println("Client aborted the program.");
        }
    }
    /**
     * Prekida komunikaciju
     */
    void stopCommunication(){
        try{
            Response response= new Response();
            response.setOperation(Operations.SERVER_STOPPED);
            response.setResult("Finito");
            //new Sender(socket).send(response);
            sleep(1000);
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error stopping communication - class Handle..");
        } catch (Exception ex) {
            System.out.println("Error stopping communication - class Handle..");
        }
    }
    /**
     * Obradjuje zahtev poslat od strane korisnika
     * @param socket soket
     * @return odgovor
     * @throws Exception Ukoliko dodje do greske prilikom obrade zahteva
     */
     public Response handleClientsRequest(Socket socket) throws Exception {
         Response response= null;
         while(true){
                    try{
                        Request request= (Request) new Receiver(socket).receive();
                            switch (request.getOperation()){
                                case Operations.LOGIN:
                                    Trainer u= (Trainer) request.getArgument();
                                    response=login(u);
                                    break;
                                case Operations.GET_ALL_MEMBERS:
                                    response= getMembers();
                                    break;
                                case Operations.ADD_MEMBER:
                                    Member member= (Member) request.getArgument();
                                    response= addMember(member);
                                    break;
                                case Operations.LOGOUT:
                                    logout(request);
                                    server.logout(this);
                                    break;
                                case Operations.GET_CITIES:
                                    response=getCities();
                                    break;
                                case Operations.GET_MEMBERS_BY_QUERY:
                                    response=getMembersByQuery(request);
                                    break;
                                case Operations.DELETE_MEMBER:
                                    response= deleteMember(request);
                                    break;
                                case Operations.UPDATE_MEMBER:
                                    response=updateMember(request);
                                    break;
                                case Operations.ADD_COMPETITION:
                                    Competition competition= (Competition) request.getArgument();
                                    response= addCompetition(competition);
                                    break;
                                case Operations.GET_ALL_COMPETITIONS:
                                    response= getCompetitions(request);
                                    break;
                                case Operations.GET_COMPETITIONS_BY_QUERY:
                                    response=getCompetitionsByQuery(request);
                                    break;
                                case Operations.ADD_TEAM:
                                    Team team= (Team) request.getArgument();
                                    response= addTeam(team);
                                    break;
                                case Operations.GET_ALL_TEAMS:
                                    response= getTeams(request);
                                    break;
                                case Operations.ADD_RESULT:
                                    Result result= (Result) request.getArgument();
                                    response=addResults(result);
                                    break;
                                case Operations.GET_RESULTS_BY_QUERY:
                                    response= getResultsByQuery(request);
                                    break;
                                case Operations.UPDATE_RESULT:
                                    response=updateResult(request);
                                    break;
                                default: ;
                            }
                               new Sender(socket).send(response);
                       
                    }catch(ClassNotFoundException ex){
                      System.out.println("Klijent je prekinuo program.");
                      server.setUserLoggedIn(user, false);
                    }
                 
            }
         
     }

     /**
      * Obradjuje zahtev korisnika za prijavljivanje na sistem
      * @param u korisnik
      * @return odgovor od strane servera
      */
    private Response login(Trainer u) {
        Response response= new Response();
        try {
            Trainer dbUser= Controller.getInstance().login(u.getUsername(),u.getPassword());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(dbUser);
            response.setOperation(Operations.LOGIN);
           
            server.setUserLoggedIn(dbUser,true); 
            this.user=dbUser;
            System.out.println(user.toString());
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception(ex.getMessage()));
            
            response.setOperation(Operations.LOGIN);
           
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za odjavljivanje sa sistema
     * @param request korisnikov zahtev
     */
     private void logout(Request request) {
        Trainer user= (Trainer) request.getArgument();
        Controller.getInstance().logout(user);
        server.setUserLoggedIn(user, false);
    }
    
     /**
      * Obradjuje zahtev korisnika za vracanje liste clanova
      * @return odgovor od strane servera
      */
    private Response getMembers() {
        Response response= new Response();
        try{
            List<Member> members= Controller.getInstance().getAllMembers();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(members);
            
        }catch(Exception e){
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;    
    }

    /**
     * Obradjuje zahtev korisnika za vracanje liste gradova
     * @return odgovor od strane servera
     */
    private Response getCities() {
       Response response= new Response();
        try {
            List<City> cities= Controller.getInstance().getAllCities();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(cities);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za dodavanje clana
     * @param member clan 
     * @return odgovor od strane servera
     */
    private Response addMember(Member member) {
        Response response= new Response();
        try {
            Controller.getInstance().addMember(member);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za vracanje liste clanova koji zadovoljavaju odredjeni uslov
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response getMembersByQuery(Request request) {
      String query= (String) request.getArgument();
      Response response= new Response();
        try {
            List<Member> members= Controller.getInstance().getByQueryMember(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(members);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    /**
     * Obradjuje zahtev korisnika za brisanje korisnika
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response deleteMember(Request request) {
        Response response= new Response();
        try {
            Controller.getInstance().deleteMember((Member) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za azuriranje clana
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response updateMember(Request request) {
        Member oldMember= ((List<Member>) request.getArgument()).get(0);
        Member newMember= ((List<Member>) request.getArgument()).get(1);
        
        Response response= new Response();
        
        try {
            Controller.getInstance().updateMember(oldMember,newMember);
            response.setResponseType(ResponseType.SUCCESS);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za dodavanje takmicenja
     * @param competition takmicenje 
     * @return odgovor od strane servera
     */
    private Response addCompetition(Competition competition) {
       Response response= new Response();
        try {
            Controller.getInstance().addCompetition(competition);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za vracanje liste takmicenja
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response getCompetitions(Request request) {
        
        Response response= new Response();
        try {
            List<Competition> competitions= Controller.getInstance().getCompetitions();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(competitions);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za vracanje liste takmicenja koji zadovoljavaju odredjeni uslov
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response getCompetitionsByQuery(Request request) {
      String query= (String) request.getArgument();
      Response response= new Response();
        try {
            List<Competition> competitions= Controller.getInstance().getCompetitionsByQuery(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(competitions);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za dodavanje tima
     * @param team tim
     * @return odgovor od strane servera
     */
    private Response addTeam(Team team) {
        Response response= new Response();
        try {
            Controller.getInstance().addTeam(team);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za vracanje liste timova
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response getTeams(Request request) {
        String query= (String) request.getArgument();
        Response response= new Response();
        try {
            List<Team> teams= Controller.getInstance().getByQueryTeams(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(teams);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za dodavanje rezultata
     * @param result rezultat
     * @return odgovor od strane servera
     */
    private Response addResults(Result result) {
        Response response= new Response();
        try {
            Controller.getInstance().addResults(result);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za vracanje liste rezultata koji zadovoljavaju odredjeni uslov
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response getResultsByQuery(Request request) {
        String query= (String) request.getArgument();
        Response response= new Response();
        try {
            List<Result> results= Controller.getInstance().getByQueryResults(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(results);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
    /**
     * Obradjuje zahtev korisnika za azuriranje rezultata
     * @param request korisnikov zahtev
     * @return odgovor od strane servera
     */
    private Response updateResult(Request request) {
        Result oldResult= ((List<Result>) request.getArgument()).get(0);
        Result newResult= ((List<Result>) request.getArgument()).get(1);
        
        Response response= new Response();
        
        try {
            Controller.getInstance().updateResult(oldResult,newResult);
            response.setResponseType(ResponseType.SUCCESS);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

   
  
    

   
    
 
    
    
}
