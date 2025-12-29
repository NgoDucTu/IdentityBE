package com.tund.identity.service.configuration;

import com.nimbusds.jose.JOSEException;
import com.tund.identity.service.dto.request.IntrospectRequest;
import com.tund.identity.service.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    private String scKey;

    @Autowired
    private AuthenticationService authenticationService;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    public Jwt decode(String token) throws JwtException {

        try {
            var resp = authenticationService.introspect(
                    IntrospectRequest.builder()
                            .token(token)
                            .build()
            );

            if (!resp.isValid()) throw new JwtException("Invalid Token");
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(scKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
