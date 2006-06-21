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

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @version $Revision: $ $Date$
 */
public class ActiveMQManagedConnection implements ManagedConnection
{
    private final Subject subject;
    private final ActiveMQConnectionRequestInfo info;
    private final Connection amqConnection;
    private final Destination amqDestination;
    private final List listeners = new ArrayList();
    private final List handles = new ArrayList();
    private PrintWriter logWriter;

    public ActiveMQManagedConnection(Subject subject, ActiveMQConnectionRequestInfo info, Connection amqConnection, Destination amqDestination)
    {
        this.subject = subject;
        this.info = info;
        this.amqConnection = amqConnection;
        this.amqDestination = amqDestination;
    }

    public Object getConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException
    {
        Object handle = new ManagedConnectionHandle(this, amqConnection, amqDestination);

        handles.add(handle);

        return handle;
    }

    public void destroy() throws ResourceException
    {
        cleanup();
    }

    public void cleanup() throws ResourceException
    {
        Iterator iterator = handles.iterator();
        while (iterator.hasNext())
        {
            ManagedConnectionHandle handle = (ManagedConnectionHandle) iterator.next();
            handle.cleanup();
        }
        handles.clear();

        try
        {
            amqConnection.close();
        }
        catch (JMSException jmse)
        {
            throw new ResourceException("Closing AMQ Connection", jmse);
        }
    }

    public void associateConnection(Object object) throws ResourceException
    {
        if (object instanceof ManagedConnectionHandle)
        {
            ManagedConnectionHandle handle = (ManagedConnectionHandle)object;
            ActiveMQManagedConnection owner = handle.getOwner();

            owner.removeConnection(handle);
            handles.add(handle);
        }
        else
        {
            throw new ResourceException("Not supported : associating object instance of " + object.getClass().getName());
        }
    }

    void removeConnection(ManagedConnectionHandle handle) throws ResourceException
    {
        handles.remove(handle);
    }

    public void addConnectionEventListener(ConnectionEventListener connectionEventListener)
    {
        listeners.add(connectionEventListener);
    }

    public void removeConnectionEventListener(ConnectionEventListener connectionEventListener)
    {
        listeners.remove(connectionEventListener);
    }

    public XAResource getXAResource() throws ResourceException
    {
        return null;  //todo: consider this autogenerated code
    }

    public LocalTransaction getLocalTransaction() throws ResourceException
    {
        return null;  //todo: consider this autogenerated code
    }

    public ManagedConnectionMetaData getMetaData() throws ResourceException
    {
        return null;  //todo: consider this autogenerated code
    }

    public void setLogWriter(PrintWriter printWriter) throws ResourceException
    {
        logWriter = printWriter;
    }

    public PrintWriter getLogWriter() throws ResourceException
    {
        return logWriter;
    }
}
