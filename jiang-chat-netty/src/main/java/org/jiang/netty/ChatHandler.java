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
 * @author 15012 ������Ϣ��handler
 * TextWebSocketFrame:netty ��ר�Ŵ����ı��Ķ���
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	/**
	 * ��¼�͹������пͻ��˵�channel
	 */
	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
	    //��ȡ�ͻ��˷���������Ϣ
		String content = msg.text();
		System.out.println("���ܵ�������:" + content);
		
//		for (Channel channel : clients) {
//			channel.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + " ��������ȡ��Ϣ��" + content));
//		}
		clients.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + " ��������ȡ��Ϣ��" + content));
		
		
	}

	/**
	 * ���ͻ������ӷ����֮�󣨴����ӣ�
	 * ��ȡ�ͻ��˵�channel,�ŵ�channelGroup�й���
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("�ͻ��˶Ͽ�����Ӧ�ĳ�ID��"+ ctx.channel().id().asLongText());
		System.out.println("�ͻ��˶Ͽ�����Ӧ�Ķ�ID��"+ ctx.channel().id().asShortText());
	}

}
