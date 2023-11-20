package jrails;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    // private Book book1;
    // private Model book2;


    @Before
    public void setUp() throws Exception {
        // Book book1 = new Book();
        // book1.title = "Programming Languages: Build, Prove, and Compare";
        // book1.author = "Norman Ramsey";
        // book1.num_copies = 999;
        // The book b exists in memory but isn't saved to the db
        
    }
    // @Test
    // public void testMultipleFind() {
    //     Book book2 = new Book();
    //     book2.title = "Programming Languages: 'Build', 'Prove', and 'Compare'";
    //     book2.author = "Norman Ramsey";
    //     book2.num_copies = 999;
    //     book2.isAvailable = false;
    //     // The book b exists in memory but isn't saved to the db
    //     System.out.println("Saving book2 model ");
    //     book2.save(); // now the book is in the db
    // }
    // @Test
    // public void testMultipleFind() {
    //     // Save the first model
    //     Book book1 = new Book();
    //     book1.title = "Random";
    //     book1.author = "RandomAuthor";
    //     book1.num_copies = 100;
    //     book1.isAvailable = true;
    //     System.out.println("Saving book1 model ");
    //     book1.save(); // now the book is in the db
    //     // model1.save();
    //     // System.out.println("model1.id() " + book1.id());

    //     Book book2 = new Book();
    //     book2.title = "Programming Languages: Build, Prove, and Compare";
    //     book2.author = "Norman Ramsey";
    //     book2.num_copies = 999;
    //     book2.isAvailable = false;
    //     // The book b exists in memory but isn't saved to the db
    //     System.out.println("Saving book2 model ");
    //     book2.save(); // now the book is in the db

    //     Book book3 = new Book();
    //     book3.title = "Software Engineering";
    //     book3.author = "Jeff";
    //     book3.num_copies = 1000;
    //     book3.isAvailable = false;
    //     // // The book book3 exists in memory but isn't saved to the db
    //     System.out.println("Saving book3 model ");
    //     book3.save(); // now the book is in the db

    //     Book copy1 = Book.find(Book.class, book1.id());
    //     Book copy2 = Book.find(Book.class, book2.id());
    //     Book copy3 = Book.find(Book.class, book3.id());

    //     assertNotEquals(0, book1.id());
    //     System.out.println("book1.id() " + book1.id() + " not equals zero ");
    //     assertEquals(book1.id(), copy1.id());
    //     System.out.println("book1.id() " + book1.id() + " equals " + " copy1.id() " + copy1.id());
    //     assertEquals(book1.title, copy1.title);
    //     System.out.println("book1.title " + book1.title + " equals " + " copy1.title " + copy1.title);
    //     assertEquals(book1.author, copy1.author);
    //     System.out.println("book1.author " + book1.author + " equals " + " copy1.author " + copy1.author);
    //     assertEquals(book1.num_copies, copy1.num_copies);
    //     System.out.println("book1.num_copies " + book1.num_copies + " equals " + " copy1.num_copies " + copy1.num_copies);
    //     assertEquals(book1.isAvailable, copy1.isAvailable);
    //     System.out.println("book1.isAvailable " + book1.isAvailable + " equals " + " copy1.isAvailable " + copy1.isAvailable);
    
    //     assertNotEquals(0, book2.id());
    //     System.out.println("book2.id() " + book2.id() + " not equals zero ");
    //     assertEquals(book2.id(), copy2.id());
    //     System.out.println("book2.id() " + book2.id() + " equals " + " copy2.id() " + copy2.id());
    //     assertEquals(book2.title, copy2.title);
    //     System.out.println("book2.title " + book2.title + " equals " + " copy2.title " + copy2.title);
    //     assertEquals(book2.author, copy2.author);
    //     System.out.println("book2.author " + book2.author + " equals " + " copy2.author " + copy2.author);
    //     assertEquals(book2.num_copies, copy2.num_copies);
    //     System.out.println("book2.num_copies " + book2.num_copies + " equals " + " copy2.num_copies " + copy2.num_copies);
    //     assertEquals(book2.isAvailable, copy2.isAvailable);
    //     System.out.println("book2.isAvailable " + book2.isAvailable + " equals " + " copy2.isAvailable " + copy2.isAvailable);
    
    //     assertNotEquals(0, book3.id());
    //     System.out.println("book3.id() " + book3.id() + " not equals zero ");
    //     assertEquals(book3.id(), copy3.id());
    //     System.out.println("book3.id() " + book3.id() + " equals " + " copy3.id() " + copy3.id());
    //     assertEquals(book3.title, copy3.title);
    //     System.out.println("book3.title " + book3.title + " equals " + " copy3.title " + copy3.title);
    //     assertEquals(book3.author, copy3.author);
    //     System.out.println("book3.author " + book3.author + " equals " + " copy3.author " + copy3.author);
    //     assertEquals(book3.num_copies, copy3.num_copies);
    //     System.out.println("book3.num_copies " + book3.num_copies + " equals " + " copy3.num_copies " + copy3.num_copies);
    //     assertEquals(book3.isAvailable, copy3.isAvailable);
    //     System.out.println("book3.isAvailable " + book3.isAvailable + " equals " + " copy3.isAvailable " + copy3.isAvailable);
    // }
    // @Test
    // public void testDestroy() {
    //     // Save the first model
    //     Book book1 = new Book();
    //     book1.destroy();
    // }
    @Test
    public void testReset() {
        // Save the first model
        Book.reset();
    }
    @Test
    public void testSingle() {
        // Save the first model;
        Book book1 = new Book();
        book1.title = "Random";
        book1.author = "RandomAuthor";
        book1.num_copies = 400;
        book1.save(); // now the book is in the db
    }

    // @Test
    // public void testFind() {
    //     // Save the first model;
    //     Book.reset();
    //     Book book1 = new Book();
    //     book1.title = "WHITE";
    //     book1.author = "cat";
    //     book1.num_copies = 1;
    //     book1.isAvailable = true;
    //     book1.save(); // now the book is in the db
    //     Book copy1 = Book.find(Book.class, book1.id());
    //     assertNotEquals(0, book1.id());
    //     System.out.println("book1.id() " + book1.id() + " not equals zero ");
    //     assertEquals(book1.id(), copy1.id());
    //     System.out.println("book1.id() " + book1.id() + " equals " + " copy1.id() " + copy1.id());
    //     Book.reset();
    // }

    @After
    public void tearDown() throws Exception {
    }
}