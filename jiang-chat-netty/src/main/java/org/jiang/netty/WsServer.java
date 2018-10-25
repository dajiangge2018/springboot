package org.jiang.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
@Component
public class WsServer {
	
	private static class SingletionWsServer{
		static final WsServer instance = new WsServer();
	}

	public static WsServer getInstance() {
		return SingletionWsServer.instance;
	}
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap  serverBootstrap;
	private ChannelFuture channelFuture;
	
	public WsServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		serverBootstrap.group(mainGroup, subGroup) 
								.channel(NioServerSocketChannel.class)  
								.childHandler(null);
	} 
	
	public void start() {
		this.channelFuture = serverBootstrap.bind(8088);
		System.out.println("netty 启动完毕!");
	}
	
}
