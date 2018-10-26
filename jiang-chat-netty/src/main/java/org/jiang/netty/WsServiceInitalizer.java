package org.jiang.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WsServiceInitalizer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// 
		ChannelPipeline pipeline =  ch.pipeline();
		
		//websocket 基于http的编解码器
		pipeline.addLast(new HttpServerCodec());
		//对大数据流的支撑
		pipeline.addLast(new ChunkedWriteHandler());
		
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		//websocket 服务器处理的协议，给客户端指定访问的路由
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		//添加自定义的handler
		pipeline.addLast(new ChatHandler());
	}

}
