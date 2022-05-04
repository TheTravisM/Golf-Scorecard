package io.travis.golfscorecard

import android.app.ListActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : ListActivity() {

    private val PREF_FILE: String = "io.travis.golfscorecard.preferences"
    private val KEY_STROKECOUNT: String = "key_strokecount"
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor
    private var mHoles:Array<Hole> = Array(18){ Hole("",0) }
    private lateinit var mListAdapter:ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()

        // Initialize Holes
        var strokes = 0;

        for (i in mHoles.indices) {
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0)
            mHoles[i] = Hole("Hole " + (i + 1) + " :", strokes)
        }

        mListAdapter = ListAdapter(this, mHoles)
        setListAdapter(mListAdapter)

    }

    override fun onPause() {
        super.onPause()

        for (i in mHoles.indices) {
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount())
        }
        mEditor.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_clear_strokes) {
            mEditor.clear()
            mEditor.apply()
            for (hole in mHoles) {
                hole.setStrokeCount(0)
            }
            mListAdapter.notifyDataSetChanged()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
