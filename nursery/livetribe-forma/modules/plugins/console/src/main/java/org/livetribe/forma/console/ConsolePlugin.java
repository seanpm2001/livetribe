/*
 * Copyright 2006 the original author or authors
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
 */
package org.livetribe.forma.console;

import java.lang.management.ManagementFactory;
import java.util.Locale;
import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.livetribe.forma.Plugin;
import org.livetribe.forma.console.perspective.WelcomePerspective;
import org.livetribe.forma.ui.perspective.PerspectiveManager;
import org.livetribe.ioc.Inject;
import org.livetribe.slp.ServiceURL;
import org.livetribe.slp.api.Configuration;
import org.livetribe.slp.api.sa.ServiceAgent;
import org.livetribe.slp.api.sa.ServiceInfo;
import org.livetribe.slp.api.sa.StandardServiceAgent;
import org.livetribe.slp.spi.net.SocketUnicastConnector;
import org.livetribe.slp.spi.sa.StandardServiceAgentManager;

/**
 * @version $Rev$ $Date$
 */
public class ConsolePlugin implements Plugin
{
    @Inject
    private PerspectiveManager perspectiveManager;
    private JMXConnectorServer connectorServer;
    private ServiceAgent serviceAgent;
    private ServiceInfo serviceInfo;

    public void init() throws Exception
    {
        perspectiveManager.setDefaultPerspectiveId(WelcomePerspective.ID);

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        JMXServiceURL jmxServiceURL = new JMXServiceURL("rmi", null, 0);
        connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mbeanServer);

        Configuration slpConfiguration = new Configuration();
        slpConfiguration.setPort(1427);
        SocketUnicastConnector unicastConnector = new SocketUnicastConnector();
        unicastConnector.setUnicastListening(true);
        StandardServiceAgentManager serviceAgentManager = new StandardServiceAgentManager();
        serviceAgentManager.setUnicastConnector(unicastConnector);
        serviceAgent = new StandardServiceAgent();
        serviceAgent.setServiceAgentManager(serviceAgentManager);
        serviceAgent.setConfiguration(slpConfiguration);
    }

    public void start() throws Exception
    {
        connectorServer.start();
        JMXServiceURL jmxServiceURL = connectorServer.getAddress();

        serviceInfo = new ServiceInfo(new ServiceURL(jmxServiceURL.toString()), null, null, Locale.getDefault().getLanguage());
        serviceAgent.register(serviceInfo);
        serviceAgent.start();
    }

    public void stop() throws Exception
    {
        serviceAgent.stop();
        connectorServer.stop();
    }

    public void destroy()
    {
    }
}
