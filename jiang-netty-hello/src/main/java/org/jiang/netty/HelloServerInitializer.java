package org.jiang.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		//ͨ��SocketChannelȥ��ö�Ӧ�ܵ�
		ChannelPipeline channelPipeline =socketChannel.pipeline();
		
		//ͨ���ܵ����handler
		//HttpServerCodec ����netty�ṩ��������
		//�����󵽷�������������Ҫ�����룬��Ӧ���ͻ��ˣ���Ҫ������
		channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
		
		//����Զ��������࣬����hello netty
		channelPipeline.addLast("CustomerHandler", new CustomerHandler());
	}

}
