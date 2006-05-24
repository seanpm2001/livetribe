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
package org.livetribe.forma.console.perspective;

import java.awt.BorderLayout;

import org.livetribe.forma.ui.Part;
import org.livetribe.forma.ui.perspective.AbstractPerspective;
import org.livetribe.ioc.PostConstruct;

/**
 * @version $Rev$ $Date$
 */
public class BrowserPerspective extends AbstractPerspective
{
    public static final String ID = BrowserPerspective.class.getName();

    @PostConstruct
    private void initComponents()
    {
        setLayout(new BorderLayout());
    }

    public String getPerspectiveId()
    {
        return ID;
    }

    public void spiDisplay(Part part)
    {
        add(part.spiGetComponent(), BorderLayout.CENTER);
    }
}
