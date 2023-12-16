package com.thesis.bus_station.mail;

public interface MailFormatter<T> {
    String getText(T params);
    String getSubject();
}
