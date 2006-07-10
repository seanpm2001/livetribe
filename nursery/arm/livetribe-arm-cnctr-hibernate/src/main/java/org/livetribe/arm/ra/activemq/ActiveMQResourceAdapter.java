/**
 *
 * Copyright 2006 (C) The original author or authors
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
package org.livetribe.arm.ra.activemq;

import javax.resource.ResourceException;
import javax.resource.NotSupportedException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;
import java.util.HashMap;


/**
 * @version $Revision$ $Date$
 */
public class ActiveMQResourceAdapter implements ResourceAdapter
{
    ResourceAdapter adapter;
    private final HashMap endpointWorkers = new HashMap();
    private BootstrapContext bootstrapContext;
    private String brokerURL;

    public void start(BootstrapContext bootstrapContext)
    throws ResourceAdapterInternalException
    {
        adapter.start(bootstrapContext);
    }

    public void stop()
    {
        adapter.stop();
    }

    public void endpointActivation(MessageEndpointFactory messageEndpointFactory, ActivationSpec activationSpec)
    throws ResourceException
    {
        adapter.endpointActivation(messageEndpointFactory, activationSpec);
    }

    public void endpointDeactivation(MessageEndpointFactory messageEndpointFactory, ActivationSpec activationSpec)
    {
        adapter.endpointDeactivation(messageEndpointFactory, activationSpec);
    }

    public XAResource[] getXAResources(ActivationSpec[] activationSpecs)
    throws ResourceException
    {
        return adapter.getXAResources(activationSpecs);
    }
}