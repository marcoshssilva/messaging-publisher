package br.com.marcoshssilva.messagingpublisher.web.control;

import br.com.marcoshssilva.messagingpublisher.web.models.payload.V1PublishDefaultExchangeDataModel;
import br.com.marcoshssilva.messagingpublisher.web.models.payload.V1PublishExchangeDataModel;
import br.com.marcoshssilva.messagingpublisher.web.models.response.DefaultWebResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/message")
public class V1MessagingPublisher {
    private static final String DEFAULT_SUCCESS_MESSAGE = "Published with SUCCESS";

    private final RabbitTemplate rabbitTemplate;

    public V1MessagingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/publish-exchange")
    public ResponseEntity<DefaultWebResponse> publishExchange(@RequestBody V1PublishExchangeDataModel data) {
        try {
            rabbitTemplate.convertAndSend(data.exchange(), data.routingKey(), data.base64());
            return ResponseEntity.ok(new DefaultWebResponse(DEFAULT_SUCCESS_MESSAGE, "SUCCESS"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new DefaultWebResponse(e.getMessage(), "ERROR"));
        }

    }

    @PostMapping(value = "/publish-default-exchange")
    public ResponseEntity<DefaultWebResponse> publishQueue(@RequestBody V1PublishDefaultExchangeDataModel data) {
        try {
            rabbitTemplate.convertAndSend(data.routingKey(), data.base64());
            return ResponseEntity.ok(new DefaultWebResponse(DEFAULT_SUCCESS_MESSAGE, "SUCCESS"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new DefaultWebResponse(e.getMessage(), "ERROR"));
        }

    }
}
