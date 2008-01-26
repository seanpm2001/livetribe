/**
 *
 * Copyright 2007 (C) The original author or authors
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
package org.livetribe.boot.beep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import net.sf.beep4j.Channel;
import net.sf.beep4j.FutureReply;
import net.sf.beep4j.FutureReplyListener;
import net.sf.beep4j.Message;
import net.sf.beep4j.MessageBuilder;
import net.sf.beep4j.StartChannelRequest;
import net.sf.beep4j.StartSessionRequest;
import net.sf.beep4j.ext.SessionHandlerAdapter;

import org.livetribe.boot.protocol.BootServer;
import org.livetribe.boot.protocol.BootServerException;
import org.livetribe.boot.protocol.ProvisionEntry;
import org.livetribe.boot.protocol.YouShould;


/**
 * @version $Revision$ $Date$
 */
public class ClientSessionHandler extends SessionHandlerAdapter implements BootServer
{
    public final static String CMD_PROFILE = "urn:livetribe.org:names:boot:1:0";
    private ClientChannelHandler channelHandler;

    @Override
    public void connectionEstablished(StartSessionRequest startSessionRequest)
    {
        startSessionRequest.registerProfile(CMD_PROFILE);
    }

    @Override
    public void channelStartRequested(StartChannelRequest startChannelRequest)
    {
        if (startChannelRequest.hasProfile(CMD_PROFILE))
        {
            startChannelRequest.selectProfile(startChannelRequest.getProfile(CMD_PROFILE), channelHandler = new ClientChannelHandler());
        }
    }

    public YouShould hello(String uuid, long version) throws BootServerException
    {
        if (channelHandler == null) throw new BootServerException("Channel is not open");

        Channel channel = channelHandler.getChannel();
        MessageBuilder builder = channel.createMessageBuilder();
        PrintWriter writer = new PrintWriter(new BufferedWriter(builder.getWriter()));

        writer.print("HELLO ");
        writer.print(uuid);
        writer.print(" ");
        writer.print(version);
        writer.println();

        FutureReply<YouShould> reply = new FutureReply<YouShould>(new FutureReplyListener<YouShould>()
        {
            @SuppressWarnings({"ThrowableInstanceNeverThrown"})
            public void receiveRPY(Message message)
            {
                BufferedReader reader = new BufferedReader(message.getReader());

                try
                {
                    String line = reader.readLine();
                    if (line == null || line.length() == 0)
                    {
                        setThrowable(new BootServerException("Empty message"));
                        return;
                    }

                    String[] tokens = line.split(" ");
                    if (tokens.length != 4)
                    {
                        setThrowable(new BootServerException("Unrecognized reply " + line));
                        return;
                    }

                    String command = tokens[0];
                    long version = Long.parseLong(tokens[1]);
                    String bootClass = tokens[2];
                    int numEntries = Integer.parseInt(tokens[3]);

                }
                catch (IOException ioe)
                {
                    setThrowable(new BootServerException(ioe.getMessage()));
                }
                catch (NumberFormatException nfe)
                {
                    setThrowable(new BootServerException(nfe.getMessage()));
                }
                setValue(new YouShould(0, "", Collections.<ProvisionEntry>emptySet()));
                //todo: consider this autogenerated code
            }
        });

        channel.sendMessage(builder.getMessage(), reply);

        try
        {
            return reply.get();
        }
        catch (ExecutionException ee)
        {
            throw new BootServerException(ee.getCause());
        }
        catch (Exception e)
        {
            throw new BootServerException(e);
        }
    }

    public InputStream pleaseProvide(String name, long version) throws BootServerException
    {
        Channel channel = channelHandler.getChannel();
        MessageBuilder builder = channel.createMessageBuilder();
        PrintWriter writer = new PrintWriter(new BufferedWriter(builder.getWriter()));

        writer.print("PROVIDE ");
        writer.print(name);
        writer.print(" ");
        writer.print(version);
        writer.println();

        FutureReply<InputStream> reply = new FutureReply<InputStream>(new FutureReplyListener<InputStream>()
        {
            public void receiveRPY(Message message)
            {
                setValue(message.getInputStream());
            }

            @SuppressWarnings({"ThrowableInstanceNeverThrown"})
            public void receiveERR(Message message)
            {
                BufferedReader reader = new BufferedReader(message.getReader());
                try
                {
                    String line = reader.readLine();
                    setThrowable(new BootServerException(line));
                }
                catch (IOException ioe)
                {
                    setThrowable(new BootServerException(ioe.getMessage()));
                }
            }
        });

        channel.sendMessage(builder.getMessage(), reply);

        try
        {
            return reply.get();
        }
        catch (ExecutionException ee)
        {
            throw new BootServerException(ee.getCause());
        }
        catch (Exception e)
        {
            throw new BootServerException(e);
        }
    }
}
