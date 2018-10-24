package org.jiang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author 15012 ʵ�ֿͻ��˷���һ�����󣬷���hello netty
 *
 */
public class HelloServer {

	public static void main(String[] args) throws Exception {
		//����һ���߳���
		//���߳��飬���ڽ��ܿͻ��˵����ӣ������κ�����
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//���߳���
		EventLoopGroup workGroup = new NioEventLoopGroup();
		//netty�������Ĵ�������һ��������
		ServerBootstrap  serverBootstrap = new ServerBootstrap();
		try {
			
			serverBootstrap.group(bossGroup, workGroup) //���������߳���
									.channel(NioServerSocketChannel.class)  //����nio��˫��ͨ��
									.childHandler(new HelloServerInitializer());	//�Ӵ����������ڴ���workGroup
			//����server,���ö˿ں�Ϊ8088��ͬʱ������ʽΪͬ��
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
			//�����ر�channel,����Ϊͬ����ʽ
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
