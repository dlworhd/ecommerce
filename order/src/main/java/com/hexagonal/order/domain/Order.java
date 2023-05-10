package com.hexagonal.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {

	private UUID userId;
	private String name;
	private String addr;
	private String phoneNum;
	private List<ProductInfo> productInfos;

	@Getter
	@Setter
	public static class ProductInfo{
		private Long id;
		private Long price;
		private Long quantity;
	}
}
