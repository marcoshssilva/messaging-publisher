package br.com.marcoshssilva.messagingpublisher;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessagingPublisherApplicationTests extends TestContainersConfiguration {

	@LocalServerPort
	private Integer serverPort;

	@LocalManagementPort
	private Integer managementServerPort;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Test
	void shouldOpenDefaultWebPage() {
		RestAssured.baseURI = "http://localhost:" + serverPort + contextPath;
		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/")
				.then()
				.statusCode(200);
	}

	@Test
	void shouldReturnStatusStartupSuccesfully() {
		RestAssured.baseURI = "http://localhost:" + managementServerPort;
		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/actuator/health")
				.then()
				.statusCode(200);
	}

}
