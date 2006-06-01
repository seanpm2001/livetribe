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
package org.livetribe.arm.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import org.opengroup.arm40.transaction.ArmCorrelator;
import org.opengroup.arm40.transaction.ArmTransactionFactory;

import org.livetribe.arm.CorrelationManager;
import org.livetribe.util.HexSupport;


/**
 * Checks to see if a correlator is being passed as a URL parameter.  If one
 * is, then it primes the <code>CorrelatorManager</code> w/ the potential
 * parent correlator.  Instances of the class <code>ArmDecisionPoint</code>
 * will decide if it will trigger an ARM transaction.
 *
 * @version $Revision: $ $Date$
 * @see ArmDecisionPoint
 */
public class ArmCorrelationFilter implements Filter
{
    private static final String TRANSACTION_FACTORY = "org.livetribe.arm.web.ArmCorrelationFilter.transactionFactory";
    private static final String CORRELATOR_PARAM = "org.livetribe.arm.correlator";
    private ArmTransactionFactory transactionFactory;

    public void init(FilterConfig filterConfig) throws ServletException
    {
        transactionFactory = (ArmTransactionFactory) KnitPoint.getContext().getBean(TRANSACTION_FACTORY);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        ArmCorrelator saved = CorrelationManager.get();
        String correlatorParam = servletRequest.getParameter(CORRELATOR_PARAM);

        if (correlatorParam != null)
        {
            try
            {
                ArmCorrelator correlator = transactionFactory.newArmCorrelator(HexSupport.toBytesFromHex(correlatorParam));

                CorrelationManager.put(correlator);
            }
            catch (NumberFormatException doNothing)
            {
            }
        }

        try
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally
        {
            CorrelationManager.put(saved);
        }
    }

    public void destroy()
    {
    }
}
