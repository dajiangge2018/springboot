package org.jiang.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author 15012 
 *
 */
public class WsService {
	
	public static void main(String[] args)  throws Exception{
		
            //���߳���		
			EventLoopGroup mainGroup = new NioEventLoopGroup();
			//���߳���
			EventLoopGroup subGroup = new NioEventLoopGroup();
			//netty����������
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			
			try {
				serverBootstrap.group(mainGroup, subGroup) //���������߳�
										.channel(NioServerSocketChannel.class) //����nio˫��ͨ��
										.childHandler(new WsServiceInitalizer()); //�Ӵ����������ڴ���workGroup

				//���� ������
				ChannelFuture channelFuture =  serverBootstrap.bind(8088).sync();
				//�����ر�channel
				channelFuture.channel().closeFuture().sync();
			} finally {
				//�ر��߳���
				mainGroup.shutdownGracefully();
				subGroup.shutdownGracefully();
			}
			
			
			
	}
}
