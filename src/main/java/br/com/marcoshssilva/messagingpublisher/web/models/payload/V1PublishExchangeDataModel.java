package br.com.marcoshssilva.messagingpublisher.web.models.payload;

public record V1PublishExchangeDataModel(String exchange, String routingKey, byte[] base64) { }
