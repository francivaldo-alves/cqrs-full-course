package com.f3pro.beautique.ms_sync.listeners;

public interface ListenerConfig {

    void listenToCustomerQueue(String messege);
    void listenToAppointmentQueue(String messege);
    void listenToBeautyProcedureQueue(String messege);

}
