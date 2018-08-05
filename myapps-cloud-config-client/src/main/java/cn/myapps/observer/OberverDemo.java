package cn.myapps.observer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OberverDemo {
	
	public static void main(String[] args) throws Exception {
		// 被观察者
		MyObservable observable = new MyObservable();
		
		// 增加订阅者
		observable.addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				System.out.println("Observer do update: " + arg);
			}
		});
		
		// 没以下语句不会触发update
		observable.setChanged();
		
		// 发布者通知，订阅者被动感知（推模式）
		observable.notifyObservers("Hello,World");
	}
	
	public void echoIterator() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		// 通过循环主动获取，迭代器模式为拉模式
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer i = iterator.next();
			System.out.println(i);
		}
	}
	
	private static class MyObservable extends Observable {
		@Override
		public synchronized void setChanged() {
			super.setChanged();
		}
	}
}
