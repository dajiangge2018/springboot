package org.jiang;

import org.jiang.netty.WsServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
			if(event.getApplicationContext().getParent() == null) {
				try {
					WsServer.getInstance().start();//启动netty
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	}

}
