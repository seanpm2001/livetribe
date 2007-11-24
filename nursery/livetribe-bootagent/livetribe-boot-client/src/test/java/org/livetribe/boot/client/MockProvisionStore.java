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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.livetribe.boot.protocol.ProvisionEntry;


/**
 * @version $Revision$ $Date$
 */
public class MockProvisionStore implements ProvisionStore
{
    private final File root;
    private String uuid = "org.livetribe.boot.uuid.test";
    private ProvisionDirective currentProvisionDirective;
    private ProvisionDirective nextProvisionDirective;

    public MockProvisionStore()
    {
        root = new File("./target/provision");
        root.mkdirs();

        currentProvisionDirective = new ProvisionDirective(0, "org.livetribe.boot.Start", Collections.<ProvisionEntry>emptySet());
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public ProvisionDirective getCurrentProvisionDirective()
    {
        return currentProvisionDirective;
    }

    public ProvisionDirective getNextProvisionDirective()
    {
        return nextProvisionDirective;
    }

    public void setNextProvisionDirective(ProvisionDirective provisionDirective)
    {
        nextProvisionDirective = provisionDirective;
    }

    public void store(ProvisionEntry provisionEntry, InputStream stream)
    {
        //todo: consider this autogenerated code
    }

    public void prepareNext()
    {
        currentProvisionDirective = nextProvisionDirective;
    }

    public List<URL> getClasspath()
    {
        return null;  //todo: consider this autogenerated code
    }
}
