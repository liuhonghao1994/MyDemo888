package custome.atguigu.com.a01;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

/**
 * Created by 刘红豪 on 2016/12/28.
 */
public class Tootls {
    /**
     * 旋转动画
     * 旋转的角度从0~180
     * @param view
     */
    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    public static void showView(ViewGroup view) {
        showView(view, 0);
    }

    public static void hideView(ViewGroup view, int startOffset) {
        /**
         * fromDegrees:从什么角度开始旋转
         * toDegrees：旋转到的角度
         * pivotX：旋转中心点的X的坐标
         * pivotY：旋转中心点的Y的坐标
         */
        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth()/2, view.getHeight());
        System.out.println(view.getWidth()/2+"-------------"+view.getHeight());
        //设置时长
        ra.setDuration(500);
        //设置在动画播放完成后的状态
        ra.setFillAfter(true);
        //延迟播放动画
        ra.setStartOffset(startOffset);
        //启动动画
        view.startAnimation(ra);

        //把view 孩子遍历出来隐藏可以了
//		view.setVisibility(View.GONE);
//		view.setEnabled(false);
        //View不能操作孩子，也不能得到孩子
//这句话就是为了修复最后点击出现的问题
        for(int i=0; i< view.getChildCount();i++){
            View children = view.getChildAt(i);//根据位置得到某一个孩子
            children.setEnabled(false);
//			children.setVisibility(View.GONE);
        }



    }

    public static void showView(ViewGroup view, int startOffset) {
        /**
         * fromDegrees:从什么角度开始旋转
         * toDegrees：旋转到的角度
         * pivotX：旋转中心点的X的坐标
         * pivotY：旋转中心点的Y的坐标
         */
        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth()/2, view.getHeight());
        System.out.println(view.getWidth()/2+"-------------"+view.getHeight());
        //设置时长
        ra.setDuration(500);
        //设置在动画播放完成后的状态
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);
        //启动动画
        view.startAnimation(ra);

        for(int i=0; i< view.getChildCount();i++){
            View children = view.getChildAt(i);//根据位置得到某一个孩子
            children.setEnabled(true);
//			children.setVisibility(View.VISIBLE);
        }

    }
}
