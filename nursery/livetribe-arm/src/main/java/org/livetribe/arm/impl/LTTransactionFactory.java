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
package org.livetribe.arm.impl;

import org.opengroup.arm40.transaction.ArmApplication;
import org.opengroup.arm40.transaction.ArmApplicationDefinition;
import org.opengroup.arm40.transaction.ArmCorrelator;
import org.opengroup.arm40.transaction.ArmID;
import org.opengroup.arm40.transaction.ArmIdentityProperties;
import org.opengroup.arm40.transaction.ArmIdentityPropertiesTransaction;
import org.opengroup.arm40.transaction.ArmTransaction;
import org.opengroup.arm40.transaction.ArmTransactionDefinition;
import org.opengroup.arm40.transaction.ArmTransactionFactory;
import org.opengroup.arm40.transaction.ArmUser;

import org.livetribe.arm.LTAbstractFactoryBase;


/**
 * @version $Revision: $ $Date: $
 */
public class LTTransactionFactory extends LTAbstractFactoryBase implements ArmTransactionFactory, TransactionErrorCodes
{
    public ArmApplication newArmApplication(ArmApplicationDefinition definition, String group, String instance, String[] contextValues)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmApplicationDefinition newArmApplicationDefinition(String name, ArmIdentityProperties identityProperties, ArmID id)
    {
        name = ArmAPIUtil.checkRequired(name);
        identityProperties = ArmAPIUtil.checkOptional(identityProperties);
        id = ArmAPIUtil.checkOptional(id);

        return new LTApplicationDefinition(name, identityProperties, id);  //todo: consider this autogenerated code
    }

    public ArmCorrelator newArmCorrelator(byte[] corrBytes)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmCorrelator newArmCorrelator(byte[] corrBytes, int offset)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmID newArmID(byte[] idBytes)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmID newArmID(byte[] idBytes, int offset)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmIdentityProperties newArmIdentityProperties(String[] identityNames, String[] identityValues, String[] contextNames)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmIdentityPropertiesTransaction newArmIdentityPropertiesTransaction(String[] identityNames, String[] identityValues, String[] contextNames, String uriValue)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmTransaction newArmTransaction(ArmApplication app, ArmTransactionDefinition definition)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmTransactionDefinition newArmTransactionDefinition(ArmApplicationDefinition app, String name, ArmIdentityPropertiesTransaction identityProperties, ArmID id)
    {
        return null;  //todo: consider this autogenerated code
    }

    public ArmUser newArmUser(String name, ArmID id)
    {
        return null;  //todo: consider this autogenerated code
    }

    protected String getFactoryInterfaceName()
    {
        return "org.opengroup.arm40.transaction.ArmTransactionFactory";
    }
}
