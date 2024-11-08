package com.nourdine.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void testParseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(generateValidGETTestCase());

        } catch (HttpParsingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(), HttpMethod.GET);
    }

    @Test
    void testParseHttpRequestBadMethod1() {

        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadGETTestCase1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }

    }

    @Test
    void testParseHttpRequestBadMethod2() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(generateBadGETTestCase2());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }

    }

    @Test
    void testParseHttpRequestBadMethod3() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(generateBadGETTestCase3());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);

        }

    }

    private InputStream generateValidGETTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" + //
                "Host: localhost:8080\r\n" + //
                "Connection: keep-alive\r\n" + //
                "sec-ch-ua: \"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"\r\n" + //
                "sec-ch-ua-mobile: ?0\r\n" + //
                "sec-ch-ua-platform: \"Windows\"\r\n" + //
                "Upgrade-Insecure-Requests: 1\r\n" + //
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36\r\n"
                + //
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
                + //
                "Sec-Fetch-Site: none\r\n" + //
                "Sec-Fetch-Mode: navigate\r\n" + //
                "Sec-Fetch-User: ?1\r\n" + //
                "Sec-Fetch-Dest: document\r\n" + //
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                "Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7,ar;q=0.6";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadGETTestCase1() {
        String rawData = "GeT / HTTP/1.1\r\n" + //
                "Host: localhost:8080\r\n" + //
                "Connection: keep-alive\r\n" + //
                "sec-ch-ua: \"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"\r\n" + //
                "sec-ch-ua-mobile: ?0\r\n" + //
                "sec-ch-ua-platform: \"Windows\"\r\n" + //
                "Upgrade-Insecure-Requests: 1\r\n" + //
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36\r\n"
                + //
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
                + //
                "Sec-Fetch-Site: none\r\n" + //
                "Sec-Fetch-Mode: navigate\r\n" + //
                "Sec-Fetch-User: ?1\r\n" + //
                "Sec-Fetch-Dest: document\r\n" + //
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                "Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7,ar;q=0.6";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadGETTestCase2() {
        String rawData = "GETTT / HTTP/1.1\r\n" + //
                "Host: localhost:8080\r\n" + //
                "Connection: keep-alive\r\n" + //
                "sec-ch-ua: \"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"\r\n" + //
                "sec-ch-ua-mobile: ?0\r\n" + //
                "sec-ch-ua-platform: \"Windows\"\r\n" + //
                "Upgrade-Insecure-Requests: 1\r\n" + //
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36\r\n"
                + //
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
                + //
                "Sec-Fetch-Site: none\r\n" + //
                "Sec-Fetch-Mode: navigate\r\n" + //
                "Sec-Fetch-User: ?1\r\n" + //
                "Sec-Fetch-Dest: document\r\n" + //
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                "Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7,ar;q=0.6";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadGETTestCase3() {
        String rawData = "GETTT / AAAAA HTTP/1.1\r\n" + //
                "Host: localhost:8080\r\n" + //
                "Connection: keep-alive\r\n" + //
                "sec-ch-ua: \"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"\r\n" + //
                "sec-ch-ua-mobile: ?0\r\n" + //
                "sec-ch-ua-platform: \"Windows\"\r\n" + //
                "Upgrade-Insecure-Requests: 1\r\n" + //
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36\r\n"
                + //
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
                + //
                "Sec-Fetch-Site: none\r\n" + //
                "Sec-Fetch-Mode: navigate\r\n" + //
                "Sec-Fetch-User: ?1\r\n" + //
                "Sec-Fetch-Dest: document\r\n" + //
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                "Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7,ar;q=0.6";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

}
