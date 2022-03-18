package ro.appptozee.ecommerce.common.dto;

import java.util.List;

public record OrderDto(List<LineItem> cart) {}
