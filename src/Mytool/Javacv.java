package Mytool;


import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.junit.Test;





public class Javacv {
/*	@SuppressWarnings("resource")
	@Test
	public  void carm() throws Exception, InterruptedException{
		OpenCVFrameGrabber cv =new OpenCVFrameGrabber(0);
		cv.start();//开启摄像头
		CanvasFrame canvas = new CanvasFrame("摄像头");
		  canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        canvas.setAlwaysOnTop(true);
	        while (true) {
	            if (!canvas.isDisplayable()) {//窗口是否关闭
	                cv.stop();//停止抓取
	                System.exit(-1);//退出
	            }
	            Frame frame = cv.grab();

	            canvas.showImage(frame);//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
	            Thread.sleep(50);//50毫秒刷新一次图像
	        }
		}*/
	
/*	@Test
    public void testCamera1() throws FrameGrabber.Exception, InterruptedException {
        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        grabber.start();
        CanvasFrame canvasFrame = new CanvasFrame("摄像头");
        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvasFrame.setAlwaysOnTop(true);
        while (true) {
            if (!canvasFrame.isDisplayable()) {
                grabber.stop();
                System.exit(-1);
            }
            Frame frame = grabber.grab();
            canvasFrame.showImage(frame);
            Thread.sleep(30);
        }
	}
    }*/
static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

public static void main(String[] args) throws Exception, InterruptedException
{
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    grabber.start();   //开始获取摄像头数据
    CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setAlwaysOnTop(true);
    int ex = 0;
    while(true)
    {
        if(!canvas.isDisplayable())
        {//窗口是否关闭
            grabber.stop();//停止抓取
            System.exit(2);//退出
            break;
        }       
        canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
        opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
        ex++;
        opencv_imgcodecs.imwrite("d:\\img\\" + ex + ".png", mat);
        Thread.sleep(200);//50毫秒刷新一次图像
    }
}
	
}
