package com.desafio.catalogo.produtos;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
public class CatalogoProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoProdutosApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		Server server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
		return server;
	}
}
