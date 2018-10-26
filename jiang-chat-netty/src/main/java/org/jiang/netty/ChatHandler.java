package org.jiang.netty;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
/**
 * 
 * @author 15012 处理消息的handler
 * TextWebSocketFrame:netty 中专门处理文本的对象
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	/**
	 * 记录和管理所有客户端的channel
	 */
	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
	    //获取客户端发送来的消息
		String content = msg.text();
		System.out.println("接受到的数据:" + content);
		
//		for (Channel channel : clients) {
//			channel.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + " 服务器获取消息：" + content));
//		}
		clients.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + " 服务器获取消息：" + content));
		
		
	}

	/**
	 * 当客户端连接服务端之后（打开连接）
	 * 获取客户端的channel,放到channelGroup中管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端断开，对应的长ID："+ ctx.channel().id().asLongText());
		System.out.println("客户端断开，对应的短ID："+ ctx.channel().id().asShortText());
	}

}
