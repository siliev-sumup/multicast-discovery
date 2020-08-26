package com.forthset.authentication.service.impl;

import com.forthset.authentication.service.KeyService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class LocalKeyServiceImpl implements KeyService {

    private static final KeyPair KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);

    @Override
    public KeyPair getAuthKeys() {
        return KEY_PAIR;
    }
}
