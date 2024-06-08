package com.seuslab.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seuslab.application.service.pet.BaseFillService;
import com.seuslab.application.service.pet.BaseReaderService;
import com.seuslab.application.vk.VKApiService;
import com.seuslab.application.vk.dto.VKUserGetRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SobesApplicationTests {

	@Autowired
	private VKApiService vkApiService;

	@Autowired
	private BaseReaderService baseReaderService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetRequest() {
		vkApiService.userGetRequest(460901028L);
	}

	@Test
	void testBaseFiller() throws InterruptedException {
		baseReaderService.startReadingFromBase();
	}

}
