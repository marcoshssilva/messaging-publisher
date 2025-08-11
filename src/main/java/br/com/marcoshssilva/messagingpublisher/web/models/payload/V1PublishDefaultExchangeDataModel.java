package br.com.marcoshssilva.messagingpublisher.web.models.payload;

public record V1PublishDefaultExchangeDataModel(String routingKey, byte[] base64) { }
