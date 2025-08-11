package br.com.marcoshssilva.messagingpublisher.web.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class DefaultWebResponse {
    private String message;
    private TypeDefaultWebResponseEnum status;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ssZ")
    private Date date;

    public DefaultWebResponse(String message, TypeDefaultWebResponseEnum status) {
        this.message = message;
        this.status = status;
        this.date = new Date();
    }

    public DefaultWebResponse(String message, TypeDefaultWebResponseEnum status, Date date) {
        this.message = message;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeDefaultWebResponseEnum getStatus() {
        return status;
    }

    public void setStatus(TypeDefaultWebResponseEnum status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DefaultWebResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultWebResponse that = (DefaultWebResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status);
    }
}
