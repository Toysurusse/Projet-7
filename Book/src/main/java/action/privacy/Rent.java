package action.privacy;


import client.Authentication;
import client.book.BookClient;
import client.book.SoapClientBookConfig;
import client.rent.SoapClientRentConfig;
import com.javainuse.*;
import com.opensymphony.xwork2.ActionSupport;
import entity.BookAndRent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;

public class Rent extends Connect {

    public Date create_at;
    public Date end_at;
    public String today;
    public String today4W;
    public String threeWeeks;
    public List<Rentbook> rentbook;
    public List<Book> bookList;
    public List<BookAndRent> listrented = new ArrayList<>();
    public HashMap<String, Book> rentResult = new HashMap<>();


    public String execute() throws Exception {

        //utiliser le calendrier par défaut
        Calendar calendar = Calendar.getInstance();

        //définir le format de la date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        today = formater.format(java.util.Calendar.getInstance().getTime());
        calendar.add(calendar.MONTH, 1);
        today4W = formater.format(calendar.getTime());
        shoppingList = (List<Book>) this.map.get("shop");
        return SUCCESS;
    }

    public String rentbook() throws Exception {

        List<Rentbook> rents = new ArrayList<>();
        User user = (User) this.map.get("user");

        shoppingList = (List<Book>) this.map.get("shop");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientRentConfig.class);
        client.rent.Rent client = context.getBean(client.rent.Rent.class);

        if (end_at != null && create_at != null) {
            if (end_at.before(create_at)) {
                this.addActionError("error.DatePost");
            } else {
                for (Book aShoppingList : shoppingList) {
                    Rentbook rent = new Rentbook();
                    rent.setBookId(aShoppingList.getId());
                    rent.setUserId(user.getUserid());
                    rent.setCreateat(translate(create_at));
                    rent.setEndat(translate(end_at));
                    rent.setReload(false);
                    rent.setReturnbook(false);
                    System.out.println(rent.getUserId() + " ; " + rent.getBookId() + " ; " + rent.getRentid() + " ; " + rent.getCreateat() + " ; " + rent.getEndat() + " ; " + rent.isReload() + " ; " + rent.isReturnbook());
                    OutputSOARentbookAddConfirm outputSOAddConfirm = client.getRentbookAdd(new Authentication("username", "password"), rent);
                    rentResult.put(outputSOAddConfirm.getResult(), aShoppingList);
                }
            }
        } else {
            this.addActionError("error.DateEmpty");
        }

        for (Map.Entry<String, Book> e : rentResult.entrySet()) {
            if (e.getKey().equals("Ok")) {
                shoppingList.remove(e.getValue());
                rentResult.remove(e.getKey(), e.getValue());
            }
        }

        this.map.remove("shop");
        this.map.put("shop", shoppingList);

        return (this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS;
    }


    public String rented() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        today = formater.format(java.util.Calendar.getInstance().getTime());
        User user = (User) this.map.get("user");
        int id = user.getUserid();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientRentConfig.class);
        client.rent.Rent client = context.getBean(client.rent.Rent.class);

        OutputSOARentbookByUser outputSOARentbookByUser = null;
        outputSOARentbookByUser = client.getRentbookByUser(new Authentication("username", "password"), id);

        rentbook = new ArrayList<>();

        AnnotationConfigApplicationContext contextbook = new AnnotationConfigApplicationContext(SoapClientBookConfig.class);
        client.book.BookClient clientbook = contextbook.getBean(BookClient.class);
        OutputSOABook response = clientbook.getBook(new Authentication("username", "password"));

        rentbook = outputSOARentbookByUser.getResult();
            bookList = response.getResult();
            for (Rentbook r : rentbook) {
                for (Book b : bookList) {
                    if (r.getBookId() == b.getId()) {
                        BookAndRent bookAndRent = new BookAndRent(b, r);
                        listrented.add(bookAndRent);
                    }
                }
            }
        return SUCCESS;
    }

    public String returnBook() throws Exception {

        User user = (User) this.map.get("user");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientRentConfig.class);
        client.rent.Rent client = context.getBean(client.rent.Rent.class);
        OutputSOARentbookById outputSOARentbookById = client.getRentbookById(new Authentication("username", "password"), idBook);
        outputSOARentbookById.getResult().setReturnbook(true);
        OutputSOARentbookAddConfirm outputSOARentbookAddConfirm = client.getRentbookAdd(new Authentication("username", "password"), outputSOARentbookById.getResult());

        System.out.println(outputSOARentbookAddConfirm.getResult());

        rentbook = new ArrayList<>();

        AnnotationConfigApplicationContext contextbook = new AnnotationConfigApplicationContext(SoapClientBookConfig.class);
        client.book.BookClient clientbook = contextbook.getBean(BookClient.class);
        OutputSOABookById outputSOABookById = clientbook.getBookById(new Authentication("username", "password"), outputSOARentbookById.getResult().getBookId());
        OutputSOAddConfirm outputSOAddConfirm = clientbook.getBookAdd(new Authentication("username", "password"), outputSOABookById.getResult());

        System.out.println(outputSOAddConfirm.getResult());

        return SUCCESS;
    }

    public String input() throws Exception {

        shoppingList = (List<Book>) this.map.get("shop");
        System.out.println(end_at + " :c " + create_at);

        return SUCCESS;
    }

    public XMLGregorianCalendar translate(Date date) {
        GregorianCalendar Gdate = new GregorianCalendar();
        Gdate.setTime(date);
        XMLGregorianCalendar xmlDate = null;
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(Gdate);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xmlDate;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }


    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public String getThreeWeeks() {
        return threeWeeks;
    }

    public void setThreeWeeks(String threeWeeks) {
        this.threeWeeks = threeWeeks;
    }

    public List<Rentbook> getRentbook() {
        return rentbook;
    }

    public void setRentbook(List<Rentbook> rentbook) {
        this.rentbook = rentbook;
    }

    public HashMap<String, Book> getRentResult() {
        return rentResult;
    }

    public void setRentResult(HashMap<String, Book> rentResult) {
        this.rentResult = rentResult;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


    public List<BookAndRent> getListrented() {
        return listrented;
    }

    public void setListrented(List<BookAndRent> listrented) {
        this.listrented = listrented;
    }

}
