package com.atguigu.l10_myapp;




import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service{

	private MediaPlayer player;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent!=null){
			String value = intent.getStringExtra("action");
			if (value.equals("play")) {
				playMusic();
			} else if (value.equals("pause")) {
				pauseMusic();
			} 
	}
		return super.onStartCommand(intent, flags, startId);
		
}
	
	private void pauseMusic() {
		if(player!=null && player.isPlaying()){
			player.stop();
		}
		
	}
	private void playMusic() {
		if(player==null){
			player = MediaPlayer.create(this, R.raw.choubaguai);
		}
		player.start();
		
	}
	
	

}
