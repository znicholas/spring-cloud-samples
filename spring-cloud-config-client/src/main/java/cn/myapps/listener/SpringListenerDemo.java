package cn.myapps.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 事件机制
 * 
 * @author nicholas
 *
 */
public class SpringListenerDemo {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		
		// 监听MyApplicationEvent事件
		context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {

			@Override
			public void onApplicationEvent(MyApplicationEvent event) {
				System.out.println("接收事件：" + event.getSource());
				
				System.out.println("上下文：" + event.getContext());
			}
		});
		
		context.refresh();
		
		context.publishEvent(new MyApplicationEvent(context, "Hello"));
		
		context.close();
	}
	
	private static class MyApplicationEvent extends ApplicationEvent {
		
		ApplicationContext context;
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1;
		
		public MyApplicationEvent(ApplicationContext context, Object source) {
			super(source);
			
			this.context = context;
		}
		
		public ApplicationContext getContext() {
			return context;
		}
	}
}
