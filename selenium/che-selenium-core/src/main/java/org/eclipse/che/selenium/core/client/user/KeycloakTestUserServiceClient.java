/*
 * Copyright (c) 2012-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.selenium.core.client.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import java.net.URLEncoder;
import org.eclipse.che.api.core.model.user.User;
import org.eclipse.che.api.core.rest.HttpJsonRequestFactory;
import org.eclipse.che.api.core.rest.HttpJsonResponse;
import org.eclipse.che.api.user.shared.dto.UserDto;
import org.eclipse.che.selenium.core.client.TestUserServiceClient;
import org.eclipse.che.selenium.core.provider.TestApiEndpointUrlProvider;

/** @author Musienko Maxim */
public class KeycloakTestUserServiceClient implements TestUserServiceClient {
  private final String apiEndpoint;
  private final HttpJsonRequestFactory requestFactory;

  @Inject
  public KeycloakTestUserServiceClient(
      TestApiEndpointUrlProvider apiEndpointProvider,
      HttpJsonRequestFactory requestFactory,
      @Assisted boolean multiuser) {
    this.apiEndpoint = apiEndpointProvider.get().toString();
    this.requestFactory = requestFactory;
  }

  public User getByEmail(String email) throws Exception {
    String url = apiEndpoint + "user/find?email=" + URLEncoder.encode(email, "UTF-8");
    HttpJsonResponse response = requestFactory.fromUrl(url).useGetMethod().request();

    return response.asDto(UserDto.class);
  }

  public void deleteByEmail(String email) throws Exception {}

  public User create(String name, String email, String password) throws Exception {
    // do nothing, user is expected to
    return null;
  }

  public UserDto getUser(String auth) throws Exception {
    return null;
  }
}