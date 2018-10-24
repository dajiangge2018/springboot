package org.jiang.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		//通过SocketChannel去获得对应管道
		ChannelPipeline channelPipeline =socketChannel.pipeline();
		
		//通过管道添加handler
		//HttpServerCodec 是由netty提供的助手类
		//当请求到服务器，我们需要做解码，响应到客户端，需要做编码
		channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
		
		//添加自定义助手类，返回hello netty
		channelPipeline.addLast("CustomerHandler", new CustomerHandler());
	}

}
