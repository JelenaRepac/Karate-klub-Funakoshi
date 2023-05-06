/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa predstavlja clana karate kluba. Sadrzi licne informacije o clanu kao sto su
 * njegov id, ime, prezime, pol, datum rodjenja, imena roditelja, grad iz kojeg su,
 * pojas koji poseduju, broj osvojenih medalja, disciplinu i kategoriju u kojoj se takmice,
 * adresu stanovanja, spisak svih izmirenih clanarina, ukupan dug, datum uclanjenja,
 * kao i tacan broj zlatnih, srebrnih i bronzanih osvojenih medalja.
 * 
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * 
 * @author Jelena Repac
 */
public class Member extends AbstractDO implements Serializable{
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Id clana.
     */
    private Long memberID;
    /**
     * Ime clana
     */
    private String name;
    /**
     * Prezime clana
     */
    private String lastname;
    /**
     * Pol clana
     */
    private Gender gender;
    /**
     * Datum rodjenja clana
     */
    private Date birthday;
    /**
     * Ime majke
     */
    private String mothersName;
    /**
     * Ime oca
     */
    private String fathersName;
    /**
     * Grad iz kojeg je clan
     */
    private City city;
    /**
     * Pojas koji clan poseduje
     */
    private Belt belt;
    /**
     * Broj osvojenih medalja
     */
    private Long medals;
    /**
     * Disciplinu u kojoj se clan takmici
     */
    private Discipline discipline;
    /**
     * Kategorija u kojoj se clan takmici
     */
    private Category category;
    /**
     * Adresa stanovanja clana
     */
    private String adress;
    /**
     * Broj osvojenih zlatnih medalja
     */
    private Long goldMedals;
    /**
     * Broj osvojenih srebrnih medalja
     */
    private Long silverMedals;
    /**
     * Broj osvojenih bronzanih medalja
     */
    private Long bronzeMedals;
    /**
     * Lista clanarina
     */
    List<MembershipFee> membershipFee;
    /**
     * Datum uclanjenja clana
     */
    private Date dateOfMembership;
    /**
     * Ukupan dug clana
     */
    private Double totalDebt;
    /**
     * Bezparametarski konstruktor
     */
    public Member() {
        this.membershipFee= new ArrayList<>();
    }
    /**
     * Konstruktor
     * @param memberID id clana
     * @param name ime
     * @param lastname prezime
     * @param gender pol
     * @param birthday datum rodjenja
     * @param mothersName ime majke
     * @param fathersName ime oca
     * @param city grad u kojem zivi
     * @param belt pojas
     * @param medals broj osvojenih medalja
     * @param discipline disciplina u kojoj se clan takmici
     * @param category kategorija u kojoj se clan takmici
     * @param adress adresa stanovanja
     * @param goldMedals broj osvojenih zlatnih medalja
     * @param silverMedals broj osvojenih srebrnih medalja
     * @param bronzeMedals broj osvojenih bronzanih medalja
     * @param membershipFee clanarine
     * @param dateOfMembership datum uclanjenja
     * @param totalDebt ukupan dug
     */
    public Member(Long memberID, String name, String lastname, Gender gender, Date birthday, String mothersName,
            String fathersName, City city, Belt belt, Long medals, Discipline discipline, Category category,
            String adress, Long goldMedals, Long silverMedals, Long bronzeMedals, ArrayList<MembershipFee> membershipFee, Date dateOfMembership, Double totalDebt) {
        this.memberID = memberID;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.city = city;
        this.belt = belt;
        this.medals = medals;
        this.discipline = discipline;
        this.category = category;
        this.adress = adress;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
        this.membershipFee = membershipFee;
        this.dateOfMembership= dateOfMembership;
        this.totalDebt=totalDebt;
    }
    /**
     * Konstruktor
     * @param name ime
     * @param lastname prezime
     * @param gender pol
     * @param birthday datum rodjenja
     * @param mothersName ime majke
     * @param fathersName ime oca
     * @param city grad u kojem zivi
     * @param belt pojas
     * @param medals broj osvojenih medalja
     * @param discipline disciplina u kojoj se clan takmici
     * @param category kategorija u kojoj se clan takmici
     * @param adress adresa stanovanja
     * @param goldMedals broj osvojenih zlatnih medalja
     * @param silverMedals broj osvojenih srebrnih medalja
     * @param bronzeMedals broj osvojenih bronzanih medalja
     * @param membershipFee clanarine
     * @param dateOfMembership datum uclanjenja
     * @param totalDebt ukupan dug
     */
     public Member(String name, String lastname, Gender gender, Date birthday, String mothersName, 
             String fathersName, City city, Belt belt, Long medals, Discipline discipline, Category category, 
             String adress, Long goldMedals, Long silverMedals, Long bronzeMedals, List<MembershipFee> membershipFee, Date dateOfMembership, Double totalDebt) {
        
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.city = city;
        this.belt = belt;
        this.medals = medals;
        this.discipline = discipline;
        this.category = category;
        this.adress = adress;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
        this.membershipFee = membershipFee;
        this.dateOfMembership=dateOfMembership;
        this.totalDebt=totalDebt;
    }

    /**
     * Vraca vrednost id-a clana.
     * @return id clana
     */
    public Long getMemberID() {
        return memberID;
    }
    /**
     * 
     * Postavlja vrednost id-a
     * @param memberID id clana
     * @throws IllegalArgumentException Ukoliko je id clana negativan broj
     */
    public void setMemberID(Long memberID) {
    	if(memberID < 0)
            throw new IllegalArgumentException("Id clana ne sme biti negativan broj");
        this.memberID = memberID;
    }
    /**
     * Vraca ime clana
     * @return ime clana
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja ime clana
     * @param name ime clana
     * @throws NullPointerException Ukoliko je prosledjeno ime null
     * @throws IllegalArgumentException Ukoliko ime clana ima manje od 3 karaktera
     */
    public void setName(String name) {
    	if(name == null )
    		throw new NullPointerException("Ime clana ne sme biti null");
    	if(name.length() < 3 || name.isEmpty())
            throw new IllegalArgumentException("Ime clana mora imati minimum 3 karaktera niti biti prazan string");
        this.name = name;
    }
    /**
     * Vraca prezime clana
     * @return prezime clana
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * Postavlja prezime clana
     * @param lastname prezime clana
     * @throws NullPointerException Ukoliko je prosledjeno prezime null
     * @throws IllegalArgumentException Ukoliko prezime clana ima manje od 3 karaktera
     */
    public void setLastname(String lastname) {
    	if(lastname == null )
    		throw new NullPointerException("Prezime clana ne sme biti null");
    	if(lastname.length() < 3 || lastname.isEmpty())
            throw new IllegalArgumentException("Prezime clana mora imati minimum 3 karaktera niti biti prazan string");
        this.lastname = lastname;
    }
    /**
     * Vraca datum rodjenja clana
     * @return datum rodjenja clana
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * Postavlja datum rodjenja clana
     * @param birthday datum rodjenja
     * @throws NullPointerException Ukoliko je datum null
     * @throws IllegalArgumentException Ukoliko je prosledjeni datum rodjenja nakon sadasnjeg
     */
    public void setBirthday(Date birthday) {
    	if(birthday==null)
    		throw new NullPointerException("Datum rodjenja ne sme biti null");
    	Date currentDate = new Date();
        if (birthday.after(currentDate)) 
            throw new IllegalArgumentException("Datum rodjenja ne sme biti nakon danasnjeg!");
        this.birthday = birthday;
    }
    /**
     * Vraca ime majke
     * @return ime majke
     */
    public String getMothersName() {
        return mothersName;
    }
    /**
     * Postavlja ime majke
     * @param mothersName ime majke
     * @throws NullPointerException Ukoliko je prosledjeno ime majke null
     * @throws IllegalArgumentException Ukoliko ime majke clana ima manje od 3 karaktera
     */
    public void setMothersName(String mothersName) {
    	if(mothersName == null )
    		throw new NullPointerException("Ime majke clana ne sme biti null");
    	if(mothersName.length() < 3 || mothersName.isEmpty())
            throw new IllegalArgumentException("Ime majke clana mora imati minimum 3 karaktera niti biti prazan string");
        this.mothersName = mothersName;
    }
    /**
     * Vraca ime oca
     * @return ime oca
     */
    public String getFathersName() {
        return fathersName;
    }
    /**
     * Postavlja ime oca
     * @param fathersName ime oca
     * @throws NullPointerException Ukoliko je prosledjeno ime oca null
     * @throws IllegalArgumentException Ukoliko ime oca clana ima manje od 3 karaktera
     */
    public void setFathersName(String fathersName) {
    	if(fathersName == null )
    		throw new NullPointerException("Ime oca clana ne sme biti null");
    	if(fathersName.length() < 3 || fathersName.isEmpty())
            throw new IllegalArgumentException("Ime oca clana mora imati minimum 3 karaktera niti biti prazan string");
        this.fathersName = fathersName;
    }
    /**
     * Vraca grad iz kojeg je clan
     * @return	grad stanovanja
     */
    public City getCity() {
        return city;
    }
    /**
     * Postavlja grad
     * @param city grad iz kojeg je clan
     * @throws IllegalArgumentException Stvara se izuzetak ukoliko prosledjeni grad nije potpuno inicijalizovan
     
     */
    public void setCity(City city) {
    	if(city == null || city.getId() ==0 || city.getName().equals("") || city.getPtt()==0 )
    		throw new IllegalArgumentException("Grad mora biti u potpunosti inicijalizovan");
        this.city = city;
    }
    /**
     * Vraca pojas clana
     * @return pojas clana
     */
    public Belt getBelt() {
        return belt;
    }
    /**
     * Postavlja pojas clana
     * @param belt pojas
     * @throws NullPointerException Ukoliko je prosledjeni pojas null
     */
    public void setBelt(Belt belt) {
    	if(belt==null)
    		throw new NullPointerException("Pojas ne sme biti null");
        this.belt = belt;
    }
    /**
     * Vraca broj osvojenih medalja
     * @return ukupan broj medalja
     */
    public Long getMedals() {
        return medals;
    }
    /**
     * Postavlja broj osvojenih medalja
     * @param medals broj osvojenih medalja
     * @throws IllegalArgumentException Ukoliko je prosledjen broj medalja nula ili negativan broj
     */
    public void setMedals(Long medals) {
    	if(medals<0 || medals==0)
    		throw new IllegalArgumentException("Ukoliko je prosledjeni broj medalja 0 ili manji od nule");
        this.medals = medals;
    }
    /**
     * Vraca broj osvojenih zlatnih medalja
     * @return broj osvojenih zlatnih medalja
     */
    public Long getGoldMedals() {
        return goldMedals;
    }
    /**
     * Postavlja broj zlatnih medalja
     * @param goldMedals zlatne medalje
     * @throws IllegalArgumentException Ukoliko je prosledjen broj medalja nula ili negativan broj
     */
    public void setGoldMedals(Long goldMedals) {
    	if(goldMedals<0 || goldMedals==0)
    		throw new IllegalArgumentException("Ukoliko je prosledjeni broj medalja 0 ili manji od nule");
        this.goldMedals = goldMedals;
    }
    /**
     * Vraca broj osvojenih srebrnih medalja
     * @return broj osvojenih srebrnih medalja
     */
    public Long getSilverMedals() {
        return silverMedals;
    }
    /**
     * Postavlja broj srebrnih medalja
     * @param silverMedals srebrne medalje
     * @throws IllegalArgumentException Ukoliko je prosledjen broj medalja nula ili negativan broj
     */
    public void setSilverMedals(Long silverMedals) {
    	if(silverMedals<0 || silverMedals==0)
    		throw new IllegalArgumentException("Ukoliko je prosledjeni broj medalja 0 ili manji od nule");
        this.silverMedals = silverMedals;
    }
    /**
     * Vraca broj osvojenih bronzanih medalja
     * @return broj osvojenih bronzanih medalja
     */
    public Long getBronzeMedals() {
        return bronzeMedals;
    }
    /**
     * Postavlja broj bronzanih medalja
     * @param bronzeMedals bronzane medalje
     * @throws IllegalArgumentException Ukoliko je prosledjen broj medalja nula ili negativan broj
     */
    public void setBronzeMedals(Long bronzeMedals) {
    	if(bronzeMedals<0 || bronzeMedals==0)
    		throw new IllegalArgumentException("Ukoliko je prosledjeni broj medalja 0 ili manji od nule");
        this.bronzeMedals = bronzeMedals;
    }
    /**
     * Vraca kategoriju u kojoj se takmici clan kluba
     * @return kategorija 
     */
    public Category getCategory() {
        return category;
    }
    /**
     * Postavlja kategoriju u kojoj se clan takmici
     * @param category kategorija u kojoj se clan takmici
     * @throws NullPointerException Ukoliko je kategorija null
     */
    public void setCategory(Category category) {
    	if(category==null)
    		throw new NullPointerException("Kategorija ne sme biti null");
        this.category = category;
    }
    /**
     * Vraca disciplinu u kojoj se takmici clan kluba
     * @return disciplina
     */
    public Discipline getDiscipline() {
        return discipline;
    }
    /**
     * Postavlja disciplinu u kojoj se clan takmici
     * @param discipline disciplina u kojoj se clan takmici
     * @throws NullPointerException Ukoliko je disciplina null
     */
    public void setDiscipline(Discipline discipline) {
    	if(discipline==null)
    		throw new NullPointerException("Disciplina ne sme biti null");
        this.discipline = discipline;
    }
    /**
     * Vraca listu izmirenih clanarina
     * @return lista izmirenih clanarina clana
     */
    public List<MembershipFee> getMembershipFee() {
        return membershipFee;
    }
    /**
     * Postavlja listu clanarina
     * @param membershipFee clanarina clana
     */
    public void setMembershipFee(List<MembershipFee> membershipFee) {
        this.membershipFee = membershipFee;
    }
    /**
     * Vraca adresu stanovanja clana
     * @return adresa stanovanja
     */
    public String getAdress() {
        return adress;
    }
    /**
     * Postavlja adresu stanovanja
     * @param adress adresa stanovanja
     * @throws NullPointerException Ukoliko je prosledjena adresa jednaka null
     * @throws IllegalArgumentException Ukoliko prosledjena adresa ne sadrzi minimum 5 karaktera
     */
    public void setAdress(String adress) {
    	if(adress==null)
    		throw new NullPointerException("Ukoliko je prosledjena adresa null");
    	if(adress.length()<5)
    		throw new IllegalArgumentException("Prosledjena adresa ne sadrzi minimum 5 karaktera");
        this.adress = adress;
    }
    /**
     * Vraca pol clana 
     * @return pol clana
     */
    public Gender getGender() {
        return gender;
    }
    /**
     * Postavlja pol clana
     * @param gender pol clana
     * @throws NullPointerException Ukoliko je pol clana null
     */
    public void setGender(Gender gender) {
    	if(gender==null)
    		throw new NullPointerException("Ukoliko je pol null");
        this.gender = gender;
    }
    /**
     * Vraca datum uclanjena clana u klub
     * @return datum uclanjenja
     */
    public Date getDateOfMembership() {
        return dateOfMembership;
    }
    /**
     * Postavlja datum uclanjenja clana u klub
     * @param dateOfMembership datum uclanjenja
     * @throws NullPointerException Ukoliko je datum null
     * @throws IllegalArgumentException Ukoliko je prosledjeni datum uclanjenja nakon sadasnjeg
     */
    public void setDateOfMembership(Date dateOfMembership) {
    	if(dateOfMembership==null)
    		throw new NullPointerException("Datum uclanjenja ne sme biti null");
    	Date currentDate = new Date();
        if (dateOfMembership.after(currentDate)) 
            throw new IllegalArgumentException("Datum uclanjenja ne sme biti nakon danasnjeg!");
        this.dateOfMembership = dateOfMembership;
    }
    /**
     * Vraca ukupan iznos duga 
     * @return ukupan iznos duga
     */
    public Double getTotalDebt() {
        return totalDebt;
    }
    /**
     * Postavlja ukupnu vrednost duga clana
     * @param totalDebt ukupan dug
     * @throws NullPointerException Ukoliko je ukupan dug clana manji od nule
     */
    public void setTotalDebt(Double totalDebt) {
    	if(totalDebt<0)
    		throw new NullPointerException("Ukoliko je ukupan dug manji od nule");
        this.totalDebt = totalDebt;
    }

    
   

    @Override
	public String toString() {
    	String formattedBirthday = DATE_FORMAT.format(birthday);
    	System.out.println(formattedBirthday);
        String formattedDateOfMembership = DATE_FORMAT.format(dateOfMembership);

		return "Member [name=" + name + ", lastname=" + lastname + ", birthday=" + formattedBirthday + ", discipline="
				+ discipline + ", category=" + category + ", adress=" + adress + ", dateOfMembership="
				+ formattedDateOfMembership + "]";
	}
	@Override
    public String getAttributeList() {
        return "name,lastname,gender,dateOfBirth,mothersName,fathersName,"
                + "adress,belt,dateOfMembership,medals,goldMedals,silverMedals,bronzeMedals,"
                + "discipline,category,cityId,totalDebt";
    }

    @Override
    public String getClassName() {
      return "member";
    }

    @Override
    public String getAttributeValues() {
       return "'"+getName()+"', '"+getLastname()+"', '"+getGender()+"', '"
               + new java.sql.Date(getBirthday().getTime())+"','"+getMothersName()+"', '"
               + getFathersName()+"', '"+getAdress()+"',  '"+getBelt().toString()+"', '"+new java.sql.Date(getDateOfMembership().getTime())+"', "+
               getMedals()+","+getGoldMedals()+","+getSilverMedals()+","+getBronzeMedals()+",'"+getDiscipline().toString()+"', '"+
               getCategory().toString()+"',"+getCity().getId()+","+getTotalDebt();
    }

    @Override
    public String getQueryCondition() {
        return "id="+getMemberID();
    }

    @Override
    public void setId(Long id) {
        this.memberID=id;
    }


    @Override
    //vrati broj vezanih objekata
    //lista clanarina
    public int getNumberOfBountObject() {
        return getMembershipFee().size();
    }

    @Override
    public Object getBoundObject(int numberOfObjects,int numberOfBoundAttributes) {
        return getMembershipFee().get(numberOfObjects);
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        return 1;
    }

    @Override
    public void setForeignId(Long id) {
        setMemberID(id);
    }

    @Override
    public String setAttributeValues() {
        return "id="+getMemberID()+","
                +"name='"+getName()+"',"+
                "lastname='"+getLastname()+"',"+
                "gender='"+getGender().toString()+"',"+
                "dateOfBirth='"+new java.sql.Date(getBirthday().getTime())+"',"+
                "mothersName='"+getMothersName()+"',"+
                "fathersName='"+getFathersName()+"',"+
                "adress='"+getAdress()+"',"+
                "belt='"+getBelt().toString()+"',"+
                "discipline='"+getDiscipline().toString()+"',"+
                "category='"+getCategory().toString()+"',"+
                "dateOfMembership='"+new java.sql.Date(getDateOfMembership().getTime())+"',"+
                "medals="+getMedals()+","+
                "goldMedals="+getGoldMedals()+","+
                "silverMedals="+getSilverMedals()+","+
                "bronzeMedals="+getBronzeMedals()+","+
                "cityId="+getCity().getId()+","+
                "totalDebt="+getTotalDebt();
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        City c= new City(rs.getLong("cityId"), rs.getLong("ptt"), rs.getString("cityName"));
        return new Member(rs.getLong("memberId"), rs.getString("name"), rs.getString("lastname"),Gender.valueOf(rs.getString("gender")), 
                rs.getDate("birthday"), rs.getString("mother"), rs.getString("father"), c,Belt.valueOf(rs.getString("belt")), 
                rs.getLong("medals"), Discipline.valueOf(rs.getString("discipline")), Category.valueOf(rs.getString("category")), rs.getString("adress"), 
                rs.getLong("gold"), rs.getLong("silver"), rs.getLong("bronze"), 
                new ArrayList<MembershipFee>(), rs.getDate("dateOfMembership"), rs.getDouble("totalDebt"));
    }
    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT m.id as memberId, m.name as name, m.lastname as lastname,"
                    + "m.gender as gender,m.dateOfBirth as birthday,m.mothersName as mother,"
                    + "m.fathersName as father, m.adress as adress, m.belt as belt,"
                    + "m.discipline as discipline, m.category as category,"
                    + "m.dateOfMembership as dateOfMembership,"
                    + "m.medals as medals,m.cityId as cityId,m.goldMedals as gold, m.silverMedals as silver, m.bronzeMedals as bronze,"
                    + "m.totalDebt as totalDebt, c.name as cityName,"
                    + "c.ptt as ptt FROM member m INNER JOIN city c ON (m.cityId=c.id)";
        
    }

    @Override
    public Long getId() {
        return memberID;
    }

    @Override
    public String getBoundObjectClassName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getBoundObjectAttributeList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getBoundObjectAttributeValues(int attributeNumber) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    

   
    
}
