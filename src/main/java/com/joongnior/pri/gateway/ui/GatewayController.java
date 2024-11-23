package com.joongnior.pri.gateway.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GatewayController {
    private final Environment env;

    @GetMapping("/healthcheck")
    public String healthcheck(@RequestHeader(value = "Accept", defaultValue = "") String acceptHeader) {
        /*
         * @RequestHeader: HTTP 요청 헤더에서 값을 읽어와 메서드 파라미터에 주입
         * value = "Accept": HTTP 요청 헤더 중 Accept라는 이름의 헤더를 지정.
         *  클라이언트가 서버 응답으로 기대하는 MIME타입(미디어 타입)을 지정하는 표준 헤더이다.
         * MIME(Multipurpose Internet Mail Extensions type):
         *  인터넷 상에서 전송되는 데이터의 형식을 표현하기 위한 표준 포맷이다.
         *  MIME 타입은 클라이언트와 서버 간 통신에서 데이터가 어떤 형식인지 식별할 수 있게 한다.
         */
        String lineSeparator = "\n";

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            log.info("acceptHeader={}", acceptHeader);
            lineSeparator = "<br>";
        }

        return "[" + env.getProperty("spring.application.name") + " healthcheck]" + lineSeparator
                + "port(local.server.port)=" + env.getProperty("local.server.port") + lineSeparator
                + "port(server.port)=" + env.getProperty("server.port") + lineSeparator
                + "with token secret=" + env.getProperty("token.secret") + lineSeparator
                + "with token time=" + env.getProperty("token.expiration_time") + lineSeparator
                + "with encryption-test=" + env.getProperty("encryption-test");
    }
}
