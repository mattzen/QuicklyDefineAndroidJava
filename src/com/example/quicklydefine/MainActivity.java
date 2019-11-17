package com.example.quicklydefine;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	
	Button defbtn;
	EditText inputtext;
	TextView t;
	dbhandler handler;
	//EditText t;
	private static final String DB_NAME = "wordsdbandroid.db";
    
    //A good practice is to define database field names as constants
    ExternalDbOpenHelper dbOpenHelper;
     private SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
        handler = new dbhandler(this);
        t =  (TextView)findViewById(R.id.textView2);
        defbtn = (Button)findViewById(R.id.button1);
        inputtext = (EditText)findViewById(R.id.editText1);
        // Reading all contacts
        
        dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //That’s it, the database is open!
        
        
   	 	defbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("Click", "inside the click");
				// TODO Auto-generated method stub
				String find = inputtext.getText().toString();
				find = find.replace(" ", "");
				ArrayList<Definitions> d  = dbOpenHelper.getDefs(find);
				
				String def= "No defs";
				
				if(d.size()>0){
					
					def =  d.get(0).getType() + "\n";
					
					for (int i = 0; i < d.size();  i++) {
						
						if(d.get(i).getExample()!=null){
							
						def += (i+1) + ": " + d.get(i).definition+"\n" + "   -"+d.get(i).getExample() + "\n";
						
						}
						else{
							
							def += (i+1) + ": " + d.get(i).definition+"\n";
		
						}
					}
				
					ArrayList<String> polwords  = dbOpenHelper.getPolWords(find);
			
					def+="\n Polish: \n {" + polwords.get(0).toString()+ "}\n";
					
					for (int j = 1; j < polwords.size(); j++) {
						def += polwords.get(j).toString() + "  ";
					}
				}
				else{

					find = find.replace(" ", "");
					ArrayList<String> engs  = dbOpenHelper.getEngs(find);
					def = "";
					if(engs.size()>0){
						
						for (int j = 0; j < engs.size(); j+=2) {
							Log.d("loopingsdas","w loopie");
							if(engs.size()>=j+1){
								
								def += engs.get(j).toString() + ":  "+ engs.get(j+1).toString() + "\n\n";
							}
						}
					}
					
					
					
				}
		        t.setText(def);
	
		
			}
		}); 
	}
	
  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
