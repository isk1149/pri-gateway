package com.joongnior.pri.gateway.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatewayController {
    private final Environment env;

    @GetMapping("/healthcheck")
    public String healthcheck(@RequestHeader(value = "Accept", defaultValue = "") String acceptHeader) {
        String lineSeparator = "\n";

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE))
            lineSeparator = "<br>";

        return "[" + env.getProperty("spring.application.name") + " healthcheck]" + lineSeparator
                + "port(local.server.port)=" + env.getProperty("local.server.port") + lineSeparator
                + "port(server.port)=" + env.getProperty("server.port") + lineSeparator
                + "with token secret=" + env.getProperty("token.secret") + lineSeparator
                + "with token time=" + env.getProperty("token.expiration_time") + lineSeparator
                + "with encryption-test=" + env.getProperty("encryption-test");
    }
}
