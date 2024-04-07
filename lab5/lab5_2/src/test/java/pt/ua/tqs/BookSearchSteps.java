package pt.ua.tqs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
 
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
 
import pt.ua.tqs.Book;
import pt.ua.tqs.Library;
 
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

    /*
	create a registered type named iso8601Date to map a string pattern from the feature 
	into a custom datatype. Extracted parameters should be strings.
	 */
	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDate iso8601Date(String year, String month, String day){
		return Utils.localDateFromDateParts(year, month, day);
	}
	
	/**
	 * load a data table from the feature (tabular format) and call this method
	 * for each row in the table. Injected parameter is a map with column name --> value
	 */
	@DataTableType
	public Book bookEntry(Map<String, String> tableEntry){
		return new Book(
				tableEntry.get("title"),
				tableEntry.get("author"),
				Utils.dateFromLocalDate(Utils.isoTextToLocalDate( tableEntry.get("published") ) ));
	}
 
    /*
	@Given(".+a book with the title '(.+)', written by '(.+)', published in {iso8601Date}")
	public void addNewBook(final String title, final String author, LocalDate published) {
		Book book = new Book(title, author, Utils.dateFromLocalDate(published));
		library.addBook(book);
	}
    */
 
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(LocalDate from, LocalDate to) {
		result = library.findBooks(from, to);
	}
 
	@Then("(\\d+) books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addNewBook(String title, String author, LocalDate date) {
        Book book = new Book(title, author, Utils.dateFromLocalDate(date));
		library.addBook(book);
    }

    @Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void anotherAddBook(String title, String author, LocalDate date) {
        Book book = new Book(title, author, Utils.dateFromLocalDate(date));
		library.addBook(book);
    }
}