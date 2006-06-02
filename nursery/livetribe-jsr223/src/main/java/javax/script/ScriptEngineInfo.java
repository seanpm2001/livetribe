/**
 *
 * Copyright 2005 (C) The original author or authors
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
package javax.script;

/**
 * @version $Revision: $ $Date: $
 */
public interface ScriptEngineInfo
{
    public String getEngineName();

    public String getEngineVersion();

    public String[] getExtensions();

    public String[] getMimeTypes();

    public String[] getNames();

    public String getLanguageName();

    public String getLanguageVersion();

    public Object getParameter(java.lang.String key);
}
