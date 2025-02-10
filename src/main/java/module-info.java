module lk.ijse.gdse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires static lombok;
    requires com.google.protobuf;
    requires net.sf.jasperreports.core;

    exports lk.ijse.gdse;
    exports lk.ijse.gdse.controller;
    opens lk.ijse.gdse.controller to javafx.fxml;
    exports lk.ijse.gdse.dao;
    exports lk.ijse.gdse.dto;
    exports lk.ijse.gdse.entity;
    exports lk.ijse.gdse.bo;
    exports lk.ijse.gdse.dto.tm;


}