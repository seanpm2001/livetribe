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
package org.livetribe.s3.jaxb.v20060301;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;

import org.junit.Test;


/**
 * @version $Revision$ $Date: 2008-05-30 09:40:48 -0700 (Fri, 30 May 2008) $
 */
public class Jaxb20060301Test
{
    @Test
    public void test() throws Exception
    {
        JAXBContext context20060301 = JAXBContext.newInstance("org.livetribe.s3.jaxb.v20060301");

        context20060301.createUnmarshaller().unmarshal(new StringReader("<CreateBucket xmlns=\"http://s3.amazonaws.com/doc/2006-03-01/\">\n" +
                                                                        "  <Bucket>quotes</Bucket>\n" +
                                                                        "  <AWSAccessKeyId>1D9FVRAYCP1VJEXAMPLE=</AWSAccessKeyId>\n" +
                                                                        "  <Timestamp>2006-03-01T12:00:00.183Z</Timestamp>\n" +
                                                                        "  <Signature>Iuyz3d3P0aTou39dzbqaEXAMPLE=</Signature>\n" +
                                                                        "</CreateBucket>"));
    }
}