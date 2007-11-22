/**
 *
 * Copyright 2007 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livetribe.boot.client;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * @version $Revision$ $Date$
 */
public interface ProvisionStore
{
    String getUuid() throws ProvisionStoreException;

    void setUuid(String uuid) throws ProvisionStoreException;

    ProvisionDirective getCurrentProvisionDirective() throws ProvisionStoreException;

    ProvisionDirective getNextProvisionDirective() throws ProvisionStoreException;

    void setNextProvisionDirective(ProvisionDirective provisionDirective) throws ProvisionStoreException;

    void store(ProvisionPair provisionPair, InputStream stream) throws ProvisionStoreException;

    void prepareNext() throws ProvisionStoreException;

    List<URL> getClasspath() throws ProvisionStoreException;
}