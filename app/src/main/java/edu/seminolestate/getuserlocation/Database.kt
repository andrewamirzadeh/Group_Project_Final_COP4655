package edu.seminolestate.getuserlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class Database : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val actionBar = supportActionBar

        actionBar!!.title = "Database"

        actionBar.setDisplayHomeAsUpEnabled(true)

        addName.setOnClickListener {

            val db = DatabaseHelper(this, null)

            val name = enterName.text.toString()
            val cty = enterCountry.text.toString()

            db.addName(name, cty)

            Toast.makeText(this, name + " has been added to the database", Toast.LENGTH_LONG).show()

            enterName.text.clear()
            enterCountry.text.clear()
        }

        printName.setOnClickListener {

            val db = DatabaseHelper(this, null)

            val cursor = db.getName()

            cursor!!.moveToFirst()
            Name.append("1." + cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME_COl)) + "\n")
            Country.append("1." + cursor.getString(cursor.getColumnIndex(DatabaseHelper.CTY_COL)) + "\n")

            while (cursor.moveToNext()) {
                Name.append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME_COl)) + "\n")
                Country.append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CTY_COL)) + "\n")
            }

            cursor.close()
        }
    }
}