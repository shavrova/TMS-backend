package com.tms.api.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
}
