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
		
		//websocket ����http�ı������
		pipeline.addLast(new HttpServerCodec());
		//�Դ���������֧��
		pipeline.addLast(new ChunkedWriteHandler());
		
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		//websocket �����������Э�飬���ͻ���ָ�����ʵ�·��
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		//����Զ����handler
		pipeline.addLast(new ChatHandler());
	}

}
