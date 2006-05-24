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
package org.livetribe.forma.console.editor;

import java.net.URI;

import org.livetribe.forma.ui.action.ActionContext;
import org.livetribe.forma.ui.editor.EditorSource;

/**
 * @version $Rev$ $Date$
 */
public class JMXEditorSource implements EditorSource
{
    public String spiGetEditorClassName(URI uri, ActionContext context)
    {
        if (uri.getSchemeSpecificPart().startsWith("jmx:")) return JMXEditor.class.getName();
        return null;
    }
}
