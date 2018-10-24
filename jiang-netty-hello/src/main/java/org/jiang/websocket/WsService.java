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
		
            //主线程组		
			EventLoopGroup mainGroup = new NioEventLoopGroup();
			//子线程组
			EventLoopGroup subGroup = new NioEventLoopGroup();
			//netty服务器创建
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			
			try {
				serverBootstrap.group(mainGroup, subGroup) //设置主从线程
										.channel(NioServerSocketChannel.class) //设置nio双向通道
										.childHandler(new WsServiceInitalizer()); //子处理器，用于处理workGroup

				//启动 服务器
				ChannelFuture channelFuture =  serverBootstrap.bind(8088).sync();
				//监听关闭channel
				channelFuture.channel().closeFuture().sync();
			} finally {
				//关闭线程组
				mainGroup.shutdownGracefully();
				subGroup.shutdownGracefully();
			}
			
			
			
	}
}
