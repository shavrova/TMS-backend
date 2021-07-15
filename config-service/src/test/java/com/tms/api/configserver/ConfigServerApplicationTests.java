package com.tms.api.configserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerApplicationTests {
    @LocalServerPort
    private int port;

    @Test
    void whenEncryptText_andDecrypt_thenResultsAreEqual() {
        String text = "Text to encrypt" + UUID.randomUUID();
        String encrypted = new RestTemplate().postForObject("http://localhost:" + port + "encrypt", new HttpEntity<>(text, new HttpHeaders()), String.class);
        String decrypted = new RestTemplate().postForObject("http://localhost:" + port + "decrypt", new HttpEntity<>(encrypted, new HttpHeaders()), String.class);
        assertEquals(decrypted, text, "Decrypted text is not equal to encrypted");
    }

    @Test
    void whenRequestZuulProperties_thenResponseContainsPropertySourceField() {
        String response = new RestTemplate().getForObject("http://localhost:" + port + "zuul/default", String.class);
        System.out.println("res: " + response);
        assertTrue(response.contains("\"spring.application.name\":\"zuul\""));
    }

    @Test
    void whenRequestEurekaDiscoveryServiceProperties_thenResponseContainsPropertySourceField() {
        String response = new RestTemplate().getForObject("http://localhost:" + port + "discoveryservice/default", String.class);
        assertTrue(response.contains("\"spring.application.name\":\"discoveryservice\""));
    }

    @Test
    void whenRequestUsersServiceServiceProperties_thenResponseContainsPropertySourceField() {
        String response = new RestTemplate().getForObject("http://localhost:" + port + "users-ws/default", String.class);
        assertTrue(response.contains("\"spring.application.name\":\"users-ws\""));
    }
}
