package com.gyub.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SingleScheduler {

	@Scheduled(fixedRate = 1000)
	public void fixedRate() throws InterruptedException {
		log.info("{} fixedRate() 실행", Thread.currentThread().getName());
		Thread.sleep(3000);
	}

	@Scheduled(fixedRate = 1000)
	public void fixedRate2() throws InterruptedException {
		log.info("{} fixedRate()2 실행", Thread.currentThread().getName());
		Thread.sleep(3000);
	}
}
