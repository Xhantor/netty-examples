/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Create on @2013-8-24 @上午10:23:23 
 * @author bsli@ustcinfo.com
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder DECODER = new StringDecoder(CharsetUtil.UTF_8);
    private static final StringEncoder ENCODER = new StringEncoder(CharsetUtil.UTF_8);
    private static final Netty4ClientHandler CLIENTHANDLER = new Netty4ClientHandler();
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", DECODER);
        pipeline.addLast("encoder", ENCODER);

        // and then business logic.
        pipeline.addLast("handler", CLIENTHANDLER);
    }
    
    public Netty4ClientHandler getClientHandler() {
    	return CLIENTHANDLER;
    }
}