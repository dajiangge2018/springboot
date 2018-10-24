package org.jiang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author 15012 ��д�Զ���������
 *
 */
//SimpleChannelInboundHandler ����������������൱����վ
public class CustomerHandler extends SimpleChannelInboundHandler<HttpObject>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		// ��ȡChannel
		Channel channel = ctx.channel();
		if(msg instanceof HttpRequest) {
			//��ӡ�ͻ��˵�Զ�̵�ַ
			System.out.println(channel.remoteAddress());
			
			//���巢�͵�������Ϣ
			ByteBuf content = Unpooled.copiedBuffer("hello netty~",CharsetUtil.UTF_8);
			//����һ��FullHttpResponse
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, 
																								 HttpResponseStatus.OK,
																								 content);
			//Ϊ��Ӧ�����������ͺͳ���
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
			
			//����Ӧˢ���ͻ���
			ctx.writeAndFlush(response);
		}
		
	}

}
