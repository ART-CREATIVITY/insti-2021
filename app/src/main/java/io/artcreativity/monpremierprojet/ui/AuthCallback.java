package io.artcreativity.monpremierprojet.ui;

public interface AuthCallback {

    void sendMessage(String phoneNumber);
    void verification(String code);

}
