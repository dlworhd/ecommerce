package com.ecommerce.order.adapter.out;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class OrderGenerator implements IdentifierGenerator {

	private static final String PREFIX = "E-";

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		String timestamp = now.format(formatter);
		String orderId = PREFIX + timestamp;
		return orderId;
	}


}
