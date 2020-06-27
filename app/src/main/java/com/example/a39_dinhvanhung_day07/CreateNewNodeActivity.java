package com.example.a39_dinhvanhung_day07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a39_dinhvanhung_day07.databinding.ActivityCreateNewNodeBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewNodeActivity extends AppCompatActivity {
    ActivityCreateNewNodeBinding binding;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    ArrayList<String> type;
    StringBuilder tag = new StringBuilder();
    StringBuilder week = new StringBuilder();
    StringBuilder tam = new StringBuilder();
    String tune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create New Node");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_node);
        binding.tvTiMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(CreateNewNodeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.tvTiMe.setText("" + selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Time Picker Dialog");
                timePickerDialog.show();
            }
        });
        binding.tvDaTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrtentDate = Calendar.getInstance();
                int mYear = mcurrtentDate.get(Calendar.YEAR);
                int mMonth = mcurrtentDate.get(Calendar.MONTH);
                int mDay = mcurrtentDate.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(CreateNewNodeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        binding.tvDaTe.setText("" + selectedDayOfMonth + "/" + selectedMonth + "/" + selectedYear);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.setTitle("Date Picker Diaglog");
                datePickerDialog.show();
            }
        });
        type = new ArrayList<>();
        type.add("Work");
        type.add("Friend");
        type.add("Family");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, type);
        binding.spType.setAdapter(arrayAdapter);
        binding.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] tags = {"Family", "Game", "Android", "VTC", "Friend", "FPT"};
                final boolean[] booleans = {false, false, false, false, false, false};
                AlertDialog alertDialogTags = new AlertDialog.Builder(CreateNewNodeActivity.this)
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(tags, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int postion, boolean isChecked) {
                                if (isChecked) {
                                    tag.append(tags[postion] + ", ");
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (tag.length() > 2) {
                                    tag.setLength(tag.length() - 2);
                                }
                                binding.tvTags.setText(tag);
                                Toast.makeText(getBaseContext(),tag,Toast.LENGTH_SHORT).show();
                                tag.setLength(0);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_LONG).show();
                            }
                        })
                        .create();
                alertDialogTags.show();
            }
        });
        binding.tvWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] weeks = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};
                AlertDialog alertDialogWeeks = new AlertDialog.Builder(CreateNewNodeActivity.this)
                        .setTitle("Choose day")
                        .setMultiChoiceItems(weeks, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int postion, boolean isChecked) {
                                if (isChecked) {
                                    tam.append(weeks[postion]);
                                    tam.setLength(3);
                                    week.append(tam + ", ");
                                    tam.setLength(0);
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (week.length() > 2) {
                                    week.setLength(week.length() - 2);
                                }
                                binding.tvWeeks.setText(week);
                                Toast.makeText(getBaseContext(),week,Toast.LENGTH_SHORT).show();
                                week.setLength(0);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_LONG).show();
                            }
                        })
                        .create();
                alertDialogWeeks.show();
            }
        });
        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.tunemenu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnFromFile:
                                Toast.makeText(getBaseContext(),"From File",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnFromDefaults:
                                final String[] tunes = {"Nexus tune","Winphone tune","Peep tune", "Nokia tune","Etc"};
                                AlertDialog alertDialogTune = new AlertDialog.Builder(CreateNewNodeActivity.this)
                                        .setTitle("Choose tune:")
                                        .setSingleChoiceItems(tunes, 1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int postion) {
                                                tune = tunes[postion];
                                            }
                                        })
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getBaseContext(),"Set tune: "+ tune,Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .create();
                                alertDialogTune.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnSave:
                Toast.makeText(getBaseContext(), "Save", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnCancel:
                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_LONG).show();
                Intent itMain = new Intent(getBaseContext(), MainActivity.class);
                startActivity(itMain);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
