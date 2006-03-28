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
package org.livetribe.arm.metric;

import org.opengroup.arm40.metric.ArmMetricGauge64Definition;
import org.opengroup.arm40.transaction.ArmID;

import org.livetribe.arm.LTAbstractFactoryBase;


/**
 * @version $Revision: $ $Date: $
 */
class LTMetricGauge64Definition extends LTAbstractMetricDefinition implements ArmMetricGauge64Definition
{
    LTMetricGauge64Definition(LTAbstractFactoryBase factory, String name, String units, short usage, ArmID id)
    {
        super(factory, name, units, usage, id);
    }
}