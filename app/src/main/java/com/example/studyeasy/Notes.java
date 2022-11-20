package com.example.studyeasy;

public class Notes {

String note, title;
   public Notes(){

   }
   public Notes(String title, String note){
       this.note = note;
       this.title = title;
   }

    public String getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }
}
