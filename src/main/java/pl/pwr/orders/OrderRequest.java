package pl.pwr.orders;

import java.util.List;

public record OrderRequest(Customer customer, List<Product> products) {
}