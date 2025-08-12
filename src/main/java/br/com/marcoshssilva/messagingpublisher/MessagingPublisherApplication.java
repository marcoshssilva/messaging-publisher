package br.com.marcoshssilva.messagingpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import java.util.concurrent.Executors;

@EnableAsync
@SpringBootApplication
public class MessagingPublisherApplication {
	public static void main(String[] args) {
		SpringApplication.run(MessagingPublisherApplication.class, args);
	}

	@Bean
	TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutor() {
		return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
	}

	@Bean
	public TaskExecutor applicationTaskExecutor() {
		return new ConcurrentTaskExecutor(Executors.newVirtualThreadPerTaskExecutor());
	}
}
