package game;

import javax.swing.JLabel;

import userInterface.UserCity;

public class Timer implements Runnable{
	
	public static int milli;
	public static int milliComp;
	public static int milliComp2;
	public static int hour;
	public static int minutes;
	private static int seconds;
	private static int hourComp;
	private static int minutesComp;
	private static int secondsComp;
	private boolean TimerRunning = true;
	private Thread T;
	private String threadName = "Timer";
	JLabel time;
	
	public Timer()
	{	
		hour = 0;
		minutes = 0;
		seconds = 0;
		hourComp = 0;
		minutesComp = 0;
		secondsComp = 0;
		milli = 0;
		milliComp = 0;
		milliComp2 = 0;
		time = UserCity.getTimeLabel();

	}
	
	public void startTimer()
	{       
		if (T == null)
		 {
	         T = new Thread (this, threadName);
	         T.start ();
	      }	
	}
	public String displayTimer()
	{
		String timer ="    "+ Integer.toString(hourComp) + hour + ':' + minutesComp + minutes + ':' + secondsComp + seconds + ':' + milliComp2 + milliComp + milli + "     " ;
		
		return timer;
	}
		
	public void  updateTimer()
	{
		if (milli > 9)
		{
			milli = 0;
			milliComp++;

			if (milliComp == 9)
			{
				milliComp = 0;
				milliComp2++;
				
				if (milliComp2 == 9)
				{
					milliComp2 = 0;
					seconds++;
				}	
			}		
		} 	
		if (seconds > 9)
		{
			seconds = 0;
			secondsComp++;

			if (secondsComp == 6)
			{
				secondsComp = 0;
				minutes++;
			}		
		} 	
		if (minutes > 9) 
		{
    		minutes = 0;
			minutesComp++;

			if (minutesComp == 6)
			{
				minutesComp = 0;
				hour++;
			}
		} 
		if (hour > 9)
		{
			hour = 0;
			hourComp++;
		}
    	time.setText(displayTimer());	
	}
	public void  incrementTimer()
	{
		milli ++;		
	}

	@Override
	public void run() {
		
		while(TimerRunning)
		{
				try {	
					Thread.sleep(1);
					incrementTimer();
					updateTimer();	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
		}		
	}
}
