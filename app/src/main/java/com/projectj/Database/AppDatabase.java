package com.projectj.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.projectj.PodcastList;

import java.io.File;
import java.lang.ref.WeakReference;

@Database(entities = {PodcastDetails.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao dao();
    public static AppDatabase dbInstance;

    static AppDatabase getInstance(final Context context){

        if(dbInstance == null){
            synchronized (AppDatabase.class){
                if(dbInstance == null){
                    dbInstance = Room.databaseBuilder(context, AppDatabase.class, "LendersDetailDB")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new populateDB(context).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return dbInstance;
    }

    private static class populateDB extends AsyncTask<Void, Void, Void>{

        WeakReference<Context> context;

        public populateDB(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... v) {
            String[] name = {"Podcast1","Podcast2","Podcast3","Podcast4","Podcast5","Podcast6"};
            String[] namep = {"Podcaster1","Podcaster2","Podcaster3","Podcaster4","Podcaster5","Podcaster6"};
            String[] cn = {"12:30","12:30","12:30","12:30","12:30","12:30"};
            AppDao dao = getInstance(context.get()).dao();
            for(int i = 0; i < 6; i++)
                dao.AddPodcast(new PodcastDetails(name[i],cn[i],namep[i]));

            return null;
        }
    }

}
