package custome.atguigu.com.a06viewpager;

import android.os.SystemClock;

/**
 * Created by 刘红豪 on 2016/12/30.
 */
public class MyScroller {
    private float scrollX;
    private float scrollY;
    /**
     * 在x轴要移动的距离
     */
    private int distanceX;
    /**
     * 在Y轴要移动的距离
     */
    private int distanceY;
    private long starttime;
    private long totaltime=500;
    //移动是否结束，没有结束false，结束了true
    private boolean isFinish;
    //得到这一小段的移动距离
    public float getCurrX() {

        return currX;
    }
    private float currX;
    public void startScroll( float scrollX, float scrollY, int distanceX, int distanceY) {
        this.scrollX=scrollX;
        this.scrollY=scrollY;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
        this.starttime = SystemClock.uptimeMillis();
        isFinish=false;
    }


        public boolean computeScrollOffset(){
                if(isFinish){
                    return false;
                }

                //结束时间
                    long endTime = SystemClock.uptimeMillis();
                    long passTime = endTime - starttime;
            //这个passTime时间是不确定的，经过的时候时间是变得，所以currx会变，，所以每次他都会从一个位置移动

                    if(passTime < totaltime){
                        //还没有移动结束
                        //平均速度 = 总距离/总时间
                        float velocityX = distanceX / totaltime;
                        //这一小段的距离
                        float smallDistanceX =  passTime * distanceX / totaltime;

                        //这一小段对应的坐标
                        currX = scrollX + smallDistanceX;
                }else {
                    //移动完成
                    isFinish = true;
                    currX = scrollX + distanceX;
                }

              //正在移动
                return true;


        }

}
