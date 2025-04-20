package com.aquariux.test.cryptocurrencytradingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class CryptocurrencyTradingSystemApplication {

    public static void main(String[] args) throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
        SpringApplication.run(CryptocurrencyTradingSystemApplication.class, args);
    }

}
