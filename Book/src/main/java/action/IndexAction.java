/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package action;

import client.Authentication;
import com.javainuse.Book;
import com.javainuse.OutputSOABook;
import com.javainuse.OutputSOABookSearch;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import client.book.BookClient;
import client.book.SoapClientBookConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 */
@Conversion()
public class IndexAction extends ActionSupport {

    private Date now = new Date(System.currentTimeMillis());
    @TypeConversion(converter = "action.DateConverter")
    public Date getDateNow() { return now; }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> bookList;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String search;

    public String execute() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientBookConfig.class);
        BookClient client = context.getBean(BookClient.class);
        OutputSOABook response = client.getBook(new Authentication("username","password"));
        bookList=response.getResult();

        now = new Date(System.currentTimeMillis());
        return SUCCESS;
    }

    public String search() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientBookConfig.class);
        BookClient client = context.getBean(BookClient.class);
        OutputSOABookSearch response = client.getSearch(new Authentication("username","password"),search);
        bookList=null;
        bookList=new ArrayList<>();
        bookList=response.getResult();

        now = new Date(System.currentTimeMillis());
        return SUCCESS;
    }
}
