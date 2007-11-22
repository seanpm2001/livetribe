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

import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import junit.framework.TestCase;
import org.livetribe.boot.server.Server;

/**
 * @version $Revision$ $Date$
 */
public class ClientTest extends TestCase
{
    public final static int PORT = 12345;
    private Server server;
    ScheduledThreadPoolExecutor executor;

    public void test() throws Exception
    {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        try
        {
            Client client = new Client(InetSocketAddress.createUnresolved("localhost", PORT), executor, new MockProvisionStore());

            client.start();

            client.stop();
        }
        finally
        {
            executor.shutdown();
        }
    }

    public void setUp() throws Exception
    {
        executor = new ScheduledThreadPoolExecutor(5);
        server = new Server(PORT, executor, new MockProvisionManager());
    }

    public void tearDown() throws Exception
    {
        server.stop();
        executor.shutdown();
    }
}