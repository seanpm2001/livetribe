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
package org.livetribe.s3.model;

/**
 * @version $Revision$ $Date: 2008-06-15 11:14:58 -0700 (Sun, 15 Jun 2008) $
 */
public class LaunchPermission
{
    private final String group;
    private final String userId;

    public LaunchPermission(String group, String userId)
    {
        if (group == null) throw new IllegalArgumentException("group cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");

        this.group = group;
        this.userId = userId;
    }

    public String getGroup()
    {
        return group;
    }

    public String getUserId()
    {
        return userId;
    }

    @Override
    public String toString()
    {
        return "(LaunchPermissionItemType group: " + group + " userId: " + userId + ")";
    }
}