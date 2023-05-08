package com.hexagonal.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

	private String name;
	private String addr;
	private String phoneNum;
	private List<ProductInfo> productInfos;
//
//	@Getter
//	@Setter
//	public static class ProductInfo{
//		private Long id;
//		private Long price;
//		private Long quantity;
//		private String productName;
//	}

	@Getter
	@Setter
	public static class ProductInfo{
		private Long id;
		private Long price;
		private Long quantity;
	}
}
