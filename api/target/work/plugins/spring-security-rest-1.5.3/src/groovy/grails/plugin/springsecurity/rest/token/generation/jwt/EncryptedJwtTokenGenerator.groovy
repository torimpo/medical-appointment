/*
 * Copyright 2013-2015 Alvaro Sanchez-Mariscal <alvaro.sanchezmariscal@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package grails.plugin.springsecurity.rest.token.generation.jwt

import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import groovy.util.logging.Slf4j

/**
 * Generates RSA-encrypted JWT's
 */
@Slf4j
class EncryptedJwtTokenGenerator extends AbstractJwtTokenGenerator {

    RSAKeyProvider keyProvider

    @Override
    protected String generateAccessToken(JWTClaimsSet claimsSet) {
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP, EncryptionMethod.A256GCM)

        // Create the encrypted JWT object
        EncryptedJWT jwt = new EncryptedJWT(header, claimsSet)

        // Create an encrypter with the specified public RSA key
        RSAEncrypter encrypter = new RSAEncrypter(keyProvider.publicKey)

        // Do the actual encryption
        jwt.encrypt(encrypter)

        // Serialise to JWT compact form
        return jwt.serialize()
    }

}
