package caffeinateMe.stepDefinitions;

import caffeinateMe.Barrista;
import caffeinateMe.Customer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class OrderCoffeeSteps {
    Customer cathy = new Customer();
    String cathysOrder;
    Barrista barry = new Barrista();

    //Se pueden poner expresiones regulares para que el dato sea variable
    //Se permiten uno o mas digitos
    @Given("Cathy is (\\d+) meters from the coffee shop")
    public void cathy_is_meters_from_the_coffee_shop(int distance) {
        cathy.setDistanceFromShop(distance);
    }

    //Coincide con cualquier caracter
    @When("Cathy orders a (.*)")
    public void cathy_orders_a_large_cappuccino(String order) {
        cathysOrder = order;
        cathy.placesOrderFor(cathysOrder);
    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(barry.getPendingOrder(), hasItem(cathysOrder));
    }

    @Then("Barry should know that the coffee is Urgent")
    public void barry_should_know_that_the_coffee_is_urgent() {
        assertThat(barry.getUrgentOrders(), hasItem(cathysOrder));
    }
}
