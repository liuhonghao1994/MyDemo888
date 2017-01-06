package com.atguigu.l10_myapp_bean;

public class Myphto {
		private String title;
		private String signal;
		@Override
		public String toString() {
			return "Myphto [title=" + title + ", signal=" + signal + "]";
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSignal() {
			return signal;
		}
		public void setSignal(String signal) {
			this.signal = signal;
		}
		public Myphto(String title, String signal) {
			super();
			this.title = title;
			this.signal = signal;
		}
		public Myphto() {
			super();
			// TODO Auto-generated constructor stub
		} 
	

}
