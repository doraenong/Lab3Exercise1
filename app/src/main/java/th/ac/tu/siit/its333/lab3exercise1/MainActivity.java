package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        calculate();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void calculate()
    {
        // String to Integer
        //i = Integer.parseInt(s)

        // Integer to String
        //s = Integer.toString(i)

        // http://developer.android.com/reference/java/util/Formatter.html
        //String.format()

        // newline "\n"

        //Grade
        //A = 4
        //B+ = 3.5
        //B = 3
        //C+ = 2.5
        //C = 2.0
        //D+ = 1.5
        //D = 1
        //F = 0


         cr = 0;         // Credits
         gp = 0.0;    // Grade points
         gpa = 0.0;   // Grade point average

        for(int i = 0 ; i < listCodes.size() ; i++)
        {
            gp += grade(listGrades.get(i)) * listCredits.get(i);
            cr += listCredits.get(i);
        }
        gpa = gp/cr;

        TextView tv1 = (TextView) findViewById(R.id.tvGP);
        TextView tv2 = (TextView) findViewById(R.id.tvCR);
        TextView tv3 = (TextView) findViewById(R.id.tvGPA);

        tv1.setText(String.format("%.2f",gp));
        tv2.setText(String.format("%d",cr));
        tv3.setText(String.format("%.2f",gpa));

        //display the result cr , gp , gpa
        //TextView ???
        //        ?? setText ???
    }

    public double grade(String s)
    {
        if (s.equals("A")) return 4.0;
        else if (s.equals("B+")) return 3.5;
        else if (s.equals("B")) return 3.0;
        else if (s.equals("C+")) return 2.5;
        else if (s.equals("C")) return 2.0;
        else if (s.equals("D+")) return 1.5;
        else if (s.equals("D")) return 1.0;
        //else if (s.equals("F")) return 0.0;

        return 0.0;
    }
    public void buttonClicked(View v) {
        int id = v.getId();

        if (id == R.id.button4)
        {
            Intent i = new Intent(this,CourseListActivity.class);

            String s = "List of Courses";
            for(int l = 0 ; l < listCodes.size() ; l++)
            {
                s += String.format("\n%s (%d credits) = %s",listCodes.get(l),listCredits.get(l),listGrades.get(l));
            }
            i.putExtra("list_of",s);

            startActivity(i);
        }
        else if (id == R.id.button2)
        {
            Intent i = new Intent(this,CourseActivity.class);
            startActivityForResult(i,111);
        }
        else if (id == R.id.button)
        {
            cr = 0;         // Credits
            gp = 0.0;    // Grade points
            gpa = 0.0;   // Grade point average

            listCodes = new ArrayList<String>();
            listCredits = new ArrayList<Integer>();
            listGrades = new ArrayList<String>();

            calculate();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 111)
        {
            if (resultCode == RESULT_OK)
            {
                listCodes.add(data.getStringExtra("v1"));
                listCredits.add(data.getIntExtra("v2",0));
                listGrades.add(data.getStringExtra("v3"));

                calculate();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
