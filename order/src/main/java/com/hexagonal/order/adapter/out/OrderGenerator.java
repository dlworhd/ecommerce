package com.hexagonal.order.adapter.out;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Component
public class OrderGenerator {

	private static Long sequence;
	private static final String PREFIX = "GIN";
	private final OrderSequenceRepository sequenceRepository;

	public OrderGenerator(OrderSequenceRepository sequenceRepository) {
		this.sequenceRepository = sequenceRepository;
	}

	@PostConstruct
	private void init() {
		Optional<OrderSequence> findSequence = sequenceRepository.findById(1L);

		if (findSequence.isPresent()) {
			OrderSequence savedSequence = findSequence.get();
			sequence = savedSequence.getLastSequence();

		} else {
			OrderSequence newSequence = new OrderSequence();
			newSequence.setLastSequence(1L);
			sequence = newSequence.getLastSequence();
			sequenceRepository.save(newSequence);
		}
	}

	@PreDestroy
	@Transactional
	private void saveSequence() {
		OrderSequence findSequence = sequenceRepository.findById(1L)
				.orElseThrow(() -> new RuntimeException("Sequence not found"));
		findSequence.setLastSequence(sequence);
		sequenceRepository.save(findSequence);
	}


	public String generateOrderId() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		String timestamp = now.format(formatter);
		String orderId = PREFIX + timestamp + getNextSequence();
		return orderId;
	}

	private synchronized Long getNextSequence() {
		return sequence++;
	}
}
