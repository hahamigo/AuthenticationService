package authenticationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerList {
    private Collection<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }

	public Collection<Customer> getCustomers() {
		return customers;
	}

    // standard constructor and getter/setter
}
