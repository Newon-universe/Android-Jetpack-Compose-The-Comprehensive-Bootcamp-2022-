package com.example.jetnote.di

import android.content.Context
import androidx.room.Room
import com.example.jetnote.data.NoteDatabase
import com.example.jetnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    // data 패키지의 앱이름Database 는 구조이지, 실제 Database 가 아님,
    // 따라서 AppModule, 즉 provides 에서 실제 데이터베이스를 만들어줘야함
    // 참고로 ApplicationContext 는 언제든 내가 어디에 있는지 알며, 접근권한 또한 있는 어노테이션
    // 당연? 하지만 데이터베이스는 1개여야겠지..? , 따라서 NoteDatabase 를 리턴함
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db"
        )
            .fallbackToDestructiveMigration()
            .build()
}