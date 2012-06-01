package sample.a146;

import java.io.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.media.*;
import android.media.MediaPlayer.*;

public class Sample2 extends Activity
{
   Button[] bt = new Button[3] ;
   MediaPlayer mp;
   MediaRecorder mr;
   File dir, f;
   
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      LinearLayout ll = new LinearLayout(this); 
      ll.setOrientation(LinearLayout.VERTICAL);
      setContentView(ll);
      //
      for(int i=0; i<bt.length; i++)
      {
         bt[i] = new Button(this);
      }
      
      bt[0].setText("ŠJŽn");
      bt[1].setText("’âŽ~");
      bt[2].setText("Ä¶");

      bt[0].setEnabled(true);
      bt[1].setEnabled(false);		
      bt[2].setEnabled(true);	
      
      File dir = new File(Environment.getExternalStorageDirectory(),"YASample");
      if(dir.exists() == false)
      {
          dir.mkdir(); 
      }	  
      f = new File(dir, "Sample.3gp");    
      
      for(int i=0; i<bt.length; i++)
      {
         ll.addView(bt[i]);
         bt[i].setOnClickListener(new SampleClickListener());
      }  
   }
   public void onResume()
   {
      super.onResume();
      
      mp = new MediaPlayer();
      mr = new MediaRecorder();
      
      mp.setOnCompletionListener(new SampleCompletionListener());
   }
   public void onPause()
   {
      super.onPause();

      mp.release();
      mr.release();
   }
   class SampleCompletionListener implements OnCompletionListener
   {
      public void onCompletion(MediaPlayer mp)
      {
         bt[0].setEnabled(true);
         bt[1].setEnabled(false);		
         bt[2].setEnabled(true);		
      }	
   }
   class SampleClickListener implements OnClickListener
   {
      public void onClick(View v)
      {
         if(v == bt[0])
         {
       	    bt[0].setEnabled(false);
      	    bt[1].setEnabled(true);		
      	    bt[2].setEnabled(false);     
      	    try
            {
      	       mp.reset();
      	       mr.setAudioSource(MediaRecorder.AudioSource.MIC);
      	       mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      	       mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
      	      
      	       String path = f.getAbsolutePath();
      	       mr.setOutputFile(path);
               mr.prepare();
               mp.setDataSource(path);
               mr.start();
            }
            catch(Exception e){}
         }
         else if(v == bt[1])
         {
      	    bt[0].setEnabled(true);
     	    bt[1].setEnabled(false);		
     	    bt[2].setEnabled(true);     	
            mr.stop();
            mr.reset();
         }    	 
         else if(v == bt[2])
         {
     	    bt[0].setEnabled(false);
    	    bt[1].setEnabled(false);		
    	    bt[2].setEnabled(false);	
    	    try
            {
               mp.prepare();
            }
            catch(Exception e){}
            mp.start();
         }
      }
   }  
}
