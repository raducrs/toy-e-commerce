package ro.appptozee.ecommerce.common.dto.inventory;

import java.util.List;

public record OrderDto(List<LineItem> cart) {}
