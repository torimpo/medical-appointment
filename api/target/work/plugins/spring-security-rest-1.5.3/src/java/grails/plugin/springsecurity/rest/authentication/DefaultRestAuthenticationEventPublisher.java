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
package grails.plugin.springsecurity.rest.authentication;

import grails.plugin.springsecurity.rest.RestTokenCreationEvent;
import grails.plugin.springsecurity.rest.token.AccessToken;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

/*
 * Default implementation of the {@link RestAuthenticationEventPublisher}.
 */
public class DefaultRestAuthenticationEventPublisher extends DefaultAuthenticationEventPublisher implements grails.plugin.springsecurity.rest.authentication.RestAuthenticationEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public DefaultRestAuthenticationEventPublisher() {
        this(null);
    }

    public DefaultRestAuthenticationEventPublisher(ApplicationEventPublisher publisher) {
        super(publisher);
        this.setApplicationEventPublisher(publisher);
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        super.setApplicationEventPublisher(publisher);
        this.applicationEventPublisher = publisher;
    }

    public void publishTokenCreation(AccessToken accessToken) {
        if (applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(new RestTokenCreationEvent(accessToken));
        }
    }

}
