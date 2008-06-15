/**
 *
 * Copyright 2008 (C) The original author or authors
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
package org.livetribe.ec2.api.v20080201;

import org.livetribe.ec2.api.ClientException;


/**
 * Attempt to authorize a permission that has already been authorized.
 *
 * @version $Revision$ $Date$
 */
public class InvalidPermissionDuplicateException extends ClientException
{
    public InvalidPermissionDuplicateException()
    {
    }

    public InvalidPermissionDuplicateException(String message)
    {
        super(message);
    }

    public InvalidPermissionDuplicateException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidPermissionDuplicateException(Throwable cause)
    {
        super(cause);
    }
}