package org.jiang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author 15012 实现客户端发送一个请求，返回hello netty
 *
 */
public class HelloServer {

	public static void main(String[] args) throws Exception {
		//定义一对线程组
		//主线程组，用于接受客户端的连接，不做任何事情
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//从线程组
		EventLoopGroup workGroup = new NioEventLoopGroup();
		//netty服务器的创建，是一个启动类
		ServerBootstrap  serverBootstrap = new ServerBootstrap();
		try {
			
			serverBootstrap.group(bossGroup, workGroup) //设置主从线程组
									.channel(NioServerSocketChannel.class)  //设置nio的双向通道
									.childHandler(new HelloServerInitializer());	//子处理器，用于处理workGroup
			//启动server,设置端口号为8088，同时启动方式为同步
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
			//监听关闭channel,设置为同步方式
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
